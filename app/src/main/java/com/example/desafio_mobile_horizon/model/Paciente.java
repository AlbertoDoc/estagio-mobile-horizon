package com.example.desafio_mobile_horizon.model;

import java.io.Serializable;

public class Paciente implements Serializable {
    private Long id;
    private String nomePaciente;
    private int idade;
    private double temperaturaCorporal;
    private int diasTosse;
    private int diasDorCabeca;
    private int semanaItalia;
    private int semanaChina;
    private int semanaIndonesia;
    private int semanaPortugal;
    private int semanaEUA;
    private String situacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getTemperaturaCorporal() {
        return temperaturaCorporal;
    }

    public void setTemperaturaCorporal(double temperaturaCorporal) {
        this.temperaturaCorporal = temperaturaCorporal;
    }

    public int getDiasTosse() {
        return diasTosse;
    }

    public void setDiasTosse(int diasTosse) {
        this.diasTosse = diasTosse;
    }

    public int getDiasDorCabeca() {
        return diasDorCabeca;
    }

    public void setDiasDorCabeca(int diasDorCabeca) {
        this.diasDorCabeca = diasDorCabeca;
    }

    public int getSemanaItalia() {
        return semanaItalia;
    }

    public void setSemanaItalia(int semanaItalia) {
        this.semanaItalia = semanaItalia;
    }

    public int getSemanaChina() {
        return semanaChina;
    }

    public void setSemanaChina(int semanaChina) {
        this.semanaChina = semanaChina;
    }

    public int getSemanaIndonesia() {
        return semanaIndonesia;
    }

    public void setSemanaIndonesia(int semanaIndonesia) {
        this.semanaIndonesia = semanaIndonesia;
    }

    public int getSemanaPortugal() {
        return semanaPortugal;
    }

    public void setSemanaPortugal(int semanaPortugal) {
        this.semanaPortugal = semanaPortugal;
    }

    public int getSemanaEUA() {
        return semanaEUA;
    }

    public void setSemanaEUA(int semanaEUA) {
        this.semanaEUA = semanaEUA;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
