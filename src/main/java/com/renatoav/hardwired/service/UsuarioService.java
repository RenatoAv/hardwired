package com.renatoav.hardwired.service;

import com.renatoav.hardwired.entity.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioService extends UserDetailsService {
    void salvar(Usuario usuario);
    void remover(Long id);
}
