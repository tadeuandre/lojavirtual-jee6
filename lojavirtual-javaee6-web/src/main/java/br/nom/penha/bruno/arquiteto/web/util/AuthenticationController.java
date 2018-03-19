package br.nom.penha.bruno.arquiteto.web.util;

import java.security.Principal;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 * Managed Bean utilizado para integração entre JSF e autenticação declarativa padrão Java EE, de forma
 * a permitir a criação de páginas de login utilizando componentes Faces.
 * 
 *
 */
@Named
@RequestScoped
public class AuthenticationController {

    private final static Logger logger = Logger.getLogger(AuthenticationController.class);
    private String username;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Método vinculado ao botão de submit da página de login. Faz o login do aplicativo
     * utilizando a API de Servlet 3
     * 
     * @return String para a página inicial do aplicativo ou null em caso de erro 
     */
    public String login() {

        logger.info("Iniciando Login " + username + " - " + password);
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        try {
            request.login(username, password);
        } catch (Exception e) {
            logger.error("Erro de login", e);
            JSFHelper.addErrorMessage(e.getMessage());
            return null;
        }

        Principal principal = request.getUserPrincipal();
        logger.info("Usuario autenticado " + principal.getName());
        return "/main/index";
    }

    /**
     * metodo que faz logout do aplicativo utilizando a API de Servlet 3.
     * 
     * @return String apontando para a pagina de login ou null em caso de erro de logout
     */
    public String logout() {
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            request.logout();
        } catch (Exception ex) {
            logger.error("Erro no logout", ex);
            return null;
        }
        return "/main/login";
    }
}
