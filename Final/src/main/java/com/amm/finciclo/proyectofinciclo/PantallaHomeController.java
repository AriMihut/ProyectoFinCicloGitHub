package com.amm.finciclo.proyectofinciclo;

import dao.DAOCliente;
import dao.DAOPersonal;
import dao.DAOProveedor;
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
    
    private @FXML Button atras, verPersonal, verProveedores, verClientes, verServicios, verProductos, verBeneficios, verDepartamentos; 
    private @FXML HBox hboxBtnAtras;
    private DAOCliente daoCliente;
    private DAOPersonal daoEmpleado;
    private DAOProveedor daoProveedor;
    private DAOUsuario daoUsuario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            daoUsuario = new DAOUsuario();
            daoCliente = new DAOCliente();
        } catch (SQLException ex) {
            System.out.println("Error en PantallaHomeController " + ex.getMessage());
        }
    }    
    
    @FXML
    public void verPersonal() throws IOException{
        this.layout.mostrarComoPantallaActual("personal");
    }
    
    @FXML
    public void verProveedores() throws IOException{
      //  App.setRoot("Proveedor");
    }
    
    @FXML
    public void verClientes() throws IOException{
        //aqui debo listar los clientes que hay
        //coger la info desde donde se registran los clientes
    }
    
    @FXML
    public void verServicios() throws IOException{
        this.layout.mostrarComoPantallaActual("servicio");
        
    }
    
    @FXML
    public void verProductos() throws IOException{
      //  App.setRoot("Producto");
    }
    
    @FXML
    public void verBeneficios() throws IOException{
       // App.setRoot("Beneficios");
    }
    
    @FXML
    public void verDepartamentos() throws IOException{
      //  App.setRoot("Departamento");
    }
    
    @FXML
    public void atras() throws IOException{
        // App.setRoot("AutentificacionCPE");
    }
    
}
