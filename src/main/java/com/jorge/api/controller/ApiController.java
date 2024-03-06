package com.jorge.api.controller;

import com.jorge.api.dto.request.EnderecoRequest;
import com.jorge.api.dto.request.PessoaRequest;
import com.jorge.api.dto.response.EnderecoResponse;
import com.jorge.api.dto.response.PessoaResponse;
import com.jorge.api.entity.Endereco;
import com.jorge.api.entity.Pessoa;
import com.jorge.api.http.DefaultResponse;
import com.jorge.api.service.EnderecoService;
import com.jorge.api.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api-rest")
@Validated
public class ApiController {


    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private EnderecoService enderecoService;

    //Pessoa
    @PostMapping("/pessoa")
    ResponseEntity<DefaultResponse> novaPessoa(@Valid @RequestBody PessoaRequest pessoaRequest) {
        Pessoa pessoa = pessoaRequest.toEntity();
        pessoaService.salvarPessoa(pessoa);
        return new ResponseEntity<>(new DefaultResponse("Usuário criado com sucesso!", null), HttpStatus.CREATED);

    }

    @PatchMapping("/pessoa/{id}")
    ResponseEntity<DefaultResponse> atualizarPessoa(@Valid @RequestBody PessoaRequest pessoaRequest, @PathVariable Long id) {
        Pessoa pessoa = pessoaRequest.toEntity();
        pessoa.setId(id);
        pessoaService.salvarPessoa(pessoa);
        return new ResponseEntity<>(new DefaultResponse("Usuário atualizado com sucesso!", null), HttpStatus.OK);

    }

    @DeleteMapping("/pessoa/{id}")
    ResponseEntity<DefaultResponse> deletarPessoa(@PathVariable("id") Long id) {
        pessoaService.deletarPessoa(id);
        return new ResponseEntity<>(new DefaultResponse("Pessoa excluida com sucesso!", null), HttpStatus.OK);
    }

    @GetMapping("/pessoa/termo/{termo}")
    ResponseEntity<DefaultResponse> listarPessoaPorTermo(@PathVariable String termo, Pageable pageable) {
        List<PessoaResponse> responseList = new ArrayList<>();
        List<Pessoa> pessoas = new ArrayList<>();

        pessoas = pessoaService.buscarPessoaPorTermo(termo, pageable);

        pessoas.forEach(pessoa -> {
            PessoaResponse pessoaResponse = PessoaResponse.fromEntity(pessoa);
            responseList.add(pessoaResponse);
        });


        return new ResponseEntity<>(new DefaultResponse("Consulta realizada com sucesso", responseList), HttpStatus.OK);
    }

    @GetMapping("/pessoa/cpf/{cpf}")
    ResponseEntity<DefaultResponse> buscarPessoPorCPF(@PathVariable String cpf) {
        Pessoa pessoa = pessoaService.buscarPessoaPorCPF(cpf);
        PessoaResponse pessoaResponse = PessoaResponse.fromEntity(pessoa);

        return new ResponseEntity<>(new DefaultResponse("Consulta realizada com sucesso", pessoaResponse), HttpStatus.OK);
    }


    @GetMapping("/pessoa/nome/{nome}")
    ResponseEntity<DefaultResponse> buscarPessoPorNome(@PathVariable String nome, Pageable pageable) {
        List<PessoaResponse> responseList = new ArrayList<>();

        List<Pessoa> pessoas = pessoaService.buscarPessoaPorNome(nome, pageable);

        pessoas.forEach(pessoa -> {
            PessoaResponse pessoaResponse = PessoaResponse.fromEntity(pessoa);
            responseList.add(pessoaResponse);
        });


        return new ResponseEntity<>(new DefaultResponse("Consulta realizada com sucesso", responseList), HttpStatus.OK);
    }


    //Endereco
    @PostMapping("/endereco/{pessoaId}")
    ResponseEntity<DefaultResponse> novoEndereco(@Valid @RequestBody EnderecoRequest enderecoRequest, @PathVariable Long pessoaId) {
        Endereco endereco = enderecoRequest.toEntity();
        Pessoa pessoa = pessoaService.buscarPessoaPorId(pessoaId);
        endereco.setPessoa(pessoa);
        enderecoService.salvarEndereco(endereco);
        return new ResponseEntity<>(new DefaultResponse("Endereço criado com sucesso!", null), HttpStatus.CREATED);
    }

    @GetMapping("/endereco/buscar/{termo}")
    ResponseEntity<DefaultResponse> buscarEnderecoPorTermo(@PathVariable String termo, Pageable pageable) {
        List<EnderecoResponse> responseList = new ArrayList<>();
        List<Endereco> enderecos = enderecoService.buscarEnderecoPorTermo(termo, pageable);
        enderecos.forEach(endereco -> {
            PessoaResponse pessoaResponse = PessoaResponse.fromEntity(endereco.getPessoa());
            responseList.add(new EnderecoResponse(endereco.getId(), pessoaResponse, endereco.getLogradouro(), endereco.getNumero(), endereco.getCidade(), endereco.getEstado(), endereco.getCep(), endereco.getBairro()));
        });

        return new ResponseEntity<>(new DefaultResponse("Consulta realizada com sucesso!", responseList), HttpStatus.OK);
    }

    @PatchMapping("/endereco/{id}")
    ResponseEntity<DefaultResponse> atualizarEndereco(@Valid @RequestBody EnderecoRequest enderecoRequest, @PathVariable Long id) {
        Endereco enderecoToUpdate = enderecoRequest.toEntity();
        Endereco endereco = enderecoService.buscarEnderecoPorId(id);
        enderecoToUpdate.setId(id);
        enderecoToUpdate.setPessoa(endereco.getPessoa());
        enderecoService.salvarEndereco(enderecoToUpdate);
        return new ResponseEntity<>(new DefaultResponse("Endereço atualizado com sucesso!", null), HttpStatus.OK);
    }

    @DeleteMapping("/endereco/{id}")
    ResponseEntity<DefaultResponse> deletarEndereco(@PathVariable("id") Long id) {
        enderecoService.deletarEndereco(id);
        return new ResponseEntity<>(new DefaultResponse("Endereco excluido com sucesso!", null), HttpStatus.OK);
    }

}
