package com.renatoav.hardwired.repository;

import com.renatoav.hardwired.entity.Componente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComponenteRepository extends JpaRepository<Componente, Long> {
}
