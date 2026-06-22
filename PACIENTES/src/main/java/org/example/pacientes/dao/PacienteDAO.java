package org.example.pacientes.dao;

import org.example.pacientes.modelo.Paciente;

import java.util.List;

public interface PacienteDAO {

    void insertar(Paciente p);

    List<Paciente> listar();

    void actualizar(Paciente p);

    void eliminar(String cedula);

    Paciente buscarPorCedula(String cedula);
}