package com.bcopstein.ex1biblioeca;

import java.time.LocalDate;

public class Usuario {
    private long id;
    private String nome;
    private String email;

    public Usuario(){}

    public Usuario(long id, String nome, String email){
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public long getId(){
        return this.id;
    }

    public String getNome(){
        return this.nome;
    }

    public String getEmail(){
        return this.email;
    }

    public String toString(){
        return "Id: \n"+ this.id + 
                "Nome: \n"+ this.nome + 
                "data de Nascimento: " + this.email;
    }
}
