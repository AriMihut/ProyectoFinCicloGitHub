package com.amm.finciclo.proyectofinciclo;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class LayoutPane extends BorderPane {
    
    private Map<String, Node> pantallasApp;
    private Map<String, ControladorConNavegabilidad> controladores;
    private TipoUsuario rolUsuario;
    private Usuario usuario = null;
    private Mensaje mensaje = null;
    
    public LayoutPane(){
        this.pantallasApp = new HashMap<>();
        this.controladores = new HashMap<>();
    }
    
    public void cargarPantalla(String nombrePantalla, URL urlArchivoFxml){
        try {
            
            if(!pantallasApp.keySet().stream().anyMatch(pantalla -> pantalla.equals(nombrePantalla))){
                FXMLLoader cargadorPantallas = new FXMLLoader(urlArchivoFxml);
                Parent pantalla = cargadorPantallas.load();
            
                ControladorConNavegabilidad controladorConNavegabilidad = cargadorPantallas.getController();
                controladorConNavegabilidad.setLayout(this);
                controladores.put(nombrePantalla, controladorConNavegabilidad);
                pantallasApp.put(nombrePantalla, pantalla);
            } 
        } catch (IOException ex) {
            Logger.getLogger(LayoutPane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mostrarComoPantallaActual(String nombrePantalla){
        this.setCenter(pantallasApp.get(nombrePantalla));
    }
    
    public ControladorConNavegabilidad getCotroller(String nombrePantalla){
       return controladores.entrySet().stream().filter(controlador -> 
                controlador.getKey().equals(nombrePantalla)).findFirst().get().getValue();
    }
    
    public Node getPantalla(String nombrePantalla) {
        return pantallasApp.entrySet().stream().filter(pantalla -> 
                pantalla.getKey().equals(nombrePantalla)).findFirst().get().getValue();
    }

    public TipoUsuario getRolUsuario() {
        return rolUsuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setRolUsuario(TipoUsuario tipoUsuario) {
        this.rolUsuario = tipoUsuario;
    }

    public Mensaje getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensaje mensaje) {
        this.mensaje = mensaje;
    }
    
    
}
