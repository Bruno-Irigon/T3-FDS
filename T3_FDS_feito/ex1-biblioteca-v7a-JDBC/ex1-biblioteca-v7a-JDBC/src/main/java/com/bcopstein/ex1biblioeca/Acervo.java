package com.bcopstein.ex1biblioeca;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class Acervo {
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    @Autowired
    public Acervo(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    @PostConstruct
    public void init() {
        // Inicializa com dados padrão se o banco estiver vazio
        if (livroRepository.count() == 0) {
            Autor luccaRibeiro = autorRepository.save(
                new Autor("Lucca Ribeiro", 1, LocalDate.of(1990, 1, 1)));
            Autor seuJorge = autorRepository.save(
                new Autor("Seu Jorge", 2, LocalDate.of(1970, 1, 1)));
            Autor igorNunes = autorRepository.save(
                new Autor("Igor Nunes", 3, LocalDate.of(1980, 1, 1)));
            Autor rafaelGuimaraes = autorRepository.save(
                new Autor("Rafael guimarães", 4, LocalDate.of(1999, 1, 1)));
            Autor lindieNorman = autorRepository.save(
                new Autor("Lindie Norman", 5, LocalDate.of(1985, 1, 1)));
            Autor eduardoBoeira = autorRepository.save(
                new Autor("Eduardo Boeira", 6, LocalDate.of(1988, 1, 1)));

            livroRepository.save(new Livro(10, "Dias de dor", luccaRibeiro, 2011));
            livroRepository.save(new Livro(20, "Dias de Glória", seuJorge, 2000));
            livroRepository.save(new Livro(15, "Manos e panos", igorNunes, 2026));
            livroRepository.save(new Livro(17, "sociedade secreta", rafaelGuimaraes, 2019));
            livroRepository.save(new Livro(18, "irmandade oculta", lindieNorman, 2005));
            livroRepository.save(new Livro(19, "Senhor dos Cajados", eduardoBoeira, 2020));
        }
    }

    public List<Livro> getAll() {
        return livroRepository.findAll();
    }

    public List<String> getTitulos() {
        return getAll()
                .stream()
                .map(livro -> livro.getTitulo())
                .toList();
    }

    public List<String> getAutores() {
        return getAll()
                .stream()
                .map(livro -> livro.getAutor().getNome())
                .distinct()
                .toList();
    }

    public List<Livro> getLivrosDoAutor(String autor) {
        return livroRepository.findByAutorNome(autor);
    }

    public Livro getLivroTitulo(String titulo) {
        return livroRepository.findByTitulo(titulo);
    }

    public boolean cadastraLivroNovo(Livro livro) {
        livroRepository.save(livro);
        return true;
    }

    public boolean removeLivro(long codigo) {
        if (livroRepository.existsById(codigo)) {
            livroRepository.deleteById(codigo);
            return true;
        }
        return false;
    }

    public List<Autor> getAllAutores(){
        return autorRepository.findAll();
    }

    public Autor getAutorPorNome(String nome){
        return autorRepository.findByNome(nome).orElse(null);
    }
}
