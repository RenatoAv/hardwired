package com.renatoav.hardwired.service;

import com.renatoav.hardwired.entity.Montagem;

public interface MontagemService {
    void salvar(Montagem montagem);
    void remover(Long id);
}
