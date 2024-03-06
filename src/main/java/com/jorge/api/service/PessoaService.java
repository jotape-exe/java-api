package com.jorge.api.service;

import com.jorge.api.entity.Pessoa;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PessoaService {
    void salvarPessoa(Pessoa pessoa);

    void deletarPessoa(Long id);

    Pessoa buscarPessoaPorId(Long id);

    Pessoa buscarPessoaPorCPF(String cpf);

    List<Pessoa> buscarPessoaPorNome(String nome,  Pageable pageable);

    List<Pessoa> buscarPessoaPorTermo(String termo,  Pageable pageable);
}
