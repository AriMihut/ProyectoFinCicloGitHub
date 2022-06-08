package com.amm.finciclo.proyectofinciclo;

import dao.DAOCliente;
import dao.DAOPersonal;
import dao.DAOUsuario;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class PantallaHomeController extends ControladorConNavegabilidad implements Initializable {
    
    private @FXML Button atras, verPersonal, verClientes, verServicios, verVentas; 
    
    private @FXML VBox footer, panelContenido, infoBotones;
    private DAOCliente daoCliente;
    private DAOPersonal daoEmpleado;
    private DAOUsuario daoUsuario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            daoUsuario = new DAOUsuario();
            daoCliente = new DAOCliente();
            panelContenido.managedProperty().bind(panelContenido.visibleProperty());
            infoBotones.managedProperty().bind(infoBotones.visibleProperty());
        } catch (SQLException ex) {
            System.out.println("Error en el initialize de PantallaHomeController " + ex.getMessage());
        }
    }    
    
    @FXML
    public void verPersonal() throws IOException{
        this.layout.cargarPantalla("personal", PersonalController.class.getResource("Personal.fxml"));
        añadirContenido("personal");
        mostrarPanelContenido();
    }
    
    @FXML
    public void verClientes() throws IOException{
        this.layout.cargarPantalla("clientes", ClientesController.class.getResource("Clientes.fxml"));
        añadirContenido("clientes");
        mostrarPanelContenido();
    }
    
    @FXML
    public void verServicios() throws IOException{
        this.layout.cargarPantalla("servicio", ServicioController.class.getResource("Servicio.fxml"));
        añadirContenido("servicio");
        mostrarPanelContenido();
    }
   
    @FXML
    public void verVentas() throws IOException{
        this.layout.cargarPantalla("venta", VentaController.class.getResource("Venta.fxml"));
        añadirContenido("venta");
        mostrarPanelContenido();
    }
    
    @FXML
    public void atras() throws IOException{
        if(panelContenido.isVisible()) {
            mostrarPantallaHome();
        } else {
            this.layout.cargarPantalla("autentificacion", AutentificacionCPEController.class.getResource("AutentificacionCPE.fxml"));
            this.layout.mostrarComoPantallaActual("autentificacion");
        }
    }
    
    private void mostrarPanelContenido() {
        infoBotones.setVisible(false);
        panelContenido.setVisible(true);
    }
    
    private void mostrarPantallaHome() {
        panelContenido.setVisible(false);
        infoBotones.setVisible(true);
    }
    
    private void añadirContenido(String nombrePantalla) {
        
        panelContenido.getChildren().clear();
        panelContenido.getChildren().add(this.layout.getPantalla(nombrePantalla));
    }
    
    
}
