package com.renatoav.hardwired.repository;

import com.renatoav.hardwired.dto.ListarMontagemPorUsuarioRequest;
import com.renatoav.hardwired.entity.Montagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MontagemRepository extends JpaRepository<Montagem, Long> {

    @Query(value = "Select new com.renatoav.hardwired.dto.ListarMontagemPorUsuarioRequest(m.id, m.nome, count(componentes), sum(preco.valor)) " +
            "from Montagem m " +
            "left join m.componentes componentes " +
            "left join PrecoComponente preco on preco.componente.id = componentes.id " +
            "where " +
            "m.cliente.id = :id " +
            "group by " +
            "m.id," +
            "m.nome",
            countQuery = "Select " +
            "count(m) " +
            "from Montagem m " +
            "where " +
            "m.cliente.id = :id")
    Page<ListarMontagemPorUsuarioRequest> listarMontagensPorUsuario(Long id, Pageable pageable);
}
