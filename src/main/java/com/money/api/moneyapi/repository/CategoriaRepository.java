package com.money.api.moneyapi.repository;

import com.money.api.moneyapi.model.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria,Long>{
    
}
