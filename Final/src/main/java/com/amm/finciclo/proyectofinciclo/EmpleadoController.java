package com.amm.finciclo.proyectofinciclo;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class EmpleadoController extends ControladorConNavegabilidad implements Initializable{

    @FXML HBox panelPrincipal;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
    }
    
    @FXML
    public void mostrarMensajes() {
        this.layout.cargarPantalla("mensaje", MensajeController.class.getResource("Mensaje.fxml"));
       ((MensajeController) this.layout.getCotroller("mensaje")).mostrar();
        Node nodoPantallaMensaje = this.layout.getPantalla("mensaje");
        
        panelPrincipal.getChildren().add(nodoPantallaMensaje);
    }
    
 
    
}
