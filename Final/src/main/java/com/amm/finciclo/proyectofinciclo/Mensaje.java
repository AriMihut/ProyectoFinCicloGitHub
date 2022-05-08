package com.amm.finciclo.proyectofinciclo;

public class Mensaje {
    
    private int id;
    private int idAutor;
    private String nombreAutor;
    private String asunto;
    private TipoUsuario tipoUsuario;
    private boolean esUrgente;
    private boolean esLeido;
    private int idDestinatario;
    private String texto;

    public Mensaje() {
    }
    
    public Mensaje(String texto){
        this.texto = texto;
    }

    public Mensaje(int id, int idAutor, String nombreAutor, String asunto, TipoUsuario tipoUsuario, 
        boolean esUrgente, boolean esLeido, int idDestinatario, String texto) {
        this.id = id;
        this.idAutor = idAutor;
        this.nombreAutor = nombreAutor;
        this.asunto = asunto;
        this.tipoUsuario = tipoUsuario;
        this.esUrgente = esUrgente;
        this.esLeido = esLeido;
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

    public boolean getEsLeido() {
        return esLeido;
    }

    public void setEsLeido(boolean esLeido) {
        this.esLeido = esLeido;
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
        return "Mensaje{" + "id=" + id + ", idAutor=" + idAutor + ", nombreAutor=" + nombreAutor + ", asunto=" + asunto + ", tipoUsuario=" + tipoUsuario + ", esUrgente=" + esUrgente + ", esLeido=" + esLeido + ", idDestinatario=" + idDestinatario + ", texto=" + texto + '}';
    }
    
}
