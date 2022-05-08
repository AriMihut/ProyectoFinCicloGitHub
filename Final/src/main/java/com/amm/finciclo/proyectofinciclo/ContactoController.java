package com.amm.finciclo.proyectofinciclo;

import dao.DAOMensaje;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ContactoController extends ControladorConNavegabilidad implements Initializable{
    @FXML private TextField asunto;
    @FXML private TextArea textoMensaje;
    @FXML private CheckBox checkUrgente;
    @FXML private Button botonEnviar;
    @FXML private VBox infoParaCliente;
    @FXML private HBox infoParaEmpleado;
    @FXML private Label nombreCliente;
    
    
    DAOMensaje mensajeDao;
    
    private String idDestinatarioParaEmpleado = "";
    private String idMensajeAresponder = "";
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            mensajeDao = new DAOMensaje();
        } catch (SQLException ex) {
            Logger.getLogger(ContactoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    public void atras() throws IOException{
        this.layout.cargarPantalla("cliente", ClienteController.class.getResource("Cliente.fxml"));
        this.layout.mostrarComoPantallaActual("cliente");
    }
    
    @FXML
    public void enviarMensaje() throws IOException {
        Usuario usuario = this.layout.getUsuario(); 
        if(this.layout.getUsuario().getTipoUsuario().equals(TipoUsuario.CLIENTE)) {
            mensajeDao.anadir(new Mensaje(0, usuario.getId(), usuario.getNombreUsuario(),
                asunto.getText(), usuario.getTipoUsuario(), checkUrgente.isSelected(), false, usuario.getId(),
                    textoMensaje.getText()));
        } else {
            mensajeDao.anadir(new Mensaje(0, usuario.getId(), usuario.getNombreUsuario(),
               "Respuesta", usuario.getTipoUsuario(), false, false, Integer.parseInt(this.idDestinatarioParaEmpleado),
                    textoMensaje.getText()));
        }
        this.idMensajeAresponder = "";
        this.layout.getCotroller("contacto");
    }
    
     @FXML
    public void verMensajes() throws IOException{
        this.layout.cargarPantalla("mensaje", MensajeController.class.getResource("Mensaje.fxml"));
        ((MensajeController) this.layout.getCotroller("mensaje")).mostrar();
        this.layout.mostrarComoPantallaActual("mensaje");
        
    }
    
     @FXML
    public void contactar() throws IOException{
        this.layout.cargarPantalla("contacto", ContactoController.class.getResource("Contacto.fxml"));
        this.layout.mostrarComoPantallaActual("contacto");
    }  
    
    public void configurarParaUsuario(int idAutor, String nombreAutor) {
       this.idDestinatarioParaEmpleado = String.valueOf(idAutor);
       infoParaCliente.managedProperty().bind(infoParaCliente.visibleProperty());
       infoParaEmpleado.managedProperty().bind(infoParaEmpleado.visibleProperty());
        
       infoParaCliente.setVisible(this.layout.getUsuario().getTipoUsuario().equals(TipoUsuario.CLIENTE));
       infoParaEmpleado.setVisible(this.layout.getUsuario().getTipoUsuario().equals(TipoUsuario.EMPLEADO));
       
       nombreCliente.setText(nombreAutor);
    }
    
   
}
