package com.jorge.api.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @Column(length = 200)
    @NotEmpty
    private String logradouro;

    private int numero;

    @Column(length = 50)
    @NotEmpty
    private String cidade;

    @Column(length = 2)
    @NotEmpty
    private String estado;

    @Column(length = 100)
    @NotEmpty
    private String cep;

    @Column(length = 50)
    @NotEmpty
    private String bairro;

    public Endereco(Long id, Pessoa pessoa, String logradouro, int numero, String cidade, String estado, String cep, String bairro) {
        this.id = id;
        this.pessoa = pessoa;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.bairro = bairro;
    }

    public Endereco(Pessoa pessoa, String logradouro, int numero, String cidade, String estado, String cep, String bairro) {
        this.pessoa = pessoa;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.bairro = bairro;
    }

    public Endereco() {

    }

    public Endereco toEntity() {
        Endereco endereco = new Endereco();
        endereco.setLogradouro(this.logradouro);
        endereco.setNumero(this.numero);
        endereco.setCidade(this.cidade);
        endereco.setEstado(this.estado);
        endereco.setCep(this.cep);
        endereco.setBairro(this.bairro);
        return endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
}

