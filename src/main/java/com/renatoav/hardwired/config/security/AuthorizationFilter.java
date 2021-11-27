package com.renatoav.hardwired.config.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class AuthorizationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;

    public AuthorizationFilter(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if(request.getServletPath().equals("/login") || request.getServletPath().equals("/token/refresh")) {
            filterChain.doFilter(request, response);
        } else {
            String authorization = request.getHeader(AUTHORIZATION);
            if(authorization != null && authorization.startsWith("Bearer ")) {
                String token = authorization.substring("Bearer ".length());
                DecodedJWT decodedJWT = jwtService.decode(token, response);
                String username = decodedJWT.getSubject();
                String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                Collection<SimpleGrantedAuthority> authorities = stream(roles)
                        .map(SimpleGrantedAuthority::new)
                        .collect(toList());
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

            filterChain.doFilter(request, response);
        }
    }

}
