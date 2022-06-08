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

public class ClientesController extends ControladorConNavegabilidad implements Initializable {
    
    @FXML private TableView<Usuario> tablaClientes;
    private ObservableList<Usuario> clientes = FXCollections.observableArrayList();
    private DAOUsuario daoUsuario;
   
    @FXML private TableColumn<Usuario, String> dniColumn;
    @FXML private TableColumn<Usuario, String> nombreColumn;
    @FXML private TableColumn<Usuario, String> sexoColumn;
    @FXML private TableColumn<Usuario, Long> telefonoColumn;
    @FXML private TableColumn<Usuario, String> emailColumn;

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
        dniColumn.setCellValueFactory(new PropertyValueFactory<Usuario, String>("dni"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombre"));
        sexoColumn.setCellValueFactory(new PropertyValueFactory<Usuario, String>("sexo"));
        telefonoColumn.setCellValueFactory(new PropertyValueFactory<Usuario, Long>("telefono"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Usuario, String>("email"));
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
