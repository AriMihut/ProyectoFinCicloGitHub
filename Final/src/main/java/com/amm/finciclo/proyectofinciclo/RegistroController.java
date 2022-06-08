package com.amm.finciclo.proyectofinciclo;
    
import dao.DAOUsuario;
import java.io.IOException;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class RegistroController extends ControladorConNavegabilidad implements Initializable {

@FXML private TextField usuario;
@FXML private PasswordField contrasena;
@FXML private PasswordField repetirContrasena;
@FXML private Label etiquetaAviso;

private DAOUsuario usuarioDao;

int id;

@Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTextFields();
    }
    
    private boolean comprobarContraseñasCoincidentes(){
        return repetirContrasena.getText().equals(contrasena.getText());
    }
    
    private boolean todosCamposCubiertos(){
        return !usuario.getText().isEmpty() && !contrasena.getText().isEmpty() && 
                !repetirContrasena.getText().isEmpty();
    }
    
     private void cambiarEstilosError(TextField campo, boolean esError) {
        if(esError){
            campo.getStyleClass().add("textField-error");
        } else {
            campo.getStyleClass().remove("textField-error");
        }
    }
    
    @FXML
    public void aceptar() throws IOException, SQLException{
        if(compobacionesUsuario()){
            guardarUsuario(usuario.getText(), contrasena.getText());
            navegarSegunTipoUsuario();
            limpiar();
        }       
    }
    
    private boolean compobacionesUsuario() {
         if(!todosCamposCubiertos()) {
            comprobarTextFieldsVaciosError();
            mostrarAviso("Todos los campos son obligatorios");
            return false;
        } else {
            if(!comprobarContraseñasCoincidentes()) {
                cambiarEstilosError(contrasena, true);
                cambiarEstilosError(repetirContrasena, true);
                mostrarAviso("Las contraseñas no coinciden");
                return false;
            }
        }
       return todosCamposCubiertos() && comprobarContraseñasCoincidentes()? true :false;
    }
    
    private void comprobarTextFieldsVaciosError(){
        cambiarEstilosError(usuario, usuario.getText().isEmpty());
        cambiarEstilosError(contrasena, contrasena.getText().isEmpty());
        cambiarEstilosError(repetirContrasena, repetirContrasena.getText().isEmpty());
    }
    
    private void configurarTextFields(){
        usuario.textProperty().addListener((obs, oldV, newV) -> {
            
            if(oldV != newV && !newV.isEmpty()){
                usuario.getStyleClass().remove("textField-error");
                etiquetaAviso.setVisible(false);
                
            }
        });
        
        contrasena.textProperty().addListener((obs, oldV, newV) -> {
            
            if(oldV != newV && !newV.isEmpty()){
                contrasena.getStyleClass().remove("textField-error");
                etiquetaAviso.setVisible(false);
                
            }
        });
        
        repetirContrasena.textProperty().addListener((obs, oldV, newV) -> {
            
            if(oldV != newV && !newV.isEmpty()){
                repetirContrasena.getStyleClass().remove("textField-error");
                etiquetaAviso.setVisible(false);
                
            }
        });
    }
    
    private void mostrarAviso(String text) {
        etiquetaAviso.setText(text);
        etiquetaAviso.setVisible(true);
    }
    
    @FXML
    public void volverAtras()  throws IOException {
        limpiar();
        this.layout.cargarPantalla("autentificacion", ClienteController.class.getResource("AutentificacionCPE.fxml"));
        this.layout.mostrarComoPantallaActual("autentificacion");
    }
    
    private void guardarUsuario(String nombre, String contrasena) throws SQLException {
        usuarioDao = new DAOUsuario();
        usuarioDao.anadir( new Usuario(0, nombre, contrasena, this.layout.getRolUsuario()));
    }
    
    private void navegarSegunTipoUsuario() throws IOException{
        switch(this.layout.getRolUsuario()){
            case CLIENTE:
                this.layout.cargarPantalla("cliente", ClienteController.class.getResource("Cliente.fxml"));
                this.layout.mostrarComoPantallaActual("cliente");
                break;
            case EMPLEADO:
                this.layout.cargarPantalla("empleado", EmpleadoController.class.getResource("Empleado.fxml"));
                this.layout.mostrarComoPantallaActual("empleado");
                break;
            default:
                System.out.println("Opción no válida!");
                break;
        }
      
    }
    
    private void limpiar() {
        usuario.clear();
        contrasena.clear();
        repetirContrasena.clear();
        cambiarEstilosError(usuario, false);
        cambiarEstilosError(contrasena, false);
        cambiarEstilosError(repetirContrasena, false);
        etiquetaAviso.setVisible(false);
    }

}
