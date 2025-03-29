package com.example.springboot.controller;

import com.example.springboot.dto.LivroDTO;
import com.example.springboot.model.Livro;
import com.example.springboot.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    // Método para converter Livro em LivroDTO
    private LivroDTO convertToDTO(Livro livro) {
        return new LivroDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getEditora()
        );
    }

    // Método para converter uma lista de Livros em LivroDTOs
    private List<LivroDTO> convertToDTOList(List<Livro> livros) {
        return livros.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<LivroDTO> getAllLivros() {
        List<Livro> livros = livroService.getAllLivros();
        return convertToDTOList(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> getLivroById(@PathVariable Long id) {
        Optional<Livro> livro = livroService.getLivroById(id);
        return livro.map(value -> new ResponseEntity<>(convertToDTO(value), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LivroDTO createLivro(@RequestBody LivroDTO livroDTO) {
        Livro livro = new Livro();
        livro.setTitulo(livroDTO.getTitulo());
        livro.setAutor(livroDTO.getAutor());
        livro.setEditora(livroDTO.getEditora());
        Livro savedLivro = livroService.createLivro(livro);
        return convertToDTO(savedLivro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO> updateLivro(@PathVariable Long id, @RequestBody LivroDTO livroDTO) {
        Livro livro = new Livro();
        livro.setId(id);
        livro.setTitulo(livroDTO.getTitulo());
        livro.setAutor(livroDTO.getAutor());
        livro.setEditora(livroDTO.getEditora());

        Livro updatedLivro = livroService.updateLivro(id, livro);
        if (updatedLivro != null) {
            return new ResponseEntity<>(convertToDTO(updatedLivro), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLivro(@PathVariable Long id) {
        livroService.deleteLivro(id);
    }
}
