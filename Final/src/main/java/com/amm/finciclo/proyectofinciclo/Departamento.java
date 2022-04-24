package com.amm.finciclo.proyectofinciclo;

public class Departamento {
    
    private int idDepartamento;
    private String nombreDepartamento;
    private int idPersonal;

    public Departamento(int idDepartamento, String nombreDepartamento, int idPersonal) {
        this.idDepartamento = idDepartamento;
        this.nombreDepartamento = nombreDepartamento;
        this.idPersonal = idPersonal;
    }

    public Departamento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }
    
    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    @Override
    public String toString() {
        return nombreDepartamento;
    }
    
}
