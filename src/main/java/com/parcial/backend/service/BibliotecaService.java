package com.parcial.backend.service;

import com.parcial.backend.entity.Biblioteca;
import com.parcial.backend.repository.BibliotecaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BibliotecaService {

    private final BibliotecaRepository bibliotecaRepository;

    public BibliotecaService(BibliotecaRepository bibliotecaRepository) {
        this.bibliotecaRepository = bibliotecaRepository;
    }

    public List<Biblioteca> findAll() {
        return bibliotecaRepository.findAll();
    }

    public Optional<Biblioteca> findById(Long id) {
        return bibliotecaRepository.findById(id);
    }

    public Biblioteca save(Biblioteca biblioteca) {
        return bibliotecaRepository.save(biblioteca);
    }

    public Optional<Biblioteca> update(Long id, Biblioteca details) {
        return bibliotecaRepository.findById(id).map(existing -> {
            existing.setNombre(details.getNombre());
            existing.setDireccion(details.getDireccion());
            existing.setTelefono(details.getTelefono());
            existing.setResponsable(details.getResponsable());
            return bibliotecaRepository.save(existing);
        });
    }

    public boolean delete(Long id) {
        if (bibliotecaRepository.existsById(id)) {
            bibliotecaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
