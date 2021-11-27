package com.renatoav.hardwired.entity;

import lombok.Data;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
public class PrecoComponente {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private BigDecimal valor;
    private LocalDate data;
    @ManyToOne
    @JoinColumn(name = "id_componente")
    private Componente componente;
}
