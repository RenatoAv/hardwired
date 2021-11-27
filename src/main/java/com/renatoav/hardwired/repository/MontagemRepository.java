package com.renatoav.hardwired.repository;

import com.renatoav.hardwired.entity.Montagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MontagemRepository extends JpaRepository<Montagem, Long> {
}
