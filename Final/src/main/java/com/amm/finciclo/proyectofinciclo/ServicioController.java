package com.amm.finciclo.proyectofinciclo;

import dao.DAOServicio;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ServicioController extends ControladorConNavegabilidad implements Initializable{
    
    @FXML private TextField id, tipoServicio, precio;
    private @FXML ComboBox<String> comboboxTipoServicios;
    @FXML private TableView<Servicio> tablaServicios;
    private ObservableList<Servicio> servicios = FXCollections.observableArrayList();
    private Servicio serviciosParaModificar;
    private DAOServicio servicioDao;
    
    @FXML private TableColumn<Servicio, Integer> idColumna;
    @FXML private TableColumn<Servicio, String> tipoServicioColumna;
    @FXML private TableColumn<Servicio, Double> precioColumna;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            servicioDao = new DAOServicio();
            mostrar();
            controlarTamanoColumnas();
        } catch (SQLException ex) {
            System.out.println("Error DaoServicio " + ex.getMessage());
        }
        configurarComboBoxTipoServicios();
    }
    
    private void configurarComboBoxTipoServicios(){
        comboboxTipoServicios.getItems().addAll("Ceremonia", "Gastronomia", "Musica", "Fotografia", "Video", "Transporte");
        comboboxTipoServicios.getSelectionModel().selectFirst();      
    }
    
     @FXML
    public void anadir(){
      Servicio servicio = new Servicio();
            servicio.setId(serviciosParaModificar == null ? 0 : serviciosParaModificar.getId());
            servicio.setTipoServicio(comboboxTipoServicios.getSelectionModel().getSelectedItem());
            servicio.setPrecio(Double.parseDouble(precio.getText()));
         
            if(servicio.getId() == 0){
                servicioDao.anadir(servicio); 
            } else {
                servicioDao.modificar(servicio);
                serviciosParaModificar = null;
            }
            mostrar();
            clear();
    }
    
    @FXML
    public void prepararModificar(){
        Servicio servicio = tablaServicios.getSelectionModel().getSelectedItem();
        comboboxTipoServicios.getSelectionModel().select(servicio.getTipoServicio());
        precio.setText(String.valueOf(servicio.getPrecio()));
        serviciosParaModificar = servicio;
      
    }
    
    @FXML
    public void eliminar(){
        Servicio servicio = tablaServicios.getSelectionModel().getSelectedItem();
        servicioDao.eliminar(servicio);
        mostrar();
    }
    
    @FXML
    public void atras(){
      this.layout.mostrarComoPantallaActual("pagHome");
    }

    private void mostrar() {
        
        tablaServicios.getItems().clear();
        
        idColumna.setCellValueFactory(new PropertyValueFactory<Servicio, Integer>("id"));
        tipoServicioColumna.setCellValueFactory(new PropertyValueFactory<Servicio, String>("tipoServicio"));
        precioColumna.setCellValueFactory(new PropertyValueFactory<Servicio, Double>("precio"));
     
        List<Servicio> serviciosAMostrar = servicioDao.buscarTodos();
        servicios.addAll(serviciosAMostrar);
        tablaServicios.setItems(servicios);
       
    }

    private void clear() {
        comboboxTipoServicios.getSelectionModel().selectFirst();
        precio.clear();
    }

    private void controlarTamanoColumnas() {
       tablaServicios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<TableColumn<Servicio, ?>> columnas = tablaServicios.getColumns();
        
        columnas.get(0).setMaxWidth(1f * Integer.MAX_VALUE * 15); 
        columnas.get(1).setMaxWidth(1f * Integer.MAX_VALUE * 50); 
        columnas.get(2).setMaxWidth(1f * Integer.MAX_VALUE * 35); 
        
    }
    
}
