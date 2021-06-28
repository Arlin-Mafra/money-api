package com.money.api.moneyapi.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import com.money.api.moneyapi.model.Lancamento;
import com.money.api.moneyapi.repository.LancamentoRepository;
import com.money.api.moneyapi.repository.filter.LancamentoFilter;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/lancamentos" })
public class LancamentoController {

    @Autowired
    private LancamentoRepository lancamentoRepo;

    @GetMapping
    public List<Lancamento> pesquisar(LancamentoFilter lancamentoFilter) {
        return lancamentoRepo.filtrar(lancamentoFilter);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Lancamento> show(@PathVariable Long id) {

        return lancamentoRepo.findById(id).map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento) {

        Lancamento lancamentoCriado = lancamentoRepo.save(lancamento);

        if (lancamentoCriado.getPessoa().getAtivo() == false) {
            throw new NullPointerException();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoCriado);

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Lancamento> edit(@PathVariable Long id, @RequestBody Lancamento lancamento) {

        Optional<Lancamento> lancamentoGet = lancamentoRepo.findById(id);

        if (lancamentoGet == null) {
            throw new NoSuchElementException();
        } else {

            Lancamento _lancamento = lancamentoGet.get();
            _lancamento.setDescricao(lancamento.getDescricao());
            _lancamento.setDataVencimento(lancamento.getDataVencimento());
            _lancamento.setDataPagamento(lancamento.getDataPagamento());
            _lancamento.setValor(lancamento.getValor());
            _lancamento.setObservacao(lancamento.getObservacao());
            _lancamento.setTipo(lancamento.getTipo());
            _lancamento.setCategoria(lancamento.getCategoria());
            _lancamento.setPessoa(lancamento.getPessoa());

            return new ResponseEntity<>(lancamentoRepo.save(_lancamento), HttpStatus.OK);
        }
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        lancamentoRepo.deleteById(id);
    }
}
