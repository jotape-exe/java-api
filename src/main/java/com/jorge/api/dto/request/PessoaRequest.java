package com.jorge.api.dto.request;

import com.jorge.api.entity.Pessoa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.Date;

public class PessoaRequest {

    @NotBlank
    private String nome;

    private String rg;

    @NotBlank
    @CPF(message = "CPF inválido")
    private String cpf;

    @NotNull
    private LocalDate dataNascimento;

    private String telefone;

    @NotBlank
    private String nomeMae;

    private String nomePai;

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

    public Pessoa toEntity() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(this.nome);
        pessoa.setRg(this.rg);
        pessoa.setCpf(this.cpf);
        pessoa.setDataNascimento(this.dataNascimento);
        pessoa.setTelefone(this.telefone);
        pessoa.setNomeMae(this.nomeMae);
        pessoa.setNomePai(this.nomePai);
        return pessoa;
    }
}
