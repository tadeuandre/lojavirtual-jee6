/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.nom.penha.bruno.arquiteto.web.controller;

import br.nom.penha.bruno.arquiteto.comparator.ComparatorCategoriaDescricao;
import br.nom.penha.bruno.arquiteto.dao.CategoriaDAO;
import br.nom.penha.bruno.arquiteto.dao.ProdutoDAO;
import br.nom.penha.bruno.arquiteto.model.Categoria;
import br.nom.penha.bruno.arquiteto.model.Produto;
import br.nom.penha.bruno.arquiteto.model.ProdutoDigital;
import br.nom.penha.bruno.arquiteto.model.ProdutoMaterial;
import br.nom.penha.bruno.arquiteto.web.util.JSFHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import org.apache.log4j.Logger;

@Named("produtoController")
@ConversationScoped
public class ProdutoController implements Serializable{

    private final static Logger logger = Logger.getLogger(ProdutoController.class);
    private Produto produto = new ProdutoMaterial();
    private Collection<Produto> produtos;
    private List categorias = new ArrayList();
    private String tipo = "material";
    private List tipos;
    private boolean produtoMaterial = true;

    @EJB
    ProdutoDAO produtoDAO;
    
    @EJB
    CategoriaDAO categoriaDAO;

    public ProdutoDAO getProdutoDAO() {
        return produtoDAO;
    }

    public void setProdutoDAO(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }
        
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipos(List tipos) {
        this.tipos = tipos;
    }

    public List getTipos() {
        tipos = new ArrayList();
        tipos.add(new SelectItem("material", "material"));
        tipos.add(new SelectItem("digital", "digital"));
        return tipos;
    }

    public List getCategorias() {
        categorias = new ArrayList<Categoria>();
        try {
            categorias = (List<Categoria>) categoriaDAO.findAll();
            Collections.sort(categorias, new ComparatorCategoriaDescricao());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return categorias;
    }

    public void setProdutoMaterial(boolean produtoMaterial) {
        this.produtoMaterial = produtoMaterial;
    }

    public boolean isProdutoMaterial() {
        return produtoMaterial;
    }

    public void atualizarTipoProduto(ValueChangeEvent valueChangeEvent) {

        if (valueChangeEvent.getNewValue().equals("material")) {
            this.produtoMaterial = true;
            produto = new ProdutoMaterial(produto);
        } else {
            this.produtoMaterial = false;
            produto = new ProdutoDigital(produto);

        }
        logger.info("ValueChangeListener action executando...");

        FacesContext.getCurrentInstance().renderResponse();

    }

    /**
     * Retorna o último produto selecionado. Caso o atributo
     * produto seja null ele é inicializado para uma nova entidade
     * 
     * 
     * @return um produto
     */
    public Produto getProduto() {
        if (produto == null) {
            produto = new ProdutoMaterial();
        }
        return produto;
    }

    /**
     * inicializa o atributo produto para uma nova entidade
     * 
     * @return "/produto/Edit"
     */
    public String setup() {
        produto = new ProdutoMaterial();
        return "/produto/Edit";
    }

    /** 
     * método vinculado ao botão salvar do formulário de criação/edição de
     * um produto. Aciona o dao correspondente a produto
     * 
     * @return null ou "/produto/List"
     */
    public String salvar() {
        try {
            produtoDAO.save(produto);
            reset();
            JSFHelper.addSuccessMessage("produto foi salvo com sucesso");
        } catch (Exception e) {
            String msg = "Erro ao salvar o produto";
            logger.error(msg, e);
            JSFHelper.addErrorMessage(msg);
            return null;
        }
        return "/produto/List";
    }

    /**
     * Método vinculado ao link de edição de um produto. Atualiza
     * o atributo produto com o produto selecionado e navega para a página
     * de edição.
     * 
     * @return /produto/Edit
     */
    public String verDetalhes(Produto produto) {
        this.produto = produto;
        return "/produto/Edit";
    }

    /**
     * Método vinculado ao link de remoção de um protótipo. Aciona 
     * o DAO correspondente a produto
     * 
     * @return null ou "sucesso"
     */
    public String remover(Produto produto) {
        try {
            produtoDAO.delete(produto);
            reset();
            JSFHelper.addSuccessMessage("O produto foi removido com sucesso");
        } catch (Exception e) {
            String msg = "Erro ao remover o produto";
            logger.error(msg, e);
            JSFHelper.addErrorMessage(msg);
            return null;
        }
        return "/produto/List";
    }

    /**
     * joga os valores dos atributos para null
     */
    private void reset() {
        produtos = null;
        produto = null;
    }

    /**
     * retorna todos os produtos. Aciona o DAO correspondente a produto
     * 
     * @return ListDataModel com todas os protótipos
     */
    public Collection<Produto> getProdutos() {
        try {
            if (produtos == null) {
                produtos = produtoDAO.findAll();
            }
        } catch (Exception e) {
            String msg = "Erro ao carregar produtos";
            logger.error(msg, e);
            JSFHelper.addErrorMessage(msg);
            return null;
        }
        return produtos;
    }
}
