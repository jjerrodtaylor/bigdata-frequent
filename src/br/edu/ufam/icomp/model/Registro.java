package br.edu.ufam.icomp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Classe de modelo que representa um registro no conjunto de dados.
 */
public class Registro implements Comparable<Registro> {

    private List<String> itens = new ArrayList<String>();
    private int contador;

    /**
     * Criar um objeto de registro a partir do conteúdo separado por vírgula do
     * arquivo original
     * 
     * @param content
     *            linha do arquivo original
     * @return Registro criado a partir da linha
     */
    public static Registro criar(String content) {
        Registro reg = new Registro();
        StringTokenizer st = new StringTokenizer(content, ",");
        while (st.hasMoreTokens()) {
            reg.addItem(st.nextToken());
        }
        return reg;
    }

    public Registro() {
    }

    public Registro(List<String> itens, int contador) {
        setItems(itens);
        setContador(contador);
    }

    public List<String> getItens() {
        return itens;
    }

    public void setItems(List<String> itens) {
        this.itens = itens;
        Collections.sort(this.itens);
    }

    public void addItem(String item) {
        this.itens.add(item);
        Collections.sort(this.itens);
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    @Override
    public int compareTo(Registro that) {
        List<String> thisItems = this.getItens();
        List<String> thatItems = that.getItens();
        if (thisItems == thatItems) {
            return 0;
        }

        for (int i = 0; i < thisItems.size(); i++) {
            int diff = thisItems.get(i).compareTo(thatItems.get(i));
            if (diff != 0) {
                return diff;
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Registro) {
            Registro that = (Registro) obj;
            return that.itens.equals(this.itens);
        }
        return false;
    }

    /**
     * Gerador simples de hashcode, que se utiliza da implementação padrão de
     * hashcode do ArrayList, quando disponível, ou uma constante prima, quando
     * a lista for vazia
     */
    @Override
    public int hashCode() {
        final int primo = 31;
        int resultado = 1;
        // Gera um hash baseado no hash da lista de itens (se houver)
        resultado = primo * resultado + ((itens == null) ? 0 : itens.hashCode());
        return resultado;
    }
}
