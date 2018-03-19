package br.nom.penha.bruno.arquiteto.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.Table;

import br.nom.penha.bruno.arquiteto.dao.PersistentEntity;

@Entity
@Table(name="clientes")
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c ORDER BY c.id", hints =
    @QueryHint(name = "org.hibernate.cacheable", value = "true"))})
public class Cliente implements Serializable, PersistentEntity<Integer> {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String telefone;
    private String email;
    private String senha;
    private boolean ativo = true;

    @Column(name="endereco_rua")
    private String enderecoRua;
    @Column(name="endereco_numero")
    private String enderecoNumero;
    @Column(name="endereco_bairro")
    private String enderecoBairro;
    @Column(name="endereco_cep")
    private String enderecoCep;
    @Column(name="endereco_cidade")
    private String enderecoCidade;
    @Column(name="endereco_estado")
    private String enderecoEstado;
    
    public Cliente(){
    }
    
    public Cliente(String nome, String email, String senha, String telefone, boolean ativo,
            String enderecoRua, String enderecoNumero, String enderecoBairro, String enderecoCep,
            String enderecoCidade, String enderecoEstado ) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.ativo = ativo;
        this.enderecoRua = enderecoRua;
        this.enderecoNumero = enderecoNumero;
        this.enderecoBairro = enderecoBairro;
        this.enderecoCep = enderecoCep;
        this.enderecoCidade = enderecoCidade;
        this.enderecoEstado = enderecoEstado;
    }
    
    public Cliente(Integer id, String nome, String email, String senha, String telefone, boolean ativo,
            String enderecoRua, String enderecoNumero, String enderecoBairro, String enderecoCep,
            String enderecoCidade, String enderecoEstado ) {
        this(nome,email,senha,telefone,ativo,enderecoRua,enderecoNumero,enderecoBairro,enderecoCep,
                enderecoCidade,enderecoEstado);
        this.id = id;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public Integer getId(){
        return id;
    }
    
    public void setId(Integer id){
        this.id = id;
    }
    
    public String getTelefone(){
        return telefone;
    }
    
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getSenha(){
        return senha;
    }
    
    public void setSenha(String senha){
        this.senha = senha;
    }
    
    public boolean isAtivo() {
        return ativo;
    }
    
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    public String getEnderecoBairro() {
        return enderecoBairro;
    }
    
    public void setEnderecoBairro(String enderecoBairro) {
        this.enderecoBairro = enderecoBairro;
    }
    
    public String getEnderecoCep() {
        return enderecoCep;
    }
    
    public void setEnderecoCep(String enderecoCep) {
        this.enderecoCep = enderecoCep;
    }
    
    public String getEnderecoCidade() {
        return enderecoCidade;
    }
    
    public void setEnderecoCidade(String enderecoCidade) {
        this.enderecoCidade = enderecoCidade;
    }
    
    public String getEnderecoEstado() {
        return enderecoEstado;
    }
    
    public void setEnderecoEstado(String enderecoEstado) {
        this.enderecoEstado = enderecoEstado;
    }
    
    public String getEnderecoNumero() {
        return enderecoNumero;
    }
    
    public void setEnderecoNumero(String enderecoNumero) {
        this.enderecoNumero = enderecoNumero;
    }
    
    public String getEnderecoRua() {
        return enderecoRua;
    }
    
    public void setEnderecoRua(String enderecoRua) {
        this.enderecoRua = enderecoRua;
    }
    
    @Override
    public String toString() {
        return getNome() + " - " + getId();
    }

    @Override
    public boolean equals(Object o) {
    	if (o == null) return false;
      	if (this == o) return true;      
        boolean result = false;
        if (o instanceof Cliente) {
            Cliente outro = (Cliente) o;
            result = this.getEmail() != null && 
                     this.getEmail().equals(outro.getEmail());
        }
        return result;
    }

    @Override
    public int hashCode() {
        int result = getEmail() != null ? getEmail().hashCode() : 41;
        return result;
    }

    public boolean isNew() {
        return id == null;
    }
    
}