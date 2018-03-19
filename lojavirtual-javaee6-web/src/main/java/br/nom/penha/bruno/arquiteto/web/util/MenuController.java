/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.nom.penha.bruno.arquiteto.web.util;

import java.util.Set;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.nom.penha.bruno.arquiteto.web.registry.MenuRegistry;

/**
 *
 * @author Kleber
 */
@Named
@ApplicationScoped
public class MenuController {
    
    @EJB
    private MenuRegistry registry;
    
    public Set<String> getMenus() {
        return registry.getMenus();
    }
}
