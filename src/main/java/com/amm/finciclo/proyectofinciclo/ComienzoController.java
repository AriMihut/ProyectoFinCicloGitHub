
package com.amm.finciclo.proyectofinciclo;
import dao.DAOCliente;
import dao.DAOEmpleado;
import dao.DAOProveedor;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


public class ComienzoController extends ControladorConNavegabilidad implements Initializable {
    
    @FXML private Button accederCliente;
    @FXML private Button accederProveedor;
    @FXML private Button accederEmpleado;
    @FXML private Button accederAdministrador;
    DAOCliente cliente;
    DAOEmpleado empleado;
    DAOProveedor proveedor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
       /* try {*/
           /* cliente = new DAOClientes();
            empleado = new DAOEmpleados();
            proveedor = new DAOProveedores();*/
        /*} catch (SQLException ex) {
            Logger.getLogger(ComienzoController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    
    /*accederCliente();
    accederEmpleado();
    accederProveedor();
        try {
    accederAdministrador();
        } catch (IOException ex) {
            Logger.getLogger(ComienzoController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }    
    
    @FXML
    public void accederComoCliente(){
        this.layout.setRolUsuario("cliente");
        AutentificacionCPEController controladorAutentificacion = 
                (AutentificacionCPEController) this.layout.getCotroller("autentificacion");
        this.layout.mostrarComoPantallaActual("autentificacion");
    }
    
    @FXML
    public void accederComoProveedor(){
        this.layout.setRolUsuario("proveedor");
        AutentificacionCPEController controladorAutentificacion = 
                (AutentificacionCPEController) this.layout.getCotroller("autentificacion");
        this.layout.mostrarComoPantallaActual("autentificacion");  
    }
    
    @FXML
    public void accederComoEmpleado(){
        this.layout.setRolUsuario("empleado");
        AutentificacionCPEController controladorAutentificacion = 
                (AutentificacionCPEController) this.layout.getCotroller("autentificacion");
        this.layout.mostrarComoPantallaActual("autentificacion"); 
    }
    
    @FXML
    public void accederComoAdministrador() throws IOException{
        this.layout.setRolUsuario("admin");
        AutentificacionCPEController controladorAutentificacion = 
                (AutentificacionCPEController) this.layout.getCotroller("autentificacion");
        this.layout.mostrarComoPantallaActual("autentificacion");    
    }  

}
