package com.bcopstein.ex1biblioeca;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{
    List<Livro> findByAutor(Autor autor);
    List<Livro> findByAutorNome(String nomeAutor);
    Livro findByTitulo(String titulo);
}