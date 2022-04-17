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
        layoutPane.cargarPantalla("cliente", ClienteController.class.getResource("Cliente.fxml"));
        layoutPane.cargarPantalla("empleado", EmpleadoController.class.getResource("Empleado.fxml"));
        layoutPane.cargarPantalla("proveedor", ProveedorController.class.getResource("Proveedor.fxml"));
        layoutPane.cargarPantalla("pagHome", PantallaHomeController.class.getResource("PantallaHome.fxml"));
        layoutPane.cargarPantalla("contacto", PantallaHomeController.class.getResource("Contacto.fxml"));
        layoutPane.cargarPantalla("mensaje", PantallaHomeController.class.getResource("Mensaje.fxml"));
        layoutPane.cargarPantalla("servicio", PantallaHomeController.class.getResource("Servicio.fxml"));
        
        layoutPane.mostrarComoPantallaActual("comienzo");
     
        Scene escena = new Scene(layoutPane, 700, 700);
        stage.setScene(escena);
        stage.show();
    
    }
   /*  private static Scene scene;
     
    @Override
    public void start(Stage stage) throws IOException {
        
        scene =  new Scene(loadFXML("Comienzo"), 600, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
     private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
        
    }*/

}