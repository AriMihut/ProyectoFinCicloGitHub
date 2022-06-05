
package com.amm.finciclo.proyectofinciclo;
import dao.DAOCliente;
import dao.DAOPersonal;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


public class ComienzoController extends ControladorConNavegabilidad implements Initializable {
    
    @FXML private Button botonCliente;
    @FXML private Button botonEmpleado;
    @FXML private Button botonAdministrador;
    private DAOCliente cliente;
    private DAOPersonal empleado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

     
        botonCliente.setOnAction((e) -> 
            configurarAccionesGenerales(TipoUsuario.CLIENTE)
        );
        botonEmpleado.setOnAction((e) -> 
            configurarAccionesGenerales(TipoUsuario.EMPLEADO)
        );
        botonAdministrador.setOnAction((e) -> 
            configurarAccionesGenerales(TipoUsuario.ADMIN)
          
        );
    }    
    
    private void setRolUsuario(TipoUsuario tipoUsuario) {
        this.layout.setRolUsuario(tipoUsuario);
    }
    
    private void cargarPantalla() throws IOException {
          this.layout.cargarPantalla("autentificacion", AutentificacionCPEController.class.getResource("AutentificacionCPE.fxml"));
    }

    private void configurarAccionesGenerales(TipoUsuario tipoUsuario) {
        try {
            cargarPantalla();
            setRolUsuario(tipoUsuario);
            ((AutentificacionCPEController) this.layout.getCotroller("autentificacion")).configurarSegunUsuario();
            this.layout.mostrarComoPantallaActual("autentificacion");
        } catch (IOException ex) {
            Logger.getLogger(ComienzoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    

}