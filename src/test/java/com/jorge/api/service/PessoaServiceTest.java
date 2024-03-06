package com.jorge.api.service;

import com.jorge.api.entity.Pessoa;
import com.jorge.api.repository.PessoaRepository;
import com.jorge.api.service.exceptions.EmptyListException;
import com.jorge.api.service.exceptions.ObjectNotFoundException;
import com.jorge.api.service.impl.PessoaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PessoaServiceTest {

    @Mock
    private PessoaRepository repository;

    @InjectMocks
    private PessoaServiceImpl service;

    @Test
    void testSalvarPessoa() {
        Pessoa pessoa = new Pessoa();

        service.salvarPessoa(pessoa);

        verify(repository, times(1)).save(pessoa);
    }

    @Test
    void testDeletarPessoa() {
        Long id = 1L;
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(pessoa));

        service.deletarPessoa(id);

        verify(repository, times(1)).deleteById(id);
    }

    @Test
    void testDeletarPessoaNaoExistente() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> service.deletarPessoa(id));
    }

    @Test
    void testBuscarPessoaPorIdExistente() {
        // Dado
        Long id = 1L;
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(pessoa));

        Pessoa result = service.buscarPessoaPorId(id);

        assertEquals(id, result.getId());
    }

    @Test
    void testBuscarPessoaPorIdNaoExistente() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> service.buscarPessoaPorId(id));
    }

    @Test
    void testBuscarPessoaPorCPFExistente() {
        // Dado
        String cpf = "12345678900";
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf(cpf);
        when(repository.findByCpf(cpf)).thenReturn(Optional.of(pessoa));

        Pessoa result = service.buscarPessoaPorCPF(cpf);

        assertEquals(cpf, result.getCpf());
    }

    @Test
    void testBuscarPessoaPorCPFNaoExistente() {
        String cpf = "12345678900";
        when(repository.findByCpf(cpf)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> service.buscarPessoaPorCPF(cpf));
    }

    @Test
    void testBuscarPessoaPorNomeComResultado() {
        String nome = "Fulano";
        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(new Pessoa());
        PageRequest pageable = PageRequest.of(0, 10);
        //Pessoa pessoa = new Pessoa();
        when(repository.findByNome(nome, pageable))
                .thenReturn(pessoas);

        List<Pessoa> result = service.buscarPessoaPorNome(nome, pageable);

        assertFalse(result.isEmpty());
    }

    @Test
    void testBuscarPessoaPorNomeSemResultado() {
        String nome = "y";
        List<Pessoa> pessoas = new ArrayList<>();

        PageRequest pageable = PageRequest.of(0, 10);
        when(repository.findByNome(nome, pageable))
                .thenReturn(pessoas);

        assertThrows(EmptyListException.class, () -> service.buscarPessoaPorNome(nome, pageable));
    }

    @Test
    void testBuscarPessoaPorTermoComResultado() {
        String termo = "yarn";
        PageRequest pageable = PageRequest.of(0, 10);
        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(new Pessoa(1L, "Fulano 2", "123456", "12345678901", LocalDate.of(2000, 1, 1), "123456789", "Mãe do Fulano", "Pai do Fulano", LocalDate.now()));
        when(repository.findByTermoBusca(termo.toLowerCase(), pageable))
                .thenReturn(pessoas);

        List<Pessoa> result = service.buscarPessoaPorTermo(termo, pageable);

        assertFalse(result.isEmpty());
    }

    @Test
    void testBuscarPessoaPorTermoSemResultado() {
        String termo = "y";
        List<Pessoa> pessoas = new ArrayList<>();
        PageRequest pageable = PageRequest.of(0, 10);
        when(repository.findByTermoBusca(termo.toLowerCase(), pageable))
                .thenReturn(pessoas);


        assertThrows(EmptyListException.class, () -> service.buscarPessoaPorTermo(termo, pageable));
    }
}
