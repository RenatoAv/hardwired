package com.renatoav.hardwired.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.renatoav.hardwired.entity.Usuario;
import com.renatoav.hardwired.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
public class JWTService {

    private static final String SECRET = "secret";
    private final Algorithm algorithm = Algorithm.HMAC256(SECRET.getBytes());
    private final JWTVerifier verifier = JWT.require(algorithm).build();
    private final Integer ACCESS_TOKEN_VALIDITY = 10 * 60 * 1000; // milissegundos
    private final Integer REFRESH_TOKEN_VALIDITY = 30 * 60 * 1000; // milissegundos

    private final UsuarioService usuarioService;

    public void criarToken(HttpServletRequest request, HttpServletResponse response, Usuario usuario) throws IOException {
        String accessToken = gerarAccessToken(usuario, request);
        String refreshToken = gerarRefreshToken(usuario, request);
        adicionarResposta(accessToken, refreshToken, response);
    }

    private void adicionarResposta(String accessToken, String refreshToken, HttpServletResponse response) throws IOException {
        Map<String, String> tokens = new HashMap<>(){{
            put("access_token", accessToken);
            put("refresh_token", refreshToken);
        }};
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

    private String gerarAccessToken(Usuario usuario, HttpServletRequest request) {
        return JWT.create()
                .withSubject(usuario.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", usuario.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(toList()))
                .sign(algorithm);
    }

    private String gerarRefreshToken(Usuario usuario, HttpServletRequest request) {
        return JWT.create()
                .withSubject(usuario.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
    }

    public void refresh(HttpServletRequest request, HttpServletResponse response, String refreshToken) throws IOException {
        DecodedJWT decodedJWT = decode(refreshToken, response);
        Usuario usuario = (Usuario) usuarioService.loadUserByUsername(decodedJWT.getSubject());
        criarToken(request, response, usuario);
    }

    public DecodedJWT decode(String token, HttpServletResponse response) throws IOException {
        DecodedJWT decodedJWT = null;

        try {
            decodedJWT = verifier.verify(token);
        } catch (Exception e) {
            HashMap<String, String> erro = new HashMap<>() {{
                put("error_message", e.getMessage());
            }};
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), erro);
        }

        return decodedJWT;
    }
}
