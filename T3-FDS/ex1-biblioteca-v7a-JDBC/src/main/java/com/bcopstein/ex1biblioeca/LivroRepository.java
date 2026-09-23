package com.bcopstein.ex1biblioeca;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class LivroRepository{
    private final Acervo acervo;
    private final JdbcTemplate JdbcTemplate;

    public LivroRepository(JdbcTemplate JdbcTemplate, Acervo acervo){
        this.JdbcTemplate = JdbcTemplate;
        this.acervo = acervo;
    }
    
    public RowMapper<Livro> rowMapper = (rs, rowNum) -> {
        Autor autor = new Autor(
                    rs.getString("nome"),
                    rs.getLong("Id"),
                    rs.getDate("data_nascimento").toLocalDate()
                );
        return new Livro(
                rs.getLong("id"),
                rs.getString("titulo"),
                autor,
                rs.getInt("ano")
        );
    };

    public long count(){
        Long count = JdbcTemplate.queryForObject("SELECT COUNT(*) FROM livro", Long.class);
        return count;
    }
    
    public Livro save(Livro livro) {
        JdbcTemplate.update("INSERT INTO livro (id, titulo, ano, autor_id) VALUES (?, ?, ?, ?)",
                livro.getId(), livro.getTitulo(), livro.getAno(), livro.getAutor().getId());
        return livro;
    }

    public List<Livro> findAll() {
        String sql = "SELECT l.id, l.titulo, l.ano, a.id as autor_id, a.nome as autor_nome, a.data_nascimento as autor_data " +
                     "FROM livro l JOIN autor a ON l.autor_id = a.id";
        return JdbcTemplate.query(sql, rowMapper);
    }

    public List<Livro> findByAutorNome(String nomeAutor) {
        String sql = "SELECT l.id, l.titulo, l.ano, a.id as autor_id, a.nome as autor_nome, a.data_nascimento as autor_data " +
                     "FROM livro l JOIN autor a ON l.autor_id = a.id WHERE a.nome = ?";
        return JdbcTemplate.query(sql, rowMapper, nomeAutor);
    }

    public Livro findByTitulo(String titulo) {
        String sql = "SELECT l.id, l.titulo, l.ano, a.id as autor_id, a.nome as autor_nome, a.data_nascimento as autor_data " +
                     "FROM livro l JOIN autor a ON l.autor_id = a.id WHERE l.titulo = ?";
        List<Livro> livros = JdbcTemplate.query(sql, rowMapper, titulo);
        return livros.isEmpty() ? null : livros.get(0);
    }

    public boolean existsById(long codigo) {
        Integer count = JdbcTemplate.queryForObject("SELECT COUNT(*) FROM livro WHERE id = ?", Integer.class, codigo);
        return count != null && count > 0;
    }

    public void deleteById(long codigo) {
        JdbcTemplate.update("DELETE FROM livro WHERE id = ?", codigo);
    }

}