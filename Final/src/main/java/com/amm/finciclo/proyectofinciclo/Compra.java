package com.amm.finciclo.proyectofinciclo;

public class Compra {
    
    private int idServicio;
    private double precioTotal;
    private int capacidadCesta;
    private Servicio servicio;

    public Compra() {
    }

    public Compra(int idServicio, double precioTotal, int capacidadCesta) {
        this.idServicio = idServicio;
        this.precioTotal = precioTotal;
        this.capacidadCesta = capacidadCesta;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public int getCapacidadCesta() {
        return capacidadCesta;
    }

    public void setCapacidadCesta(int capacidadCesta) {
        this.capacidadCesta = capacidadCesta;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.precioTotal) ^ (Double.doubleToLongBits(this.precioTotal) >>> 32));
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
        final Compra other = (Compra) obj;
        return true;
    }

    @Override
    public String toString() {
        return "Compra{" + "precioTotal=" + precioTotal + '}';
    }
    
}
