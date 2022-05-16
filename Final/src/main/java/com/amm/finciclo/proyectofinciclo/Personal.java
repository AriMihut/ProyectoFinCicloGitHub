package com.amm.finciclo.proyectofinciclo;

import java.util.Date;

public class Personal {
    
    private int id;
    private String dni;
    private String nombre;
    private Date fechaAlta;
    private Date fechaBaja;
    private double sueldo;
    private int idServicio;
    
    public Personal(){}

    public Personal(int id, String dni, String nombre, Date fechaAlta, Date fechaBaja, double sueldo, int idServicio) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.sueldo = sueldo;
        this.idServicio = idServicio;
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

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
