package com.bcopstein.ex1biblioeca;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UsuarioRepository {
    private final JdbcTemplate jdbcTemplate;

    public UsuarioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Usuario> rowMapper = (rs, rowNum) -> new Usuario(
        rs.getLong("id"),
        rs.getString("nome"),
        rs.getString("email")
    );

    public boolean existsById(long id) {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM usuario WHERE id = ?", Integer.class, id);
        return count != null && count > 0;
    }

    public Usuario save(Usuario usuario) {
        jdbcTemplate.update("INSERT INTO usuario (nome, email) VALUES (?, ?)", usuario.getNome(), usuario.getEmail());
        Long lastId = jdbcTemplate.queryForObject("SELECT MAX(id) FROM usuario", Long.class);
        return new Usuario(lastId != null ? lastId : 1, usuario.getNome(), usuario.getEmail());
    }

    public Usuario findById(long id) {
        List<Usuario> usuarios = jdbcTemplate.query("SELECT * FROM usuario WHERE id = ?", rowMapper, id);
        return usuarios.isEmpty() ? null : usuarios.get(0);
    }

    public void addLivroLido(long usuarioId, long livroId) {
        jdbcTemplate.update("INSERT INTO usuario_livro_lido (usuario_id, livro_id) VALUES (?, ?)", usuarioId, livroId);
    }

    public List<LivroResponseDTO> findLivrosLidos(long usuarioId) {
        String sql = "SELECT l.id, l.titulo, l.ano, a.nome as autor_nome " +
                     "FROM livro l " +
                     "JOIN autor a ON l.autor_id = a.id " +
                     "JOIN usuario_livro_lido ul ON l.id = ul.livro_id " +
                     "WHERE ul.usuario_id = ?";
        
        return jdbcTemplate.query(sql, (rs, rowNum) -> new LivroResponseDTO(
            rs.getLong("id"),
            rs.getString("titulo"),
            rs.getInt("ano"),
            rs.getString("autor_nome")
        ), usuarioId);
    }
}