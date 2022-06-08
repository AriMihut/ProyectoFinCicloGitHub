package com.amm.finciclo.proyectofinciclo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {

     @Override
    public void start(Stage stage) throws IOException {
    
        LayoutPane layoutPane = new LayoutPane();
        layoutPane.cargarPantalla("comienzo", ComienzoController.class.getResource("Comienzo.fxml"));
        layoutPane.cargarPantalla("autentificacion", AutentificacionCPEController.class.getResource("AutentificacionCPE.fxml"));
        layoutPane.cargarPantalla("registro", RegistroController.class.getResource("Registro.fxml"));
        
        layoutPane.mostrarComoPantallaActual("comienzo");
        
        Scene escena = new Scene(layoutPane, 700, 700);
        stage.setScene(escena);
        stage.show();
    }

}