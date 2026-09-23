package com.bcopstein.ex1biblioeca;



public class Livro {

    private long id;
    private String titulo;
    private int ano;

    private Autor autor; 

    public Livro() {}

    public Livro(long id, String titulo, Autor autor, int ano) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
    }

    public long getId() {
        return this.id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public int getAno() {
        return this.ano;
    }

    public Autor getAutor(){
        return this.autor;
    }

    @Override
    public String toString() {
        return "Livro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", ano=" + ano + "]";
    }
}