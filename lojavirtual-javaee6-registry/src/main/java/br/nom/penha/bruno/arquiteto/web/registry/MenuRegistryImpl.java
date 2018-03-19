package br.nom.penha.bruno.arquiteto.web.registry;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.ejb.Singleton;

/**
 *
 * @author Kleber
 */
@Singleton
public class MenuRegistryImpl implements MenuRegistry {

    private Set<String> menus = new LinkedHashSet<String>();

    @Override
    public void addMenu(String menu) {
        menus.add(menu);
    }

    @Override
    public Set<String> getMenus() {
        return menus;
    }

    @Override
    public void removeMenu(String menu) {
        menus.remove(menu);
    }
}
