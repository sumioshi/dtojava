package com.example.springboot.dto;

public class LivroDTO {

    private Long id;
    private String titulo;
    private String autor;
    private String editora;

    // Construtores
    public LivroDTO(Long id, String titulo, String autor, String editora, Integer anoPublicacao, String isbn, Integer numeroPaginas) {
    }

    public LivroDTO(Long id, String titulo, String autor, String editora ) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }
}
