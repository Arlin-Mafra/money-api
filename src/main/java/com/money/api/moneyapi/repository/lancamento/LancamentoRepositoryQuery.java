package com.money.api.moneyapi.repository.lancamento;

import com.money.api.moneyapi.model.Lancamento;
import com.money.api.moneyapi.repository.filter.LancamentoFilter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LancamentoRepositoryQuery {

    public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
    
}
