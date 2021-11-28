package com.renatoav.hardwired.dto;

import com.renatoav.hardwired.entity.Componente;
import lombok.Data;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
public class CadastrarMontagemRequest {
    private String nome;
    List<Long> componentes;

    public List<Componente> getComponentes() {
        return componentes.stream().map(Componente::new).collect(toList());
    }
}
