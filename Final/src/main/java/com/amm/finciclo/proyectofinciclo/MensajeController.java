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
    @FXML private TableColumn<Mensaje, String> asuntoColumn;
    @FXML private TableColumn<Mensaje, TipoUsuario> tipoUsuarioColumn;
    @FXML private TableColumn<Mensaje, Boolean> esUrgenteColumn;
    @FXML private TableColumn<Mensaje, Integer> idDestinatarioColumn;
    @FXML private TableColumn<Mensaje, String> verMensajeColumn;
    
    @FXML private Button btnResponder;
    @FXML private Button btnContacto;
    
    
    private ObservableList<Mensaje> mensajes = FXCollections.observableArrayList();
    private DAOMensaje mensajeDao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            mensajeDao = new DAOMensaje();
         //   pantallaMensaje.addEventHandler(new  MouseAdapter() {
         //   }, eh);
            //verMensaje();
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
       /* botonVerMensaje.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent evt ) {
                contenedorMensaje.setVisible(true);
            }
        } );*/
        
    }
    
    @FXML
    public void atras(){
        this.layout.cargarPantalla("cliente", ClienteController.class.getResource("Cliente.fxml"));
        this.layout.mostrarComoPantallaActual("cliente");
    }
    
    public void mostrar(){
        configurarBotonesAcciones();
        tablaMensajes.getItems().clear();
        configurarCeldas();
        ObservableList<Mensaje> mensajes = FXCollections.observableArrayList();
        List<Mensaje> mensajesParaMostrar = new ArrayList<>();
        if(this.layout.getUsuario().getTipoUsuario().equals(TipoUsuario.CLIENTE)){
            //caso de usuario cliente
           mensajesParaMostrar = mensajeDao.buscarTodos(Optional.of(this.layout.getUsuario().getId()));
        } else {
            //caso de usuario empleado
           mensajesParaMostrar = mensajeDao.buscarTodos(Optional.empty());
        }
        
        /*Igual pero en ternario
        mensajesParaMostrar = mensajeDao.buscarTodos(idCliente.isPresent()? idCliente : Optional.empty()); */
        
        mensajes.addAll(mensajesParaMostrar);
        tablaMensajes.setItems(mensajes);
    }

    private void configurarCeldas() {
        idMensajeColumn.setCellValueFactory(new PropertyValueFactory<Mensaje, Integer>("id"));
        idAutorColumn.setCellValueFactory(new PropertyValueFactory<Mensaje, Integer>("idAutor"));
        asuntoColumn.setCellValueFactory(new PropertyValueFactory<Mensaje, String>("asunto"));
        tipoUsuarioColumn.setCellValueFactory(new PropertyValueFactory<Mensaje, TipoUsuario>("tipoUsuario"));
        esUrgenteColumn.setCellValueFactory(new PropertyValueFactory<Mensaje, Boolean>("esUrgente"));
        esUrgenteColumn.setCellFactory(tc -> new TableCell<Mensaje, Boolean>() {
          @Override
            protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if(!empty && item != null) {
                setText(item ? "Sí": "No");
            }
              
           }
        });    
        idDestinatarioColumn.setCellValueFactory(new PropertyValueFactory<Mensaje, Integer>("idDestinatario"));
        verMensajeColumn.setCellValueFactory(new PropertyValueFactory<Mensaje, String>("texto"));
        verMensajeColumn.setCellFactory(tc -> new TableCell<Mensaje, String>() {
          @Override
            protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
                if(!empty && item != null) {
                    Button botonVerMensaje = new Button("Ver mensaje");
                    botonVerMensaje.setOnAction((ActionEvent event) -> {
                        verMensaje(item);
                    });
                    /*if (!botonVerMensaje.setOnMouseClicked) {
                        contenedorMensaje.setVisible(false);
                    
                    //un listener se aplica sobre un nodo
                    //para establecer un listener, hay que establecerlo sobre un nodo, que puede ser un boton
                    //setOnClick - captura los clicks que se hacen sobre ese boton
                    
                    }*/
                    setGraphic(botonVerMensaje);
                    setText("");
                }  
           }
        });
    }
    
    private void controlarTamanoColumnas() {
        tablaMensajes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<TableColumn<Mensaje, ?>> columnas = tablaMensajes.getColumns();
        
        columnas.get(0).setMaxWidth(1f * Integer.MAX_VALUE * 10); 
        columnas.get(1).setMaxWidth(1f * Integer.MAX_VALUE * 10); 
        columnas.get(2).setMaxWidth(1f * Integer.MAX_VALUE * 25); 
        columnas.get(3).setMaxWidth(1f * Integer.MAX_VALUE * 15); 
        columnas.get(4).setMaxWidth(1f * Integer.MAX_VALUE * 15);
        columnas.get(5).setMaxWidth(1f * Integer.MAX_VALUE * 10);
        columnas.get(6).setMaxWidth(1f * Integer.MAX_VALUE * 15);
        
    }
    
    @FXML
    private void verMensaje(String texto) {
        configurarLabelMensaje(texto);            
    }
    
      @FXML
    public void responder(){
    }

    private void configurarBotonesAcciones() {
       btnContacto.managedProperty().bind(btnContacto.visibleProperty());
       btnResponder.managedProperty().bind(btnResponder.visibleProperty());

       btnResponder.setVisible(this.layout.getUsuario().getTipoUsuario().equals(TipoUsuario.EMPLEADO));
       btnContacto.setVisible(this.layout.getUsuario().getTipoUsuario().equals(TipoUsuario.CLIENTE)); 
    }
    
    
}
