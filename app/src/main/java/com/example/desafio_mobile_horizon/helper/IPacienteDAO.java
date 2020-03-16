package com.example.desafio_mobile_horizon.helper;

import com.example.desafio_mobile_horizon.model.Paciente;

import java.util.List;

public interface IPacienteDAO {

    public boolean salvar(Paciente paciente);
    public boolean deletar(Paciente paciente);
    public List<Paciente> listar();
}
