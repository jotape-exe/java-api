package com.jorge.api.service;

import com.jorge.api.entity.Endereco;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EnderecoService {
    Endereco buscarEnderecoPorId(Long id);

    List<Endereco> buscarEnderecoPorTermo(String nome, Pageable pageable);

    void salvarEndereco(Endereco endereco);

    void deletarEndereco(Long id);
}
