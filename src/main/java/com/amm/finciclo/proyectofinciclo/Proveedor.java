
package com.amm.finciclo.proyectofinciclo;

public class Proveedor {
    
    private int id;
    private String nombre;
    private String apellido;
    private int idProductoOfrecido;
    
    public Proveedor(){}

    public Proveedor(int id, String nombre, String apellido, int idProductoOfrecido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.idProductoOfrecido = idProductoOfrecido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getIdProductoOfrecido() {
        return idProductoOfrecido;
    }

    public void setIdProductoOfrecido(int idProductoOfrecido) {
        this.idProductoOfrecido = idProductoOfrecido;
    }
    
}
