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

public class RegistroController extends ControladorConNavegabilidad implements Initializable {

@FXML private TextField usuario;
@FXML private TextField contrasena;
@FXML private TextField repetirContrasena;
@FXML private Label etiquetaAviso;

private DAOUsuario usuarioDao;

int id;

@Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
            case PROVEEDOR:
                this.layout.mostrarComoPantallaActual("proveedor");
                break;
            default:
                System.out.println("Opción no válida!");
                break;
        }
      
    }

}
