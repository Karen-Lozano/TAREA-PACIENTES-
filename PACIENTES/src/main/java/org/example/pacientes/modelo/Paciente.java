package org.example.pacientes.modelo;

import java.time.LocalDate;

public class Paciente {

    private String cedula;
    private String nombres;
    private String apellidos;
    private LocalDate fechaNac;
    private String genero;
    private String telefono;
    private String email;
    private String tipoSangre;
    private String alergias;
    private boolean activo;

    // Constructor vacío
    public Paciente() {
    }

    // Constructor con todos los atributos
    public Paciente(String cedula,
                    String nombres,
                    String apellidos,
                    LocalDate fechaNac,
                    String genero,
                    String telefono,
                    String email,
                    String tipoSangre,
                    String alergias,
                    boolean activo) {

        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
        this.genero = genero;
        this.telefono = telefono;
        this.email = email;
        this.tipoSangre = tipoSangre;
        this.alergias = alergias;
        this.activo = activo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return nombres + " " + apellidos;
    }

}