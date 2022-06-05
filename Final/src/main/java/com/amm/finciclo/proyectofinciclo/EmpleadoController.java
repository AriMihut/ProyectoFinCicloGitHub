package com.amm.finciclo.proyectofinciclo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EmpleadoController extends ControladorConNavegabilidad implements Initializable{

    @FXML HBox panelPrincipal;
    @FXML VBox panelEmpleado, panelTitulo, footer;
    @FXML Button volverAtrasEnEmpleado;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         panelEmpleado.managedProperty().bind(panelEmpleado.visibleProperty());
         footer.managedProperty().bind(footer.visibleProperty());
         panelTitulo.managedProperty().bind(panelTitulo.visibleProperty());
         panelPrincipal.managedProperty().bind(panelPrincipal.visibleProperty());
    }
    
    @FXML
    public void mostrarMensajes() {
        panelEmpleado.setVisible(false);
        panelPrincipal.setVisible(true);
        panelTitulo.setVisible(false);
        footer.setVisible(true);
        this.layout.cargarPantalla("mensaje", MensajeController.class.getResource("Mensaje.fxml"));
       ((MensajeController) this.layout.getCotroller("mensaje")).mostrar();
        Node nodoPantallaMensaje = this.layout.getPantalla("mensaje");
        panelPrincipal.getChildren().clear();
        panelPrincipal.getChildren().add(nodoPantallaMensaje);
    }
    
    @FXML
    public void mostrarServicios() {
        panelEmpleado.setVisible(false);
        panelPrincipal.setVisible(true);
        panelTitulo.setVisible(false);
        footer.setVisible(true);
        this.layout.cargarPantalla("servicio", ServicioController.class.getResource("Servicio.fxml"));
       ((ServicioController) this.layout.getCotroller("servicio")).mostrar();
        Node nodoPantallaServicio = this.layout.getPantalla("servicio");
        panelPrincipal.getChildren().clear();
        panelPrincipal.getChildren().add(nodoPantallaServicio);
    }
    
    @FXML 
    public void volverAtrasEnEmpleado(){
        if(panelEmpleado.isVisible()) {
            this.layout.mostrarComoPantallaActual("autentificacion");
            
        } else {
            
            panelPrincipal.setVisible(false);
            panelEmpleado.setVisible(true);
            panelTitulo.setVisible(true);
            footer.setVisible(false);
        }
        
    }
    
    @FXML
    public void volver(){
        panelPrincipal.setVisible(false);
        panelEmpleado.setVisible(true);
        panelTitulo.setVisible(true);
        footer.setVisible(false);
    }
    
}
