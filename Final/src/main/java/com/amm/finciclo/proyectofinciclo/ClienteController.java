package com.amm.finciclo.proyectofinciclo;

import dao.DAOCliente;
import java.io.IOException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ClienteController extends ControladorConNavegabilidad implements Initializable {
    
    @FXML TextField dni, nombre, sexo, telefono, email; 
    @FXML HBox formularioModificacion;
    @FXML VBox infoCliente;
    //@FXML private ComboBox<Servicio> servicios;
    @FXML private ComboBox<String> comboboxSexo;
    @FXML TextArea reclamarOContactar;
   // @FXML TableView<Cliente> tablaClientes;
   // @FXML Button anadir;
 //   @FXML Button prepararModificar;
  //  @FXML Button eliminar;
    @FXML Button atras;
    DAOCliente daoCliente;
    
    private int id;
    
    /*@FXML private TableColumn<Cliente, Integer> idColumna;
    @FXML private TableColumn<Cliente, String> dniColumna;
    @FXML private TableColumn<Cliente, String> nombreColumna;
    @FXML private TableColumn<Cliente, String> sexoColumna;
    @FXML private TableColumn<Cliente, Long> telefonoColumna;
    @FXML private TableColumn<Cliente, String> emailColumna;
    */
    private Cliente clientesAModificar;
    private ObservableList<Cliente> clientes = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
           // infoCliente.visibleProperty().bind(infoCliente.managedProperty());
           // formularioModificacion.visibleProperty().bind(formularioModificacion.managedProperty());
            controlarTamanoColumnas();
            //configurarComboBox();
            daoCliente = new DAOCliente();
            mostrar();
            //configurarServicios();
            
           /* comboboxSexo.setCellFactory(listView -> new ImagenListCell());
            comboboxSexo.setButtonCell(new ImagenListCell());*/
            
        } catch (SQLException ex) {
            System.out.println("Error initialize DaoCliente " + ex.getMessage());
        }
    }    
    
    /*private void configurarServicios(){
        servicios.getSelectionModel().selectFirst();      
    }*/
     
    private void configurarComboBox(){
      /*  comboboxSexo.getItems().addAll("Femenino", "Masculino");
        comboboxSexo.getSelectionModel().selectFirst();   */   
    }
    
    @FXML
    public void comprar(){
      
    }
    
    @FXML
    private void editarPerfil(){
        
        infoCliente.setVisible(false);
        infoCliente.setManaged(false);
        formularioModificacion.setVisible(true);
        formularioModificacion.setManaged(true);
        
        
        
         /*Cliente cliente = new Cliente();
        cliente.setDni(dni.getText());
        cliente.setNombre(nombre.getText());
        cliente.setSexo(comboboxSexo.getSelectionModel().getSelectedItem());
        cliente.setTelefono(Long.parseLong(telefono.getText()));
        cliente.setEmail(email.getText());
       
        if(cliente.getId() == 0){
            daoCliente.crearPerfil(cliente); 
        } else {
            daoCliente.modificar(cliente);
            clientesAModificar = null;
        }
        mostrar();
        clear();*/
    }
    
    @FXML
    public void contactar(){
        this.layout.mostrarComoPantallaActual("contacto");
    }
    
    
    @FXML
    public void editar(){
       /* Cliente cliente = tablaClientes.getSelectionModel().getSelectedItem();
        dni.setText(cliente.getDni());
        nombre.setText(cliente.getNombre());
        comboboxSexo.setValue(cliente.getSexo());
        telefono.setText(String.valueOf(cliente.getTelefono()));
        email.setText(cliente.getEmail());
        clientesAModificar = cliente;
        id = cliente.getId();*/
    
    }
    
    @FXML
    public void eliminar(){
       /* Cliente cliente = tablaClientes.getSelectionModel().getSelectedItem();
        daoCliente.eliminar(cliente);
        mostrar();*/
    }
    
    @FXML
    public void volverAtras() throws IOException{
       this.layout.mostrarComoPantallaActual("autentificacion");
    }
    
    private void mostrar() {
        
        /*tablaClientes.getItems().clear();
        
        idColumna.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("id"));
        dniColumna.setCellValueFactory(new PropertyValueFactory<Cliente, String>("dni"));
        nombreColumna.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
        sexoColumna.setCellValueFactory(new PropertyValueFactory<Cliente, String>("sexo"));
        telefonoColumna.setCellValueFactory(new PropertyValueFactory<Cliente, Long>("telefono"));
        emailColumna.setCellValueFactory(new PropertyValueFactory<Cliente, String>("email"));
     
        List<Cliente> clientesAMostrar = daoCliente.buscarTodos();
        clientes.addAll(clientesAMostrar);
        tablaClientes.setItems(clientes);*/
       
    }

    private void clear() {
       /* dni.clear();
        nombre.clear();
        comboboxSexo.getSelectionModel().selectFirst();
        telefono.clear();
        email.clear();*/
    }
    
    private void controlarTamanoColumnas(){
       /* tablaClientes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<TableColumn<Cliente, ?>> columnas = tablaClientes.getColumns();
        
        columnas.get(0).setMaxWidth(1f * Integer.MAX_VALUE * 10); 
        columnas.get(1).setMaxWidth(1f * Integer.MAX_VALUE * 15); 
        columnas.get(2).setMaxWidth(1f * Integer.MAX_VALUE * 20); 
        columnas.get(3).setMaxWidth(1f * Integer.MAX_VALUE * 15); 
        columnas.get(4).setMaxWidth(1f * Integer.MAX_VALUE * 20); 
        columnas.get(5).setMaxWidth(1f * Integer.MAX_VALUE * 20); */
       
    }
    
}
