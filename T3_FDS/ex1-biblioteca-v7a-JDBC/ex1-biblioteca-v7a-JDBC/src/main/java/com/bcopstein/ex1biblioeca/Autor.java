package com.bcopstein.ex1biblioeca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Indexed;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Autor {
    @Id
    private long id;
    private String nome;
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
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
