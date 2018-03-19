/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.nom.penha.bruno.arquiteto.web.listener;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.log4j.Logger;

import br.nom.penha.bruno.arquiteto.web.registry.MenuRegistry;

/**
 * Web application lifecycle listener.
 * @author Kleber
 */
@WebListener()
public class ComprasWebListener implements ServletContextListener {

    
    private final static Logger logger = Logger.getLogger(ComprasWebListener.class);
    
    @EJB
    MenuRegistry registry;
   
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("====================== Inicializando modulo de compras =============================");
        registry.addMenu("/WEB-INF/includes/menu-compras.xhtml");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("====================== Finalizando modulo de compras ================================");
    }
}
