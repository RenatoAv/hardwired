package com.renatoav.hardwired.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CadastrarComponenteRequest {
    private String nome;
    private String tipo;
    private String caracteristicas;
    private LocalDate dataLancamento;
}
