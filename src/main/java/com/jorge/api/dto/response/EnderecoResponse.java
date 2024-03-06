package com.jorge.api.dto.response;

import com.jorge.api.entity.Endereco;


public class EnderecoResponse {

    private Long id;

    private PessoaResponse pessoa;

    private String logradouro;

    private int numero;

    private String cidade;

    private String estado;

    private String cep;

    private String bairro;

    public EnderecoResponse(){

    }

    public EnderecoResponse(Long id, PessoaResponse pessoa, String logradouro, int numero, String cidade, String estado, String cep, String bairro) {
        this.id = id;
        this.pessoa = pessoa;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.bairro = bairro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PessoaResponse getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaResponse pessoa) {
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

    public static EnderecoResponse fromEntity(Endereco endereco) {
        EnderecoResponse response = new EnderecoResponse();
        response.setId(endereco.getId());
        response.setPessoa(PessoaResponse.fromEntity(endereco.getPessoa()));
        response.setLogradouro(endereco.getLogradouro());
        response.setNumero(endereco.getNumero());
        response.setCidade(endereco.getCidade());
        response.setEstado(endereco.getEstado());
        response.setCep(endereco.getCep());
        response.setBairro(endereco.getBairro());
        return response;
    }
}
