package com.amm.finciclo.proyectofinciclo;

public class Tarjeta {
    
    private int id;
    private long numTarjeta;
    private int cvv;

    public Tarjeta() {
    }

    public Tarjeta(int id, long numTarjeta, int cvv) {
        this.id = id;
        this.numTarjeta = numTarjeta;
        this.cvv = cvv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(long numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "Tarjeta{" + "id=" + id + ", numTarjeta=" + numTarjeta + ", cvv=" + cvv + '}';
    }
    
}
