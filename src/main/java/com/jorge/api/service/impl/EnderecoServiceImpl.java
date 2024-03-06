package com.jorge.api.service.impl;

import com.jorge.api.entity.Endereco;
import com.jorge.api.repository.EnderecoRepository;
import com.jorge.api.service.EnderecoService;
import com.jorge.api.service.exceptions.EmptyListException;
import com.jorge.api.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    @Override
    public Endereco buscarEnderecoPorId(Long id) {
        Optional<Endereco> endereco = this.repository.findById(id);
        return endereco.orElseThrow(() -> new ObjectNotFoundException("Endereco não encontrada"));
    }

    @Override
    public List<Endereco> buscarEnderecoPorTermo(String termo, Pageable pageable) {
        List<Endereco> enderecos = this.repository.findEnderecoByTermo(termo.toLowerCase(), pageable);

        if (enderecos.isEmpty()) {
            throw new EmptyListException("Nenhum endereço encontrado");
        }
        return enderecos;
    }

    @Override
    public void salvarEndereco(Endereco endereco) {
        this.repository.save(endereco);
    }

    @Override
    public void deletarEndereco(Long id) {
        Endereco endereco = buscarEnderecoPorId(id);
        this.repository.delete(endereco);
    }
}
