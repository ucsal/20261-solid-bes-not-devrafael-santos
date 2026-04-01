package br.com.ucsal.olimpiadas.view.model;

public abstract class MenuOpcao {
    private String titulo;

    public MenuOpcao(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public abstract void action();
}
