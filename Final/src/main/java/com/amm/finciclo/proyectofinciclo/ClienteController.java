package com.amm.finciclo.proyectofinciclo;

import dao.DAOUsuario;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ClienteController extends ControladorConNavegabilidad implements Initializable {
    
    @FXML TextField filtroDni, filtroNombre, filtroApellido, filtroTelefono, filtroEmail;
    @FXML private ComboBox<String> comboboxSexo;
    
    @FXML private Label dniCliente, nombreCliente, apellidoCliente, labelSexo, telefono, email;
    @FXML private Label dniClienteTexto, nombreClienteTexto, apellidoClienteTexto, labelSexoTexto, telefonoTexto, emailTexto;
    
    @FXML VBox formularioModificacion;
    @FXML HBox infoCliente;
    //@FXML private ComboBox<Servicio> servicios;
    
    @FXML Button atras;
    private DAOUsuario daoUsuario;
    
    private int id;
    Usuario usuario = null;
    
    private Cliente clientesAModificar;
    private ObservableList<Cliente> clientes = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setDatosUsuario();
            infoCliente.managedProperty().bind(infoCliente.visibleProperty());
            formularioModificacion.managedProperty().bind(formularioModificacion.visibleProperty());
           // infoCliente.visibleProperty().bind(infoCliente.managedProperty());
           // formularioModificacion.visibleProperty().bind(formularioModificacion.managedProperty());
           
            configurarComboBox();
            daoUsuario = new DAOUsuario();
            //editarPerfil();
            //configurarServicios();
            
            comboboxSexo.setCellFactory(listView -> new ImagenListCell());
            comboboxSexo.setButtonCell(new ImagenListCell());
            
        } catch (SQLException ex) {
            System.out.println("Error initialize DaoUsuario " + ex.getMessage());
        }
    }    
    
    /*private void configurarServicios(){
        servicios.getSelectionModel().selectFirst();      
    }*/
     
    private void configurarComboBox(){
        comboboxSexo.getItems().addAll("Femenino", "Masculino");
        comboboxSexo.getSelectionModel().selectFirst();     
    }
    
    @FXML
    public void comprar(){
        this.layout.cargarPantalla("compra", CompraController.class.getResource("Compra.fxml"));
        this.layout.mostrarComoPantallaActual("compra");
    }
    
    @FXML
    public void guardar(){
        Usuario usuario = new Usuario();
        usuario.setId(this.layout.getUsuario().getId());
        usuario.setNombreUsuario(this.layout.getUsuario().getNombreUsuario());
        usuario.setContrasena(this.layout.getUsuario().getContrasena());
        usuario.setDni(filtroDni.getText());
        usuario.setNombre(filtroNombre.getText());
        usuario.setApellido(filtroApellido.getText());
        usuario.setSexo(comboboxSexo.getSelectionModel().getSelectedItem());
        usuario.setTelefono(Long.parseLong(filtroTelefono.getText()));
        usuario.setEmail(filtroEmail.getText());
       
        daoUsuario.modificar(usuario); 
        clearFiltros();
        this.layout.setUsuario(usuario);
        setInfoUsuario();
        volverAInfoCliente();
    }
    
     private void clearFiltros() {
        filtroDni.clear();
        filtroNombre.clear();
        filtroApellido.clear();
        filtroTelefono.clear();
        filtroEmail.clear();
        comboboxSexo.getSelectionModel().selectFirst();
    }
     
    private void clearLabels() {
        dniClienteTexto.setText("");
        nombreClienteTexto.setText("");
        apellidoClienteTexto.setText("");
        labelSexoTexto.setText("");
        telefonoTexto.setText("");
        emailTexto.setText("");
    }
    
    @FXML
    public void editarPerfil(){
        prepararEdicionPerfil();
        infoCliente.setVisible(false);
        formularioModificacion.setVisible(true);
    }
  
    @FXML
    public void contactar(){
        this.layout.cargarPantalla("contacto", ContactoController.class.getResource("Contacto.fxml"));
        this.layout.mostrarComoPantallaActual("contacto");
    }

    
    @FXML
    public void volverAtras() throws IOException{
        this.layout.cargarPantalla("autentificacion", AutentificacionCPEController.class.getResource("AutentificacionCPE.fxml"));
        this.layout.mostrarComoPantallaActual("autentificacion");
    }
    
    public void setInfoUsuario() {
        usuario = this.layout.getUsuario();
        setDatosUsuario();
    }
    
    public void setDatosUsuario() {
        
        if(usuario != null) {

            dniClienteTexto.setText(usuario.getDni() != null ? usuario.getDni() : dniCliente.getText());        
            nombreClienteTexto.setText(usuario.getNombre() != null?
                    usuario.getNombre() : nombreCliente.getText());            
            apellidoClienteTexto.setText(usuario.getApellido()!= null? 
                    usuario.getApellido() : apellidoCliente.getText());
            labelSexoTexto.setText(usuario.getSexo()!= null? 
                    usuario.getSexo(): labelSexo.getText());
            telefonoTexto.setText(usuario.getTelefono()!= null?
                    String.valueOf(usuario.getTelefono()): telefono.getText());
            emailTexto.setText(usuario.getEmail()!= null? 
                    usuario.getEmail(): email.getText());
        }
    }

    private void prepararEdicionPerfil() {
       filtroDni.setText(usuario.getDni());
       filtroNombre.setText(usuario.getNombre());
       filtroApellido.setText(usuario.getApellido());
       filtroTelefono.setText(String.valueOf(usuario.getTelefono()));
       filtroEmail.setText(usuario.getEmail());
       comboboxSexo.getSelectionModel().select(usuario.getSexo());
    }

    @FXML
    private void volverAInfoCliente() {
       infoCliente.setVisible(true);
       formularioModificacion.setVisible(false);
       
    }
    
}
