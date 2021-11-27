package com.renatoav.hardwired.service;

import com.renatoav.hardwired.entity.Componente;
import com.renatoav.hardwired.repository.ComponenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComponenteServiceImpl implements ComponenteService {

    private final ComponenteRepository componenteRepository;

    @Override
    public void salvar(Componente componente) {
        componenteRepository.save(componente);
    }

    @Override
    public void remover(Long id) {
        componenteRepository.deleteById(id);
    }
}
