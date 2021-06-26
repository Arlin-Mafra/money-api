package com.money.api.moneyapi.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.money.api.moneyapi.model.Pessoa;
import com.money.api.moneyapi.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepo;

    @GetMapping
    public List<Pessoa> listar() {

        return pessoaRepo.findAll();
    }

    @PostMapping
    public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {

        Pessoa pessoaSalva = pessoaRepo.save(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Pessoa> show(@PathVariable long id) {
        return pessoaRepo.findById(id).map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Pessoa> edit( @PathVariable Long id, @Valid @RequestBody Pessoa pessoa) {
        // return pessoaRepo.findById(id).map(record -> {
        //     record.setNome(pessoa.getNome());
        //     record.setAtivo(pessoa.getAtivo());
        //     record.setEndereco(pessoa.getEndereco());
        //     Pessoa updated = pessoaRepo.save(record);
        //     return ResponseEntity.ok().body(updated);
        // }).orElse(ResponseEntity.notFound().build());

        Optional<Pessoa> pessoaSalva = pessoaRepo.findById(id);
        if(pessoaSalva == null){
            throw new EmptyResultDataAccessException(1);
        }else{
            Pessoa _pessoa = pessoaSalva.get();
            _pessoa.setNome(pessoa.getNome());
            _pessoa.setAtivo(pessoa.getAtivo());
            _pessoa.setEndereco(pessoa.getEndereco());

            return new ResponseEntity<>(pessoaRepo.save(_pessoa), HttpStatus.OK);
        }

    }

}
