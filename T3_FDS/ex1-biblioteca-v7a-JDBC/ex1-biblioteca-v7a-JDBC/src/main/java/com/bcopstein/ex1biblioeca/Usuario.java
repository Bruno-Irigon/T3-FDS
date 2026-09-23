package com.bcopstein.ex1biblioeca;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity 
public class Usuario {
    @Id 
    private long id;
    private String nome;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Livro> lidos = new ArrayList<>();


}
