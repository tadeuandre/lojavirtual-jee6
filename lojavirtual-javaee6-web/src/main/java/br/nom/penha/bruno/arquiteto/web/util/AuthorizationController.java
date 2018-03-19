/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.nom.penha.bruno.arquiteto.web.util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 * Classe utilizada para gerenciar a autorização de exibição de componentes JSF baseada
 * no role do usuario
 * 
 * @author Kleber
 */
@Named
public class AuthorizationController {
    
    /**
     * Verifica se o usuario atual possui um determinado role
     * 
     * @param role role necessário para autorizaçao
     * @return true se o usuário atual possui o role passado como parâmetro
     */
    public boolean hasRole(String role) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        return request.isUserInRole(role);
    }
}
