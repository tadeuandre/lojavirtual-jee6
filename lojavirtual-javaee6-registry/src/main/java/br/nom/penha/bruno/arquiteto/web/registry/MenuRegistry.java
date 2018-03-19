package br.nom.penha.bruno.arquiteto.web.registry;

import java.util.Set;
import javax.ejb.Remote;

@Remote
public interface MenuRegistry {

    public void addMenu(String menu);

    public Set<String> getMenus();

    public void removeMenu(String menu);
    
}
