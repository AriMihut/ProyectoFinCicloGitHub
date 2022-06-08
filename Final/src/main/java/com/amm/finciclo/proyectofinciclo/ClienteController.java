package com.amm.finciclo.proyectofinciclo;

import dao.DAOCliente;
import dao.DAOUsuario;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ClienteController extends ControladorConNavegabilidad implements Initializable {
    
    @FXML private TextField filtroDni, filtroNombre, filtroApellido, filtroTelefono, filtroEmail;
    @FXML private ComboBox<String> comboboxSexo;
    @FXML private TableView<Cliente> tablaClientes;
    @FXML private Button btnEditar;
    private DAOCliente clienteDao;
    
    @FXML private Label dniCliente, nombreCliente, apellidoCliente, labelSexo, telefono, email;
    @FXML private Label dniClienteTexto, nombreClienteTexto, apellidoClienteTexto, labelSexoTexto, telefonoTexto, emailTexto;
    
    @FXML private VBox formularioModificacion, panelContenido, vBoxTitulo;
    private ObservableList<Cliente> clientes = FXCollections.observableArrayList();
    @FXML private HBox infoCliente, datosCliente;
    @FXML private ToolBar buttons;
    //@FXML private ComboBox<Servicio> servicios;
    
    private DAOUsuario daoUsuario;
    
    private int id;
    private Usuario usuario = null;
    
    private Cliente clientesAModificar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setDatosUsuario();
            infoCliente.managedProperty().bind(infoCliente.visibleProperty());
            formularioModificacion.managedProperty().bind(formularioModificacion.visibleProperty());
            datosCliente.managedProperty().bind(datosCliente.visibleProperty());
            panelContenido.managedProperty().bind(panelContenido.visibleProperty());
            
            
            btnEditar.disableProperty().bind(formularioModificacion.visibleProperty());
            
            configuraCabecero();
            configurarComboBox();
            daoUsuario = new DAOUsuario();
            
            comboboxSexo.setCellFactory(listView -> new ImagenListCell());
            comboboxSexo.setButtonCell(new ImagenListCell());
            
            
            
        } catch (SQLException ex) {
            System.out.println("Error initialize DaoUsuario " + ex.getMessage());
        }
    }    
  
    private void configurarComboBox(){
        comboboxSexo.getItems().addAll("Femenino", "Masculino");
        comboboxSexo.getSelectionModel().selectFirst();     
    }
    
    @FXML
    public void comprar(){
        this.layout.cargarPantalla("compra", CompraController.class.getResource("Compra.fxml"));
        anadirContenido("compra");
        mostrarContenido();
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
        /*this.layout.cargarPantalla("cliente", ClienteController.class.getResource("Cliente.fxml"));
        anadirContenido("cliente");
        mostrarContenido();*/
    }
  
    @FXML
    public void contactar(){
        this.layout.cargarPantalla("contacto", ContactoController.class.getResource("Contacto.fxml"));
        anadirContenido("contacto");
        mostrarContenido();
        
    }
    
    private void configuraCabecero() {
        vBoxTitulo.managedProperty().bind(vBoxTitulo.visibleProperty());
        vBoxTitulo.visibleProperty().bind(panelContenido.visibleProperty().not());
        buttons.managedProperty().bind(buttons.visibleProperty());
        buttons.visibleProperty().bind(panelContenido.visibleProperty().not());
                
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
    
    @FXML
    private void volver() {
        if(datosCliente.isVisible() && infoCliente.isVisible()) {
            this.layout.cargarPantalla("autentificacion", AutentificacionCPEController.class.getResource("AutentificacionCPE.fxml"));
            this.layout.mostrarComoPantallaActual("autentificacion");  
        } else {
            mostrarDatosCliente();
        }
    }
    
    public void mostrar(){
        tablaClientes.getItems().clear();
     
        List<Cliente> clientesAMostrar = clienteDao.buscarTodos();
        clientes.addAll(clientesAMostrar);
        tablaClientes.setItems(clientes);
    }
    
    public void mostrarDatosCliente() {
        formularioModificacion.setVisible(false);
        panelContenido.setVisible(false);
        datosCliente.setVisible(true);
        infoCliente.setVisible(true);
    }
    
    private void mostrarContenido() {
      panelContenido.setVisible(true);
      datosCliente.setVisible(false);
    }
    
    private void anadirContenido(String nombrePantalla) {
        panelContenido.getChildren().clear();
        panelContenido.getChildren().add(this.layout.getPantalla(nombrePantalla));
    }
    
    

}
