package com.renatoav.hardwired.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.renatoav.hardwired.dto.CadastrarComponenteRequest;
import com.renatoav.hardwired.entity.Componente;
import com.renatoav.hardwired.service.ComponenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/componente")
@RequiredArgsConstructor
public class ComponenteController {

    private final ComponenteService componenteService;
    private final ObjectMapper mapper;

    @PostMapping
    public void salvar(@RequestBody CadastrarComponenteRequest componenteRequest) {
        componenteService.salvar(mapper.convertValue(componenteRequest, Componente.class));
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        componenteService.remover(id);
    }

}
