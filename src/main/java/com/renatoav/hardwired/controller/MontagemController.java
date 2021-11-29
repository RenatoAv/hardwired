package com.renatoav.hardwired.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.renatoav.hardwired.dto.CadastrarMontagemRequest;
import com.renatoav.hardwired.dto.ListarMontagemPorUsuarioRequest;
import com.renatoav.hardwired.entity.Cliente;
import com.renatoav.hardwired.entity.Montagem;
import com.renatoav.hardwired.entity.Usuario;
import com.renatoav.hardwired.service.MontagemService;
import com.renatoav.hardwired.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/montagem")
@RequiredArgsConstructor
public class MontagemController {

    private final MontagemService montagemService;
    private final UsuarioService usuarioService;
    private final ObjectMapper mapper;

    @PostMapping
    public void salvar(@RequestBody CadastrarMontagemRequest cadastrarMontagemRequest) {
        Usuario usuario = usuarioService.obterUsuarioAutenticado();
        Montagem montagem = mapper.convertValue(cadastrarMontagemRequest, Montagem.class);
        montagem.setCliente((Cliente) usuario);
        montagemService.salvar(montagem);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
       montagemService.remover(id);
    }

    @GetMapping
    public Page<ListarMontagemPorUsuarioRequest> listarPorUsuarioAutenticado(@RequestParam(required = false, defaultValue = "0") int page) {
        Usuario usuario = usuarioService.obterUsuarioAutenticado();
        Pageable pageable = PageRequest.of(page, 5);
        return montagemService.listarPorIdCliente(usuario.getId(), pageable);
    }
}
