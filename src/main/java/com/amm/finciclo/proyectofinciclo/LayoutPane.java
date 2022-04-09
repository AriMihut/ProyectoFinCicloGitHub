package com.amm.finciclo.proyectofinciclo;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class LayoutPane extends BorderPane {
    
    private Map<String, Node> pantallasApp;
    private Map<String, ControladorConNavegabilidad> controladores;
    private String rolUsuario;
    
    public LayoutPane(){
        this.pantallasApp = new HashMap<>();
        this.controladores = new HashMap<>();
    }
    
    public void cargarPantalla(String nombrePantalla, URL urlArchivoFxml) throws IOException{
       FXMLLoader cargadorPantallas = new FXMLLoader(urlArchivoFxml);
       Parent pantalla = cargadorPantallas.load();
       
       ControladorConNavegabilidad controladorConNavegabilidad = cargadorPantallas.getController();
       controladorConNavegabilidad.setLayout(this);
       controladores.put(nombrePantalla, controladorConNavegabilidad);
       pantallasApp.put(nombrePantalla, pantalla);
    }
    
    public void mostrarComoPantallaActual(String nombrePantalla){
        this.setCenter(pantallasApp.get(nombrePantalla));
    }
    
    public ControladorConNavegabilidad getCotroller(String nombrePantalla){
       return controladores.entrySet().stream().filter(controlador -> 
                controlador.getKey().equals(nombrePantalla)).findFirst().get().getValue();
    }

    public String getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }
    
}
