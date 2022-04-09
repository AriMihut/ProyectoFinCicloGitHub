package com.amm.finciclo.proyectofinciclo;
    
import dao.DAOCliente;
import dao.DAOEmpleado;
import dao.DAOProveedor;
import dao.DAOUsuario;
import java.io.IOException;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class RegistroController extends ControladorConNavegabilidad implements Initializable {

@FXML private TextField usuario;
@FXML private TextField contrasena;
@FXML private TextField repetirContrasena;
@FXML private Label etiquetaAviso;

private DAOUsuario usuarioDao;

int id;

@Override
    public void initialize(URL url, ResourceBundle rb) {

    /*try {
       aceptar();
    } catch (IOException ex) {
        System.out.println("Error en el initialize de RegistroController " + ex.getMessage());
        } catch (SQLException ex) {
        Logger.getLogger(RegistroController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
    
    private boolean comprobarContraseñasCoincidentes(){
        return repetirContrasena.getText().equals(contrasena.getText());
    }
    
    private boolean todosCamposCubiertos(){
        return !usuario.getText().isEmpty() && !contrasena.getText().isEmpty() && 
                !repetirContrasena.getText().isEmpty();
    }
    
    private void cambiarEstilosError(TextField campo) {
        campo.getStyleClass().add("textField-error");
        System.out.println(campo.getStyleClass());
    }
    
    @FXML
    public void aceptar() throws IOException, SQLException{
        if(compobacionesUsuario()){
            guardarUsuario(usuario.getText(), contrasena.getText());
            navegarSegunTipoUsuario();
        }       
    }
    
    private boolean compobacionesUsuario() {
         if(!todosCamposCubiertos()) {
            comprobarTextFieldsVaciosError();
            mostrarAviso("Todos los campos son obligatorios");
            return false;
        } else {
            if(!comprobarContraseñasCoincidentes()) {
                cambiarEstilosError(contrasena);
                cambiarEstilosError(repetirContrasena);
                mostrarAviso("Las contraseñas no coinciden");
                return false;
            }
        }
        
       return todosCamposCubiertos() && comprobarContraseñasCoincidentes()? true :false;
           
    }
    
    private void comprobarTextFieldsVaciosError(){
        if(usuario.getText().isEmpty()){
            cambiarEstilosError(usuario);
        }
        
        if(contrasena.getText().isEmpty()){
            cambiarEstilosError(contrasena);
        }
        
        if(repetirContrasena.getText().isEmpty()){
            cambiarEstilosError(repetirContrasena);
        }
        
    }
    
    private void mostrarAviso(String text) {
        etiquetaAviso.setText(text);
        etiquetaAviso.setVisible(true);
    }
    
    @FXML
    public void volverAtras()  throws IOException {
        this.layout.cargarPantalla("autentificacion", ClienteController.class.getResource("AutentificacionCPE.fxml"));
        this.layout.mostrarComoPantallaActual("autentificacion");
    }
    
    private void guardarUsuario(String nombre, String contrasena) throws SQLException {
        usuarioDao = new DAOUsuario();
        Usuario usuario = new Usuario(0, nombre, contrasena);
        usuarioDao.anadir(usuario);
    }

    private void navegarSegunTipoUsuario() throws IOException{
        switch(this.layout.getRolUsuario()){
            case "cliente":
                this.layout.mostrarComoPantallaActual("cliente");
                break;
            case "empleado":
                this.layout.mostrarComoPantallaActual("empleado");
                break;
            case "proveedor":
                this.layout.mostrarComoPantallaActual("proveedor");
                break;
            default:
                System.out.println("Opción no válida!");
                break;
        }
        
    }

}
