package com.jorge.api.repository;

import com.jorge.api.entity.Pessoa;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("SELECT p FROM Pessoa p WHERE " +
            "lower(p.nome) LIKE CONCAT('%', :nome, '%') ")
    List<Pessoa> findByNome(String nome, Pageable pageable);

    Optional<Pessoa> findByCpf(String cpf);

    @Query("SELECT p FROM Pessoa p WHERE " +
            "lower(p.nome) LIKE CONCAT('%', :termoBusca, '%') " +
            "OR lower(p.nomeMae) LIKE CONCAT('%', :termoBusca, '%')" +
            "OR LOWER(FUNCTION('FORMATDATETIME', p.dataNascimento, 'yyyy-MM-dd')) LIKE CONCAT('%', :termoBusca, '%')")
    List<Pessoa> findByTermoBusca(@Param("termoBusca") String termoBusca, Pageable pageable);



}

