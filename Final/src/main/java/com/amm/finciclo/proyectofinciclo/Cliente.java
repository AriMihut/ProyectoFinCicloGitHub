package com.amm.finciclo.proyectofinciclo;

public class Cliente {
    
    private int id;
    private String dni;
    private String nombre;
    private String sexo;
    private Long telefono;
    private String email;
    private int idServicio;

    public Cliente() {
    }

    public Cliente(int id, String dni, String nombre, String sexo, Long telefono, String email, int idServicio) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.sexo = sexo;
        this.telefono = telefono;
        this.email = email;
        this.idServicio = idServicio;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", sexo=" + sexo + ", telefono=" + telefono + ", email=" + email + '}';
    }
    
}
