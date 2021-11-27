package com.renatoav.hardwired.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.renatoav.hardwired.dto.CadastrarMontagemRequest;
import com.renatoav.hardwired.dto.RemoverMontagemRequest;
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

    @DeleteMapping
    public void remover(@RequestBody RemoverMontagemRequest montagem) {
        montagemService.remover(mapper.convertValue(montagem, Montagem.class));
    }
}
