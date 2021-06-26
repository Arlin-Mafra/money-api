package com.money.api.moneyapi.controller;

import java.util.List;

import javax.validation.Valid;

import com.money.api.moneyapi.model.Categoria;
import com.money.api.moneyapi.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/categorias" })
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepo;

    @GetMapping
    public List<Categoria> listar() {
        return categoriaRepo.findAll();

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Categoria> show(@PathVariable long id){
        return categoriaRepo.findById(id)
        .map(record ->  ResponseEntity.ok().body(record))
        .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria) {

        Categoria categoriaSalva = categoriaRepo.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Categoria> editCategoria(@PathVariable("id") long id, @RequestBody Categoria categoria) {
        return categoriaRepo.findById(id).map(record -> {
            record.setNome(categoria.getNome());
            Categoria updated = categoriaRepo.save(record);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long id){             
        categoriaRepo.deleteById(id);
    }
}
