package com.jorge.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60, nullable = false)
    @NotBlank
    private String nome;

    @Column(length = 10)
    private String rg;

    @Column(length = 11, nullable = false, unique = true)
    @NotBlank
    @CPF(message = "CPF inválido")
    private String cpf;

    @Column(nullable = false)
    @NotNull
    private LocalDate dataNascimento;

    @Column(length = 11)
    private String telefone;

    @Column(length = 60, nullable = false, unique = true)
    @NotBlank
    private String nomeMae;

    @Column(length = 60)
    private String nomePai;

    @Column(nullable = false, updatable = false)
    @NotNull
    private LocalDate dataCadastro = LocalDate.now();

    public Pessoa() {

    }

    public Pessoa(Long id, String nome, String rg, String cpf, LocalDate dataNascimento, String telefone, String nomeMae, String nomePai, LocalDate dataCadastro) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
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

}
