package com.renatoav.hardwired.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@DiscriminatorColumn(name = "tipo")
@NoArgsConstructor
public abstract class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(insertable = false, updatable = false)
    private String tipo;
    private String nome;
    private String email;
    private String username;
    private String password;
    private String roles;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public Usuario(String nome, String email, String username, String password, String roles, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled) {
        this.tipo = tipo;
        this.nome = nome;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return stream(roles.split(",")).map(SimpleGrantedAuthority::new).collect(toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
