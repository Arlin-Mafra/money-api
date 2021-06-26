package com.money.api.moneyapi.repository.lancamento;

import java.util.List;

import com.money.api.moneyapi.model.Lancamento;
import com.money.api.moneyapi.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {

    public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
    
}
