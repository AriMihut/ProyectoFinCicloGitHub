package com.amm.finciclo.proyectofinciclo;

import dao.DAOCliente;
import dao.DAOEmpleado;
import dao.DAOProveedor;
import dao.DAOUsuario;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class PantallaHomeController extends ControladorConNavegabilidad implements Initializable {
    
    @FXML Button atras; 
    @FXML Button verPersonal;
    @FXML Button verProveedores;
    @FXML Button verClientes;
    @FXML Button verServicios;
    @FXML Button verProductos;
    @FXML Button verBeneficios;
    @FXML Button verDepartamentos;
    @FXML HBox hboxBtnAtras;
    private  DAOCliente daoClientes;
    private DAOEmpleado daoEmpleados;
    private DAOProveedor daoProveedores;
    private DAOUsuario daoUsuarios;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            daoUsuarios = new DAOUsuario();
            daoClientes = new DAOCliente();
            daoEmpleados = new DAOEmpleado();
            /*daoProductos = new DAOProductos();
            daoProveedores = new DAOProveedores();
            daoServicios = new DAOServicios();*/
        } catch (SQLException ex) {
            System.out.println("ex");
            Logger.getLogger(PantallaHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @FXML
    public void verPersonal() throws IOException{
        //App.setRoot("Personal");
    }
    
    @FXML
    public void verProveedores() throws IOException{
      //  App.setRoot("Proveedor");
    }
    
    @FXML
    public void verClientes() throws IOException{
       // App.setRoot("Cliente");
    }
    
    @FXML
    public void verServicios() throws IOException{
       // App.setRoot("Servicio");
        
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
