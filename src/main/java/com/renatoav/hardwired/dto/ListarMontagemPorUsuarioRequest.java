package com.renatoav.hardwired.dto;

import com.renatoav.hardwired.entity.Componente;
import com.renatoav.hardwired.entity.Montagem;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ListarMontagemPorUsuarioRequest {
    private Long id;
    private String nome;
    private Long totalComponentes;
    private Double valorTotal;

    public ListarMontagemPorUsuarioRequest(Montagem montagem) {
        this.id = montagem.getId();
        this.nome = montagem.getNome();
        this.totalComponentes = montagem.getComponentes().stream().count();
    }

    public ListarMontagemPorUsuarioRequest(Long id, String nome, Long totalComponentes, BigDecimal valorTotal) {
        this.id = id;
        this.nome = nome;
        this.totalComponentes = totalComponentes;
        this.valorTotal = valorTotal == null ? 0d : valorTotal.doubleValue();
    }
}
