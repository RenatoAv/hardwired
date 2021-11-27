package com.renatoav.hardwired.controller;

import com.renatoav.hardwired.config.security.JWTService;
import com.renatoav.hardwired.dto.LoginRequest;
import com.renatoav.hardwired.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/autenticacao")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @PostMapping("/login")
    public void login(LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Authentication authenticacao = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword());

        try {
            authenticacao = authenticationManager.authenticate(authenticacao);
        } catch (AuthenticationException auex) {
            throw new BadCredentialsException("Login incorreto");
        }

        jwtService.criarToken(request, response, (Usuario) authenticacao.getPrincipal());
    }

    @GetMapping("/token/refresh")
    public void refresh(LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        jwtService.refresh(request, response, request.getHeader(AUTHORIZATION));
    }
}
