package com.amm.finciclo.proyectofinciclo;

import dao.DAOMensaje;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class MensajeController extends ControladorConNavegabilidad implements Initializable{
    
    @FXML private TableView<Mensaje> tablaMensajes;
    @FXML private ComboBox<Cliente> clientes;
    private DAOMensaje mensajeDao;
  
    @FXML private TableColumn<Mensaje, Integer> idMensajeColumn;
    @FXML private TableColumn<Mensaje, String> nombreAutorColumn;
    @FXML private TableColumn<Mensaje, String> asuntoColumn;
    @FXML private TableColumn<Mensaje, String> textoMensajeColumn;
    @FXML private TableColumn<Mensaje, TipoUsuario> tipoUsuarioColumn;
    @FXML private TableColumn<Mensaje, Boolean> esUrgenteColumn;
    @FXML private TableColumn<Mensaje, Integer> idUsuarioColumn;
    @FXML private TableColumn<Mensaje, Void> verMensajeColumn;
    
    private ObservableList<Mensaje> mensajes = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            mensajeDao = new DAOMensaje();
            mostrar();
            controlarTamanoColumnas();
            //configurarCheckBox();
        } catch (SQLException ex) {
            System.out.println("Error en el initialize de MensajeController " + ex.getMessage());
        }
    }
    
    @FXML
    public void contactar(){
        this.layout.cargarPantalla("contacto", ContactoController.class.getResource("Contacto.fxml"));
        this.layout.mostrarComoPantallaActual("contacto");
    }
    
    @FXML
    public void atras(){
        this.layout.cargarPantalla("cliente", ClienteController.class.getResource("Cliente.fxml"));
        this.layout.mostrarComoPantallaActual("cliente");
    }

    private void mostrar() {
        
        tablaMensajes.getItems().clear();
        
        idMensajeColumn.setCellValueFactory(new PropertyValueFactory<Mensaje, Integer>("id"));
        nombreAutorColumn.setCellValueFactory(new PropertyValueFactory<Mensaje, String>("nombreAutor"));
        asuntoColumn.setCellValueFactory(new PropertyValueFactory<Mensaje, String>("asunto"));
        textoMensajeColumn.setCellValueFactory(new PropertyValueFactory<Mensaje, String>("texto"));
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
        idUsuarioColumn.setCellValueFactory(new PropertyValueFactory<Mensaje, Integer>("idUsuario"));
        verMensajeColumn.setCellFactory(new Callback<TableColumn<Mensaje, Void>, TableCell<Mensaje, Void>>() {
            @Override
            public TableCell<Mensaje, Void> call(final TableColumn<Mensaje, Void> param) {
                final TableCell<Mensaje, Void> cell = new TableCell<Mensaje, Void>() {

                    private final Button btn = new Button("Ver Mensaje");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                           // llamar metodo mostrar mensaje -> mostrarMensaje(tablaMesnajes.getelectionModel().getSelectedItem().getTexto());
                           /*mostrarMensaje() - en este método 
                            hago visible etiqueta con texto de mensaje debajo de la tabla.
                           //añadir columna columna de leído
                           */
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        });
        
        
       /* new Callback<TableColumn<Data, Void>, TableCell<Data, Void>>() {
            @Override
            public TableCell<Data, Void> call(final TableColumn<Data, Void> param) {
                final TableCell<Data, Void> cell = new TableCell<Data, Void>() {

                    private final Button btn = new Button("Action");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Data data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);*/
        
        ObservableList<Mensaje> mensajes = FXCollections.observableArrayList();
        List<Mensaje> mensajesParaMostrar = mensajeDao.buscarTodos();
        mensajes.addAll(mensajesParaMostrar);
        tablaMensajes.setItems(mensajes);
    }

    private void controlarTamanoColumnas() {
        tablaMensajes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<TableColumn<Mensaje, ?>> columnas = tablaMensajes.getColumns();
        
        columnas.get(0).setMaxWidth(1f * Integer.MAX_VALUE * 10); 
        columnas.get(1).setMaxWidth(1f * Integer.MAX_VALUE * 20); 
        columnas.get(2).setMaxWidth(1f * Integer.MAX_VALUE * 30); 
        columnas.get(3).setMaxWidth(1f * Integer.MAX_VALUE * 25); 
        columnas.get(4).setMaxWidth(1f * Integer.MAX_VALUE * 15);
    }
    
}
