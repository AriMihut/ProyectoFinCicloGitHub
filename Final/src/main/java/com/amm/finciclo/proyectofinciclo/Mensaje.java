package com.amm.finciclo.proyectofinciclo;

public class Mensaje {
    
    private int id;
    private String nombreAutor;
    private String asunto;
    private TipoUsuario tipoUsuario;
    private boolean esUrgente;

    public Mensaje() {
    }

    public Mensaje(int id, String nombreAutor, String asunto, TipoUsuario tipoUsuario, boolean esUrgente) {
        this.id = id;
        this.nombreAutor = nombreAutor;
        this.asunto = asunto;
        this.tipoUsuario = tipoUsuario;
        this.esUrgente = esUrgente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public boolean getEsUrgente() {
        return esUrgente;
    }

    public void setEsUrgente(boolean esUrgente) {
        this.esUrgente = esUrgente;
    }

    @Override
    public String toString() {
        return "Mensaje{" + "id=" + id + ", nombreAutor=" + nombreAutor + ", asunto=" + asunto + ", tipoUsuario=" + tipoUsuario + ", esUrgente=" + esUrgente + '}';
    }
    
}
