package com.amm.finciclo.proyectofinciclo;

public class Mensaje {
    
    private int id;
    private String nombreAutor;
    private String asunto;
    private TipoUsuario tipoUsuario;
    private boolean esUrgente;
    private int idUsuario;
    private String texto;

    public Mensaje() {
    }

    public Mensaje(int id, String nombreAutor, String asunto, String texto, TipoUsuario tipoUsuario, boolean esUrgente, int idUsuario) {
        this.id = id;
        this.nombreAutor = nombreAutor;
        this.asunto = asunto;
        this.texto = texto;
        this.tipoUsuario = tipoUsuario;
        this.esUrgente = esUrgente;
        this.idUsuario = idUsuario;
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

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Mensaje{" + "id=" + id + ", nombreAutor=" + nombreAutor + ", asunto=" + asunto + ", tipoUsuario=" + tipoUsuario + ", esUrgente=" + esUrgente + '}';
    }
    
}
