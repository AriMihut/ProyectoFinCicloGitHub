package com.amm.finciclo.proyectofinciclo;

import java.util.Date;
import java.util.HashSet;


public class Venta {
    
    private String codigoConjunto;
    private int idUsuario;
    private String nombreUsuario;
    private HashSet<String> nombreServicios;
    private Date fechaVenta;
    private double valorTotalVenta;
    private int idServicio;

    public Venta() {
    }

    public Venta(String codigoConjunto, Date fechaVenta, double valorTotalVenta, int idUsuario, String nombreUsuario, String nombreServicio, int idServicio) {
        this.codigoConjunto = codigoConjunto;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.nombreServicios = new HashSet<>();
        this.fechaVenta = fechaVenta;
        this.valorTotalVenta = valorTotalVenta;
        this.nombreServicios.add(nombreServicio);
        this.idServicio = idServicio;
       
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getCodigoConjunto() {
        return codigoConjunto;
    }

    public void setCodigoConjunto(String codigoConjunto) {
        this.codigoConjunto = codigoConjunto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public HashSet<String> getNombreServicios() {
        return nombreServicios;
    }

    public void setNombreServicios(HashSet<String> nombreServicio) {
        this.nombreServicios = nombreServicio;
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
        return "Venta{" + "codigoConjunto=" + codigoConjunto + ", idUsuario=" + idUsuario + ", nombreUsuario=" + nombreUsuario + ", nombreServicios=" + nombreServicios + ", fechaVenta=" + fechaVenta + ", valorTotalVenta=" + valorTotalVenta + '}';
    }
    
}
