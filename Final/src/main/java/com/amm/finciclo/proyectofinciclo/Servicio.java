package com.amm.finciclo.proyectofinciclo;

import java.util.Objects;

public class Servicio {
    
    private int id;
    public enum TipoServicio {CEREMONIA, GASTRONOMIA, MUSICA, FOTOGRAFIA, VIDEO, TRANSPORTE};
    public TipoServicio tipoServicio;
    private String nombreServicio;
    private double precio;
    private int cantidad;

    public Servicio() {
    }
    
    public Servicio(String nombreServicio, double precio, int cantidad){
        this.nombreServicio = nombreServicio;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public Servicio(int id, TipoServicio tipoServicio, String nombreServicio, double precio) {
        this.id = id;
        this.tipoServicio = tipoServicio;
        this.nombreServicio = nombreServicio;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.tipoServicio);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.precio) ^ (Double.doubleToLongBits(this.precio) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Servicio other = (Servicio) obj;
        return true;
    }

    @Override
    public String toString() {
        return "Servicio: " + nombreServicio + " " + "Precio: " + precio;
    }

    
}
