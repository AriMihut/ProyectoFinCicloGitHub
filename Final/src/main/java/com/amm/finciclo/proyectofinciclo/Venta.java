package com.amm.finciclo.proyectofinciclo;

import java.util.ArrayList;
import java.util.Date;

public class Venta {
    
    private String codigoConjunto;
    private int idUsuario;
    private String nombreUsuario;
    private ArrayList<String> nombreServicios;
    private Date fechaVenta;
    private double valorTotalVenta;
    private int idServicio;

    public Venta() {
    }

    public Venta(String codigoConjunto, Date fechaVenta, double valorTotalVenta, int idUsuario, String nombreUsuario) {
        this.codigoConjunto = codigoConjunto;
        this.idUsuario = idUsuario;
         this.nombreUsuario = nombreUsuario;
        this.nombreServicios = new ArrayList<>();
        this.fechaVenta = fechaVenta;
        this.valorTotalVenta = valorTotalVenta;
       
    }
    
    public Venta(int idUsuario, String codigoConjunto, int idServicio, double valorTotalVenta) {
        this.idUsuario = idUsuario;
        this.codigoConjunto = codigoConjunto;
        this.idServicio = idServicio;
        this.valorTotalVenta = valorTotalVenta;
       
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

    public ArrayList<String> getNombreServicios() {
        return nombreServicios;
    }

    public void setNombreServicios(ArrayList<String> nombreServicio) {
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
