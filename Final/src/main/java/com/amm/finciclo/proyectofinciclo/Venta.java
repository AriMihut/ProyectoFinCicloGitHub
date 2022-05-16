package com.amm.finciclo.proyectofinciclo;

import java.util.Date;

public class Venta {
    
    private int id;
    private int idConjunto;
    private int idUsuario;
    private int idServicio;
    private Date fechaVenta;
    private double valorTotalVenta;

    public Venta() {
    }

    public Venta(int id,int idConjunto, int idUsuario, int idServicio, Date fechaVenta, double valorTotalVenta) {
        this.id = id;
        this.idConjunto = idConjunto;
        this.idUsuario = idUsuario;
        this.idServicio = idServicio;
        this.fechaVenta = fechaVenta;
        this.valorTotalVenta = valorTotalVenta;
    }
    
    public Venta(int idUsuario, int idServicio, double valorTotalVenta) {
        this.idUsuario = idUsuario;
        this.idServicio = idServicio;
        this.valorTotalVenta = valorTotalVenta;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdConjunto() {
        return idConjunto;
    }

    public void setIdConjunto(int idConjunto) {
        this.idConjunto = idConjunto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public double getValorTotalVenta() {
        return valorTotalVenta;
    }

    public void setValorTotalVenta(double valorTotalVenta) {
        this.valorTotalVenta = valorTotalVenta;
    }

    @Override
    public String toString() {
        return "Venta{" + "id=" + id + ", idUsuario=" + idUsuario + ", idServicio=" + idServicio + ", fechaVenta=" + fechaVenta + ", valorTotalVenta=" + valorTotalVenta + '}';
    }
    
}
