package com.amm.finciclo.proyectofinciclo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EmpleadoController extends ControladorConNavegabilidad implements Initializable{

    @FXML HBox panelPrincipal;
    @FXML VBox panelEmpleado;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         panelEmpleado.managedProperty().bind(panelEmpleado.visibleProperty());
    }
    
    @FXML
    public void mostrarMensajes() {
        panelEmpleado.setVisible(false);
        panelPrincipal.setVisible(true);
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
        this.layout.cargarPantalla("servicio", ServicioController.class.getResource("Servicio.fxml"));
       ((ServicioController) this.layout.getCotroller("servicio")).mostrar();
        Node nodoPantallaServicio = this.layout.getPantalla("servicio");
        panelPrincipal.getChildren().clear();
        panelPrincipal.getChildren().add(nodoPantallaServicio);
    }
    
    @FXML
    public void atras(){
        panelPrincipal.setVisible(false);
        panelEmpleado.setVisible(true);
    }
    
}
