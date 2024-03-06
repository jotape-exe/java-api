package com.jorge.api.dto.response;

import com.jorge.api.entity.Pessoa;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public class PessoaResponse {

    private Long id;

    private String nome;

    private String rg;

    @CPF
    private String cpf;

    private LocalDate dataNascimento;

    private String telefone;

    private String nomeMae;

    private String nomePai;

    private LocalDate dataCadastro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(
            LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public PessoaResponse(Long id, String nome, String rg, String cpf, LocalDate dataNascimento, String telefone, String nomeMae, String nomePai, LocalDate dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.nomeMae = nomeMae;
        this.nomePai = nomePai;
        this.dataCadastro = dataCadastro;
    }

    public PessoaResponse() {

    }
    public static PessoaResponse fromEntity(Pessoa pessoa) {
        PessoaResponse response = new PessoaResponse();
        response.setId(pessoa.getId());
        response.setNome(pessoa.getNome());
        response.setRg(pessoa.getRg());
        response.setCpf(pessoa.getCpf());
        response.setDataNascimento(pessoa.getDataNascimento());
        response.setTelefone(pessoa.getTelefone());
        response.setNomeMae(pessoa.getNomeMae());
        response.setNomePai(pessoa.getNomePai());
        response.setDataCadastro(pessoa.getDataCadastro());
        return response;
    }
}
