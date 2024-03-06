package com.jorge.api.service.impl;

import com.jorge.api.entity.Pessoa;
import com.jorge.api.repository.PessoaRepository;
import com.jorge.api.service.PessoaService;
import com.jorge.api.service.exceptions.EmptyListException;
import com.jorge.api.service.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository repository;

    @Override
    @Transactional
    public void salvarPessoa(Pessoa pessoa) {
        repository.save(pessoa);
    }

    @Override
    public void deletarPessoa(Long id) {
        buscarPessoaPorId(id);
        repository.deleteById(id);
    }

    @Override
    public Pessoa buscarPessoaPorId(Long id) {
        Optional<Pessoa> pessoa = repository.findById(id);
        return pessoa.orElseThrow(() -> new ObjectNotFoundException("Pessoa não encontrada"));
    }

    @Override
    public Pessoa buscarPessoaPorCPF(String cpf) {
        Optional<Pessoa> pessoa = repository.findByCpf(cpf);
        return pessoa.orElseThrow(() -> new ObjectNotFoundException("Pessoa não encontrada"));
    }

    @Override
    public List<Pessoa> buscarPessoaPorNome(String nome, Pageable pageable) {
        List<Pessoa> pessoas = repository.findByNome(nome, pageable);
        if (pessoas.isEmpty()) {
            throw new EmptyListException("Pessoa não encontrada");
        }

        return pessoas;
    }

    @Override
    public List<Pessoa> buscarPessoaPorTermo(String termo, Pageable pageable) {
        List<Pessoa> pessoas = repository.findByTermoBusca(termo.toLowerCase(), pageable);
        if (pessoas.isEmpty()) {
            throw new EmptyListException("Pessoa não encontrada");
        }
        return pessoas;
    }
}
