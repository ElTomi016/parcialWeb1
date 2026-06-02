package com.parcial.backend.controller;

import com.parcial.backend.entity.Biblioteca;
import com.parcial.backend.service.BibliotecaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bibliotecas")
public class BibliotecaController {

    private final BibliotecaService bibliotecaService;

    public BibliotecaController(BibliotecaService bibliotecaService) {
        this.bibliotecaService = bibliotecaService;
    }

    @GetMapping
    public ResponseEntity<List<Biblioteca>> getAll() {
        return ResponseEntity.ok(bibliotecaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Biblioteca> getById(@PathVariable Long id) {
        return bibliotecaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Biblioteca> create(@RequestBody Biblioteca biblioteca) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bibliotecaService.save(biblioteca));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Biblioteca> update(@PathVariable Long id,
                                              @RequestBody Biblioteca bibliotecaDetails) {
        return bibliotecaService.update(id, bibliotecaDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return bibliotecaService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
