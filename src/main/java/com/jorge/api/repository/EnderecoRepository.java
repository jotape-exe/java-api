package com.jorge.api.repository;

import com.jorge.api.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    @Query("SELECT e FROM Endereco e WHERE lower(e.logradouro) LIKE CONCAT('%', :termo, '%')" +
            "OR lower(e.cidade) LIKE CONCAT('%', :termo, '%')" +
            "OR lower(e.estado) LIKE CONCAT('%', :termo, '%')" +
            "OR lower(e.bairro) LIKE CONCAT('%', :termo, '%')" +
            "OR lower(e.cep) LIKE CONCAT('%', :termo, '%')")
    List<Endereco> findEnderecoByTermo(String termo, Pageable pageable);
}

