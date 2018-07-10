package com.tads.ifrn.myapplication;

public class Alerta {

    private int id;
    private String titulo, categoria, mensagem;

    public Alerta(){}

    public Alerta(String titulo, String categoria, String mensagem){
        this.titulo = titulo;
        this.categoria = categoria;
        this.mensagem = mensagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
