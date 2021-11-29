package com.renatoav.hardwired.service;

import com.renatoav.hardwired.dto.ListarMontagemPorUsuarioRequest;
import com.renatoav.hardwired.entity.Montagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MontagemService {
    void salvar(Montagem montagem);
    void remover(Long id);
    Page<ListarMontagemPorUsuarioRequest> listarPorIdCliente(Long id, Pageable pageable);
}
