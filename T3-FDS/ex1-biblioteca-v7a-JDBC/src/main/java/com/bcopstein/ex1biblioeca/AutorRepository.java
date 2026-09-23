package com.bcopstein.ex1biblioeca;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AutorRepository {
    private final JdbcTemplate JdbcTemplate;

    public AutorRepository(JdbcTemplate JdbcTemplate) {
        this.JdbcTemplate = JdbcTemplate;
    }

    public RowMapper rowMapper = (rs, rowNum) -> new Autor(
        rs.getString("nome"),
        rs.getLong("Id"),
        rs.getDate("data_nascimento").toLocalDate()
    );

    public Autor save(Autor autor) {
        JdbcTemplate.update("INSERT INTO autor (id, nome, data_nascimento) VALUES (?, ?, ?)",
                autor.getId(), autor.getNome(), autor.getDate());
        return autor;
    }

    public List<Autor> findAll() {
        return JdbcTemplate.query("SELECT * FROM autor", rowMapper);
    }

    public Optional<Autor> findByNome(String nome) {
        List<Autor> autores = JdbcTemplate.query("SELECT * FROM autor WHERE nome = ?", rowMapper, nome);
        return autores.stream().findFirst();
    }
}
