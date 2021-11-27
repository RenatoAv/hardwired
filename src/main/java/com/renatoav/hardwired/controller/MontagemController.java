package com.renatoav.hardwired.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.renatoav.hardwired.dto.CadastrarMontagemRequest;
import com.renatoav.hardwired.entity.Montagem;
import com.renatoav.hardwired.service.MontagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/montagem")
@RequiredArgsConstructor
public class MontagemController {

    private final MontagemService montagemService;
    private final ObjectMapper mapper;

    @PostMapping
    public void salvar(@RequestBody CadastrarMontagemRequest montagem) {
        montagemService.salvar(mapper.convertValue(montagem, Montagem.class));
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
       montagemService.remover(id);
    }
}
