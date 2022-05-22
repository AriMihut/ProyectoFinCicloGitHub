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
import javafx.scene.layout.HBox;

public class PantallaHomeController extends ControladorConNavegabilidad implements Initializable {
    
    private @FXML Button atras, verPersonal, verClientes, verServicios, verVentas; 
    private @FXML HBox hboxBtnAtras;
    private DAOCliente daoCliente;
    private DAOPersonal daoEmpleado;
    private DAOUsuario daoUsuario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            daoUsuario = new DAOUsuario();
            daoCliente = new DAOCliente();
        } catch (SQLException ex) {
            System.out.println("Error en el initialize de PantallaHomeController " + ex.getMessage());
        }
    }    
    
    @FXML
    public void verPersonal() throws IOException{
        this.layout.cargarPantalla("personal", PersonalController.class.getResource("Personal.fxml"));
        this.layout.mostrarComoPantallaActual("personal");
    }
    
    @FXML
    public void verClientes() throws IOException{
        //aqui debo listar los clientes que hay
        //coger la info desde donde se registran los clientes
    }
    
    @FXML
    public void verServicios() throws IOException{
        this.layout.cargarPantalla("servicio", ServicioController.class.getResource("Servicio.fxml"));
        this.layout.mostrarComoPantallaActual("servicio");
    }
   
    @FXML
    public void verVentas() throws IOException{
        this.layout.cargarPantalla("venta", VentaController.class.getResource("Venta.fxml"));
        this.layout.mostrarComoPantallaActual("venta");
    }
    
    @FXML
    public void atras() throws IOException{
        this.layout.cargarPantalla("autentificacion", AutentificacionCPEController.class.getResource("AutentificacionCPE.fxml"));
        this.layout.mostrarComoPantallaActual("autentificacion");
    }
    
}
