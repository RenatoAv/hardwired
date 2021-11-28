package com.renatoav.hardwired.service;

import com.renatoav.hardwired.entity.Usuario;
import com.renatoav.hardwired.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public void salvar(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
    }

    @Override
    public void remover(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario obterUsuarioAutenticado() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (Usuario) loadUserByUsername(username);
    }

}
