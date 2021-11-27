package com.renatoav.hardwired.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Cliente extends Usuario {

    public Cliente(String nome, String email, String username, String password, String roles) {
        super(nome, email, username, password, roles, true, true, true, true);
    }
}
