package com.amm.finciclo.proyectofinciclo;

import java.util.Date;

public class Empleado {
    
    private int id;
    private String dni;
    private String nombre;
    private String apellido;
    private Date fechaAlta;
    private Date fechaBaja;
    private int idServicioOfrecido;
    private int idDepartamento;
    private boolean esEncargado;
    
    public Empleado(){}

    public Empleado(int id, String dni, String nombre, String apellido, Date fechaAlta, Date fechaBaja, int idServicioOfrecido, int idDepartamento, boolean esEncargado) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.idServicioOfrecido = idServicioOfrecido;
        this.idDepartamento = idDepartamento;
        this.esEncargado = esEncargado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public int getIdServicioOfrecido() {
        return idServicioOfrecido;
    }

    public void setIdServicioOfrecido(int idServicioOfrecido) {
        this.idServicioOfrecido = idServicioOfrecido;
    }
    
    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public boolean getEsEncargado() {
        return esEncargado;
    }

    public void setEsEncargado(boolean esEncargado) {
        this.esEncargado = esEncargado;
    }
    
    
    
}
