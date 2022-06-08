package com.amm.finciclo.proyectofinciclo;

import dao.DAOUsuario;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class ClientesController extends ControladorConNavegabilidad implements Initializable {
    
    @FXML private TableView<Usuario> tablaClientes;
    @FXML private VBox pantallaInfoClientes;
    private ObservableList<Usuario> clientes = FXCollections.observableArrayList();
    private DAOUsuario daoUsuario;
   
    @FXML private TableColumn<Cliente, String> dniColumn;
    @FXML private TableColumn<Cliente, String> nombreColumn;
    @FXML private TableColumn<Cliente, String> sexoColumn;
    @FXML private TableColumn<Cliente, Long> telefonoColumn;
    @FXML private TableColumn<Cliente, String> emailColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            daoUsuario = new DAOUsuario();
            configuracionTabla();
            controlarTamanoColumnas();
            mostrar();
        } catch (SQLException ex) {
            System.out.println("Error en el initialize ClientesController " + ex.getMessage());
        }
    }
    
    public void mostrar(){
       
            tablaClientes.getItems().clear();
            List<Usuario> clientesAMostrar = daoUsuario.buscarClientes();
            clientes.addAll(clientesAMostrar);
            tablaClientes.setItems(clientes);
        
    }
    
    private void configuracionTabla() {
        dniColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("dni"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
        sexoColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("sexo"));
        telefonoColumn.setCellValueFactory(new PropertyValueFactory<Cliente, Long>("telefono"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("email"));
    }

    private void controlarTamanoColumnas() {
       tablaClientes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<TableColumn<Usuario, ?>> columnas = tablaClientes.getColumns();
        
        columnas.get(0).setMaxWidth(1f * Integer.MAX_VALUE * 20); 
        columnas.get(1).setMaxWidth(1f * Integer.MAX_VALUE * 20); 
        columnas.get(2).setMaxWidth(1f * Integer.MAX_VALUE * 20); 
        columnas.get(3).setMaxWidth(1f * Integer.MAX_VALUE * 20); 
        columnas.get(4).setMaxWidth(1f * Integer.MAX_VALUE * 20); 
    }
    
}
