package com.renatoav.hardwired.entity;

import lombok.Data;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
public class Montagem {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String nome;
    @ManyToMany
    @JoinTable(name="componente_montagem",
            joinColumns = {@JoinColumn(name = "id_montagem")},
            inverseJoinColumns = {@JoinColumn(name = "id_componente")})
    private List<Componente> componentes;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Cliente cliente;
}
