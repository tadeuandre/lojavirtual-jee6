/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.nom.penha.bruno.arquiteto.web.controller;

import br.nom.penha.bruno.arquiteto.dao.CategoriaDAO;
import br.nom.penha.bruno.arquiteto.model.Categoria;
import br.nom.penha.bruno.arquiteto.web.util.JSFHelper;

import java.io.Serializable;
import java.util.Collection;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import org.apache.log4j.Logger;

@Named("categoriaController")
@ConversationScoped
public class CategoriaController implements Serializable{

    private final static Logger logger = Logger.getLogger(CategoriaController.class);
    private Categoria categoria = new Categoria();
    private Collection<Categoria> categorias;
    
    @EJB
    CategoriaDAO categoriaDAO;

    public CategoriaDAO getCategoriaDAO() {
        return categoriaDAO;
    }

    public void setCategoriaDAO(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }
    
    
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    
    /**
     * Retorna a última categoria selecionado. Caso o atributo
     * categoria seja null ele é inicializado para uma nova entidade
     * 
     * 
     * @return uma categoria
     */
    public Categoria getCategoria() {
        if (categoria == null) {
            categoria = new Categoria();
        }
        return categoria;
    }

    /**
     * inicializa o atributo categoria para uma nova entidade
     * 
     * @return "editarCategoria"
     */
    public String setup() {
        categoria = new Categoria();
        return "/categoria/Edit";
    }

    /** 
     * método vinculado ao botão salvar do formulário de criação/edição de
     * categoria. Aciona o dao correspondente a categoria
     * 
     * @return null ou "sucesso"
     */
    public String salvar() {
        try {
            categoriaDAO.save(categoria);
            reset();
            JSFHelper.addSuccessMessage("categoria foi salva com sucesso");
        } catch (Exception e) {
            String msg = "Erro ao salvar a categoria";
            logger.error(msg, e);
            JSFHelper.addErrorMessage(msg);
            return null;
        }
        return "/categoria/List";
    }

    /**
     * Método vinculado ao link de edição de uma categoria. Atualiza
     * o atributo categoria com a categoria selecionado e navega para a página
     * de edição.
     * 
     * @return editarCategoria
     */
    public String verDetalhes(Categoria categoria) {
        this.categoria = categoria;
        return "/categoria/Edit";
    }

    /**
     * Método vinculado ao link de remoção de uma categoria. Aciona 
     * o DAO correspondente a categoria
     * 
     * @return null ou "sucesso"
     */
    public String remover(Categoria categoria) {
        try {
            categoriaDAO.delete(categoria);
            reset();
            JSFHelper.addSuccessMessage("A categoria foi removida com sucesso");
        } catch (Exception e) {
            String msg = "Erro ao remover a categoria";
            logger.error(msg, e);
            JSFHelper.addErrorMessage(msg);
            return null;
        }
        return "/categoria/List";
    }

    /**
     * joga os valores dos atributos para null
     */
    private void reset() {
        categorias = null;
        categoria = null;
    }

    public Collection<Categoria> getCategorias() {
        try {
            if (categorias == null) {
                categorias = categoriaDAO.findAll();
            }
        } catch (Exception e) {
            String msg = "Erro ao carregar categorias";
            logger.error(msg, e);
            JSFHelper.addErrorMessage(msg);
            return null;
        }
        return categorias;
    }
}
