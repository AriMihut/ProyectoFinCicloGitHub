package com.amm.finciclo.proyectofinciclo;

public class Mensaje {
    
    private int id;
    private int idAutor;
    private String asunto;
    private TipoUsuario tipoUsuario;
    private boolean esUrgente;
    private int idDestinatario;
    private String texto;

    public Mensaje() {
    }
    
    public Mensaje(String texto){
        this.texto = texto;
    }

    public Mensaje(int id, int idAutor, String asunto, TipoUsuario tipoUsuario, boolean esUrgente, int idDestinatario, String texto) {
        this.id = id;
        this.idAutor = idAutor;
        this.asunto = asunto;
        this.tipoUsuario = tipoUsuario;
        this.esUrgente = esUrgente;
        this.idDestinatario = idDestinatario;
        this.texto = texto;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
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

    public boolean isEsUrgente() {
        return esUrgente;
    }

    public void setEsUrgente(boolean esUrgente) {
        this.esUrgente = esUrgente;
    }

    public int getIdDestinatario() {
        return idDestinatario;
    }

    public void setIdDestinatario(int idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "Mensaje{" + "id=" + id + ", idAutor=" + idAutor + ", asunto=" + asunto + ", tipoUsuario=" + tipoUsuario + ", esUrgente=" + esUrgente + ", idDestinatario=" + idDestinatario + ", texto=" + texto + '}';
    }
    
}
