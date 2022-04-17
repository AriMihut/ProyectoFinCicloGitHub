package com.amm.finciclo.proyectofinciclo;

public class Cliente {
    
    private int id;
    private String dni;
    private String nombre;
    //public enum Sexo{Mujer, Hombre};
    //public Sexo sexo;
    private String sexo;
    private Long telefono;
    private String email;
    private String tipoServicio;
    //private enum tipoServicio {Ceremonia, Gastronomia, Musica, Fotografia, Video, Transporte};

    public Cliente() {
    }

    public Cliente(int id, String dni, String nombre, String sexo, Long telefono, String email, String tipoServicio) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.sexo = sexo;
        this.telefono = telefono;
        this.email = email;
        this.tipoServicio = tipoServicio;
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

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", sexo=" + sexo + ", telefono=" + telefono + ", email=" + email + '}';
    }
    
}
