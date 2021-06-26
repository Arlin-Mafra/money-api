package com.money.api.moneyapi.repository;

import com.money.api.moneyapi.model.Lancamento;
import com.money.api.moneyapi.repository.lancamento.LancamentoRepositoryQuery;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {
    
}
