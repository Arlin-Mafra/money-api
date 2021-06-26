package com.money.api.moneyapi.repository;

import com.money.api.moneyapi.model.Pessoa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    
}
