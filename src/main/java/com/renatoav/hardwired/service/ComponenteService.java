package com.renatoav.hardwired.service;

import com.renatoav.hardwired.entity.Componente;

public interface ComponenteService {
    void salvar(Componente componente);
    void remover(Long id);
}
