package com.money.api.moneyapi.model;


import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;



import lombok.Data;

@Entity
@Table(name="pessoas")
@Data
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  
    @NotNull @NotEmpty
    private String nome;
    
 
    @NotNull 
    private Boolean ativo;
    
    @Embedded
    private Endereco endereco;
    
}
