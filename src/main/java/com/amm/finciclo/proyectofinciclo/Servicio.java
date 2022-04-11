package com.amm.finciclo.proyectofinciclo;

public class Servicio {
    
    private int id;
    private String tipoServicio;
    private double precio;
    private int idCliente;
    private int idEmpleado;

    public Servicio() {
    }

    public Servicio(int id, String tipoServicio, double precio, int idCliente, int idEmpleado) {
        this.id = id;
        this.tipoServicio = tipoServicio;
        this.precio = precio;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public String toString() {
       return "Servicio{" + ", tipoServicio=" + tipoServicio + '}';
    }
    
}
