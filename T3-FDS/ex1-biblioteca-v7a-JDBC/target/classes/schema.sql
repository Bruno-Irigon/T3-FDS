CREATE TABLE autor (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(255),
    data_nascimento DATE
);

CREATE TABLE livro (
    id BIGINT PRIMARY KEY,
    titulo VARCHAR(255),
    ano INT,
    autor_id BIGINT,
    FOREIGN KEY (autor_id) REFERENCES autor(id)
);

CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE usuario_livro_lido (
    usuario_id BIGINT,
    livro_id BIGINT,
    PRIMARY KEY (usuario_id, livro_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (livro_id) REFERENCES livro(id)
);