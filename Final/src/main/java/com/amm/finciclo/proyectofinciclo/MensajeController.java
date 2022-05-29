package com.amm.finciclo.proyectofinciclo;

import dao.DAOMensaje;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;

public class MensajeController extends ControladorConNavegabilidad implements Initializable{
    
    @FXML private BorderPane pantallaMensaje;
    
    @FXML private TableView<Mensaje> tablaMensajes;
    @FXML private ComboBox<Cliente> clientes;
    @FXML private HBox contenedorMensaje;
    @FXML private Label textoMensaje;
      
    @FXML private TableColumn<Mensaje, Integer> idMensajeColumn;
    @FXML private TableColumn<Mensaje, Integer> idAutorColumn;
    @FXML private TableColumn<Mensaje, String> nombreAutorColumn;
    @FXML private TableColumn<Mensaje, String> asuntoColumn;
    @FXML private TableColumn<Mensaje, TipoUsuario> tipoUsuarioColumn;
    @FXML private TableColumn<Mensaje, Boolean> esUrgenteColumn;
    @FXML private TableColumn<Mensaje, Boolean> esLeidoColumn; 
    @FXML private TableColumn<Mensaje, String> verMensajeColumn;
    
    @FXML private Button btnResponder;
    @FXML private Button btnContacto;
    
    
    private ObservableList<Mensaje> mensajes = FXCollections.observableArrayList();
    private DAOMensaje mensajeDao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            mensajeDao = new DAOMensaje();
            /*pantallaMensaje.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent me) -> {
               if(me.equals(MouseButton.PRIMARY)){
               contenedorMensaje.setVisible(false);
               }
            });*/
          
            controlarTamanoColumnas();
        } catch (SQLException ex) {
            System.out.println("Error en el initialize de MensajeController " + ex.getMessage());
        }
    }
    
    @FXML
    public void contactar(){
        this.layout.cargarPantalla("contacto", ContactoController.class.getResource("Contacto.fxml"));
        this.layout.mostrarComoPantallaActual("contacto");
    }
    
    public void configurarLabelMensaje(String texto) {
        textoMensaje.setText(texto);
    }
    
    @FXML
    public void atras(){
        System.out.println("tipoUsuario = " + this.layout.getUsuario().getTipoUsuario());
        if(this.layout.getUsuario().getTipoUsuario().equals(TipoUsuario.CLIENTE)){
            this.layout.cargarPantalla("cliente", ClienteController.class.getResource("Cliente.fxml"));
            this.layout.mostrarComoPantallaActual("cliente");
        } else if(this.layout.getUsuario().getTipoUsuario().equals(TipoUsuario.EMPLEADO)) {
            this.layout.cargarPantalla("empleado", EmpleadoController.class.getResource("Empleado.fxml"));
            ((EmpleadoController) this.layout.getCotroller("empleado")).atras();
            this.layout.mostrarComoPantallaActual("empleado");
        }
                
    }
    
    public void mostrar(){
        configurarBotonesAcciones();
        configurarCeldas();
        cargarMensajes();
    }
    
    private void cargarMensajes() {
        tablaMensajes.getItems().clear();
        ObservableList<Mensaje> mensajes = FXCollections.observableArrayList();
        List<Mensaje> mensajesParaMostrar = new ArrayList<>();
        if(this.layout.getUsuario().getTipoUsuario().equals(TipoUsuario.CLIENTE)){
           mensajesParaMostrar = mensajeDao.buscarTodos(Optional.of(this.layout.getUsuario().getId()));
        } else {
           mensajesParaMostrar = mensajeDao.buscarTodos(Optional.empty());
        }
        mensajes.addAll(mensajesParaMostrar);
        tablaMensajes.setItems(mensajes);
    }

    private void configurarCeldas() {
        idMensajeColumn.setCellValueFactory(new PropertyValueFactory<Mensaje, Integer>("id"));
        idAutorColumn.setCellValueFactory(new PropertyValueFactory<Mensaje, Integer>("idAutor"));
        nombreAutorColumn.setCellValueFactory(new PropertyValueFactory<Mensaje, String>("nombreAutor"));
        asuntoColumn.setCellValueFactory(new PropertyValueFactory<Mensaje, String>("asunto"));
        tipoUsuarioColumn.setCellValueFactory(new PropertyValueFactory<Mensaje, TipoUsuario>("tipoUsuario"));
        esUrgenteColumn.setCellValueFactory(new PropertyValueFactory<Mensaje, Boolean>("esUrgente"));
        esUrgenteColumn.setCellFactory(tc -> new TableCell<Mensaje, Boolean>() {
          @Override
            protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if(!empty && item != null) {
                setText(item ? "Sí": "No");
                setGraphic(null);
            } else {
                 setText("");
                 setGraphic(null);
            }
              
           }
        });  
        esLeidoColumn.setCellValueFactory(new PropertyValueFactory<Mensaje, Boolean>("esLeido"));
        esLeidoColumn.setCellFactory(tc -> new TableCell<Mensaje, Boolean>() {
           
          @Override
            protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if(!empty && item != null) {
                setText(item ? "Sí": "No");
                setGraphic(null);
            } else {
                 setText("");
                 setGraphic(null);
            }
              
           }
        });    
        verMensajeColumn.setCellValueFactory(new PropertyValueFactory<Mensaje, String>("texto"));
        verMensajeColumn.setCellFactory(tc -> new TableCell<Mensaje, String>() {
          @Override
            protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
                if(!empty && item != null) {
                    Button botonVerMensaje = new Button("Ver mensaje");
                    
                    botonVerMensaje.setOnAction((ActionEvent event) -> {
                        verMensaje(this.getTableRow().getItem());
                        this.getTableView().getSelectionModel().select(this.getTableRow().getItem());
                    });
                    
                    setGraphic(botonVerMensaje);
                    setText("");
                } else {
                    setText("");
                    setGraphic(null);
                }
           }
        });
    }
    
    private void controlarTamanoColumnas() {
        tablaMensajes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<TableColumn<Mensaje, ?>> columnas = tablaMensajes.getColumns();
        
        columnas.get(0).setMaxWidth(1f * Integer.MAX_VALUE * 10); 
        columnas.get(1).setMaxWidth(1f * Integer.MAX_VALUE * 10); 
        columnas.get(2).setMaxWidth(1f * Integer.MAX_VALUE * 15); 
        columnas.get(3).setMaxWidth(1f * Integer.MAX_VALUE * 15); 
        columnas.get(4).setMaxWidth(1f * Integer.MAX_VALUE * 15);
        columnas.get(5).setMaxWidth(1f * Integer.MAX_VALUE * 10);
        columnas.get(6).setMaxWidth(1f * Integer.MAX_VALUE * 10);
        columnas.get(7).setMaxWidth(1f * Integer.MAX_VALUE * 15);
    }
    
    @FXML
    private void verMensaje(Mensaje mensaje) {
        if(mensaje.getTexto() != null) {
            configurarLabelMensaje(mensaje.getTexto());   
            mensajeDao.marcarComoLeido(mensaje.getId());
            cargarMensajes();
        }
    }
    
    @FXML
    public void responder(){
        Mensaje mensajeSeleccionado = tablaMensajes.getSelectionModel().getSelectedItem();
        this.layout.cargarPantalla("contacto", ContactoController.class.getResource("Contacto.fxml"));
        ((ContactoController) this.layout.getCotroller("contacto")).configurarParaUsuario(
            mensajeSeleccionado.getIdAutor(),
            mensajeSeleccionado.getNombreAutor());
        this.layout.mostrarComoPantallaActual("contacto");
    }
    
    private void configurarBotonesAcciones() {
       btnContacto.managedProperty().bind(btnContacto.visibleProperty()); 
       btnResponder.managedProperty().bind(btnResponder.visibleProperty());
       
       btnResponder.disableProperty().bind(tablaMensajes.getSelectionModel().selectedItemProperty().isNull());

       btnResponder.setVisible(this.layout.getUsuario().getTipoUsuario().equals(TipoUsuario.EMPLEADO));
       btnContacto.setVisible(this.layout.getUsuario().getTipoUsuario().equals(TipoUsuario.CLIENTE)); 
    }
}

