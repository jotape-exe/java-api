package com.jorge.api.dto.request;

import com.jorge.api.entity.Endereco;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class EnderecoRequest {


    @NotEmpty
    private String logradouro;

    private int numero;

    @NotEmpty
    private String cidade;

    @NotEmpty
    private String estado;

    @NotEmpty
    private String cep;

    @NotEmpty
    private String bairro;


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

}
