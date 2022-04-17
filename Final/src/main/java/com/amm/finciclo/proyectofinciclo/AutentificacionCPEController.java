package com.amm.finciclo.proyectofinciclo;

import dao.DAOUsuario;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AutentificacionCPEController extends ControladorConNavegabilidad implements Initializable {
    
    @FXML private TextField usuario;
    @FXML private TextField contrasena;
    @FXML private Label etiquetaAviso;
    @FXML private HBox contenedorRegistro;
    
    private DAOUsuario usuarioDao;
    
    int id = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       try {
      
        usuarioDao = new DAOUsuario();
        
        } catch (SQLException ex) {
            System.out.println("Error AutentificacionCPEController: " + ex.getMessage());
        } 
      
    }    

    @FXML
    public void autentificarse() throws IOException, SQLException {
        Usuario nuevoUsuario = new Usuario(0, usuario.getText(), contrasena.getText(), this.layout.getRolUsuario());
        if(comprobacionesUsuario()){
            if(usuarioDao.comprobarExistenciaUsuario(nuevoUsuario)){
            navegarSegunTipoUsuario();
            } 
            else {
                 mostrarAviso("El usuario o la contraseña están erróneos");
             }
        }
        else{
            mostrarAviso("Todos los campos deben estar cubiertos!");
         }
    }
    
    private void navegarSegunTipoUsuario() throws IOException{
        
        switch(this.layout.getRolUsuario()){
            case CLIENTE:
                this.layout.mostrarComoPantallaActual("cliente");
                break;
            case EMPLEADO:
                this.layout.mostrarComoPantallaActual("empleado");
                break;
            case PROVEEDOR:
                this.layout.mostrarComoPantallaActual("proveedor");
                break;
            case ADMIN:
                this.layout.mostrarComoPantallaActual("pagHome");
                break;
            default:
                System.out.println("Opción no válida!");
                break;
        }
    }
    
    @FXML
    public void registrarse() throws IOException{
        this.layout.cargarPantalla("registro", RegistroController.class.getResource("Registro.fxml"));
        this.layout.mostrarComoPantallaActual("registro");
    }
    
    @FXML
    public void volver() throws IOException{
       this.layout.mostrarComoPantallaActual("comienzo");
    }

    private void configurarSegunUsuario() {
        contenedorRegistro.setVisible(!this.layout.getRolUsuario().equals("admin"));
        
    }

    private boolean comprobacionesUsuario() {
       
        if(!todosCamposCubiertos()) {
            mostrarAviso("Todos los campos son obligatorios");
            return false;
        } else{
            return true;
        }
       
    }

    private boolean todosCamposCubiertos() {
       return !usuario.getText().isEmpty() && !contrasena.getText().isEmpty();
    }

    private void mostrarAviso(String text) {
       etiquetaAviso.setText(text);
       etiquetaAviso.setVisible(true);
    }
    
}
