package br.nom.penha.bruno.arquiteto.comparator;

import java.util.Comparator;

import br.nom.penha.bruno.arquiteto.model.Categoria;


public class ComparatorCategoriaDescricao implements Comparator {

    public int compare(Object o1, Object o2) {

        Categoria cat1 = (Categoria)o1;
        Categoria cat2 = (Categoria)o2;

        return compareDescricao(cat1, cat2);

    }

    private int compareDescricao(Categoria cat1, Categoria cat2) {

        if (cat1.getDescricao() == null && cat2.getDescricao() == null) {
            return 0;
        } else if (cat1.getDescricao() == null) {
            return -1;
        } else if (cat2.getDescricao() == null) {
            return +1;
        } else {
            return cat1.getDescricao().compareTo(cat2.getDescricao());
        }

    }

}
