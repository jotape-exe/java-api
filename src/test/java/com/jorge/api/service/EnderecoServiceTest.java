package com.jorge.api.service;

import com.jorge.api.entity.Endereco;
import com.jorge.api.entity.Pessoa;
import com.jorge.api.repository.EnderecoRepository;
import com.jorge.api.service.exceptions.EmptyListException;
import com.jorge.api.service.exceptions.ObjectNotFoundException;
import com.jorge.api.service.impl.EnderecoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnderecoServiceTest {

    @Mock
    private EnderecoRepository repository;

    @InjectMocks
    private EnderecoServiceImpl service;

    @Test
    void testBuscarEnderecoPorIdExistente() {
        Long id = 1L;
        Endereco endereco = new Endereco();
        endereco.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(endereco));

        Endereco result = service.buscarEnderecoPorId(id);

        assertEquals(id, result.getId());
    }

    @Test
    void testBuscarEnderecoPorIdNaoExistente() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> service.buscarEnderecoPorId(id));
    }

    @Test
    void testBuscarEnderecoPorTermoComResultado() {
        String termo = "rua";
        List<Endereco> enderecos = new ArrayList<>();
        Pessoa pessoa = new Pessoa();

        Endereco endereco = new Endereco(pessoa, "Rua Fictícia", 123, "Cidade Fictícia", "Estado Fictício", "12345-678", "Bairro Fictício");

        PageRequest pageable = PageRequest.of(0, 10);
        enderecos.add(endereco);
        when(repository.findEnderecoByTermo(termo.toLowerCase(), pageable)).thenReturn(enderecos);

        List<Endereco> result = service.buscarEnderecoPorTermo(termo, pageable);

        assertFalse(result.isEmpty());
    }

    @Test
    void testBuscarEnderecoPorTermoSemResultado() {
        String termo = "rua";
        List<Endereco> enderecos = new ArrayList<>();
        PageRequest pageable = PageRequest.of(0, 10);
        when(repository.findEnderecoByTermo(termo.toLowerCase(), pageable))
                .thenReturn(enderecos);

        assertThrows(EmptyListException.class, () -> service.buscarEnderecoPorTermo(termo, pageable));
    }

    @Test
    void testSalvarEndereco() {
        Endereco endereco = new Endereco();

        service.salvarEndereco(endereco);

        verify(repository, times(1)).save(endereco);
    }

    @Test
    void testDeletarEndereco() {
        Long id = 1L;
        Endereco endereco = new Endereco();
        endereco.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(endereco));

        service.deletarEndereco(id);

        verify(repository, times(1)).delete(endereco);
    }

    @Test
    void testDeletarEnderecoNaoExistente() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> service.deletarEndereco(id));
    }
}

