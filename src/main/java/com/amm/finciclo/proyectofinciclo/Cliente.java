package com.amm.finciclo.proyectofinciclo;

public class Cliente {
    
    private int id;
    private String dni;
    private String nombre;
    private String apellido;
    private int idServicioContratado;
    private int idProductoComprado;
    private int pagoRealizado;
    
    public Cliente(){}

    public Cliente(int id, String dni, String nombre, String apellido, int idServicioContratado, int idProductoComprado, int pagoRealizado) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.idServicioContratado = idServicioContratado;
        this.idProductoComprado = idProductoComprado;
        this.pagoRealizado = pagoRealizado;
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

    public int getIdServicioContratado() {
        return idServicioContratado;
    }

    public void setIdServicioContratado(int idServicioContratado) {
        this.idServicioContratado = idServicioContratado;
    }

    public int getIdProductoComprado() {
        return idProductoComprado;
    }

    public void setIdProductoComprado(int idProductoComprado) {
        this.idProductoComprado = idProductoComprado;
    }

    public int getPagoRealizado() {
        return pagoRealizado;
    }

    public void setPagoRealizado(int pagoRealizado) {
        this.pagoRealizado = pagoRealizado;
    }
    
}
