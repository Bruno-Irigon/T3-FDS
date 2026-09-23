package com.bcopstein.ex1biblioeca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Indexed;

public class Autor {

    private long id;
    private String nome;
    private LocalDate dataNascimento;

    private List<Livro> livros = new ArrayList<>();

    public Autor() {
    }

    public Autor(String nome, long id, LocalDate dataNascimento) {
        this.nome = nome;
        this.id = id;
        this.dataNascimento = dataNascimento;
    }

    public long getId(){
        return this.id;
    }

    public String getNome(){
        return this.nome;
    }

    public LocalDate getDate(){
        return this.dataNascimento;
    }

    public List<Livro> getLivros(){
        return this.livros;
    }

    public String toString(){
        return "Autor [id= " + this.id + 
                ", nome= " + this.nome + 
                ", data de Nascimento= " + this.dataNascimento + "]";
    }
}
