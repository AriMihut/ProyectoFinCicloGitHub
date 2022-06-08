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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class ServicioController extends ControladorConNavegabilidad implements Initializable{
    
    @FXML private TextField nombreServicio, precio;
    @FXML private TableView<Servicio> tablaServicios;
    private ObservableList<Servicio> servicios = FXCollections.observableArrayList();
    private Servicio serviciosParaModificar;
    private DAOServicio servicioDao;
    @FXML private RadioButton CEREMONIA, GASTRONOMIA, MUSICA, FOTOGRAFIA, VIDEO, TRANSPORTE;
    @FXML ToggleGroup group;
    
    @FXML private TableColumn<Servicio, Integer> idColumn;
    @FXML private TableColumn<Servicio, Enum> tipoServicioColumn;
    @FXML private TableColumn<Servicio, String> nombreServicioColumn;
    @FXML private TableColumn<Servicio, Double> precioColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            servicioDao = new DAOServicio();
            configurarRadioBoxServicios();
            mostrar();
            controlarTamanoColumnas();
            
        } catch (SQLException ex) {
            System.out.println("Error DaoServicio initialize " + ex.getMessage());
        }
        configurarRadioBoxServicios();
    }
    
    private void configurarRadioBoxServicios(){
        CEREMONIA.setSelected(true);     
    }
    
     @FXML
    public void anadir(){
        Servicio servicio = new Servicio();
            servicio.setId(serviciosParaModificar == null ? 0 : serviciosParaModificar.getId());
            if (CEREMONIA.isSelected()) {
                       servicio.setTipoServicio(Servicio.TipoServicio.CEREMONIA);
                    }
            if (GASTRONOMIA.isSelected()) {
                       servicio.setTipoServicio(Servicio.TipoServicio.GASTRONOMIA);
                    }
            if (MUSICA.isSelected()) {
                       servicio.setTipoServicio(Servicio.TipoServicio.MUSICA);
                    }
            if (FOTOGRAFIA.isSelected()) {
                       servicio.setTipoServicio(Servicio.TipoServicio.FOTOGRAFIA);
                    }
            if (VIDEO.isSelected()) {
                       servicio.setTipoServicio(Servicio.TipoServicio.VIDEO);
            }
            if (TRANSPORTE.isSelected()) {
                       servicio.setTipoServicio(Servicio.TipoServicio.TRANSPORTE);
            }    
      
            servicio.setNombreServicio(nombreServicio.getText());
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
        System.out.println("tipoServ = " + servicio.getTipoServicio());
        servicio.getTipoServicio();
        switch(servicio.getTipoServicio().toString()) {
            case "CEREMONIA" :
                CEREMONIA.setSelected(true);
                break;
            case "GASTRONOMIA" :
                GASTRONOMIA.setSelected(true);
                break;
            case "MUSICA" :
                MUSICA.setSelected(true);
                break;
            case "FOTOGRAFIA" :
                FOTOGRAFIA.setSelected(true);
                break;
            case "VIDEO" :
                VIDEO.setSelected(true);
                break;
            case "TRANSPORTE" :
              TRANSPORTE.setSelected(true);
              break;
      }
        nombreServicio.setText(servicio.getNombreServicio());
        precio.setText(String.valueOf(servicio.getPrecio()));
        serviciosParaModificar = servicio;
      
    }
    
    @FXML
    public void eliminar(){
        Servicio servicio = tablaServicios.getSelectionModel().getSelectedItem();
        servicioDao.eliminar(servicio);
        mostrar();
    }

    public void mostrar() {
        
        tablaServicios.getItems().clear();
        
        idColumn.setCellValueFactory(new PropertyValueFactory<Servicio, Integer>("id"));
        tipoServicioColumn.setCellValueFactory(new PropertyValueFactory<Servicio, Enum>("tipoServicio"));
        nombreServicioColumn.setCellValueFactory(new PropertyValueFactory<Servicio, String>("nombreServicio"));
        precioColumn.setCellValueFactory(new PropertyValueFactory<Servicio, Double>("precio"));
     
        List<Servicio> serviciosAMostrar = servicioDao.buscarTodos();
        servicios.addAll(serviciosAMostrar);
        tablaServicios.setItems(servicios);
       
    }

    private void clear() {
        CEREMONIA.setSelected(true);
        GASTRONOMIA.setSelected(false);
        MUSICA.setSelected(false);
        FOTOGRAFIA.setSelected(false);
        VIDEO.setSelected(false);
        TRANSPORTE.setSelected(false);
        nombreServicio.clear();
        precio.clear();
    }

    private void controlarTamanoColumnas() {
       tablaServicios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<TableColumn<Servicio, ?>> columnas = tablaServicios.getColumns();
        
        columnas.get(0).setMaxWidth(1f * Integer.MAX_VALUE * 10); 
        columnas.get(1).setMaxWidth(1f * Integer.MAX_VALUE * 30); 
        columnas.get(2).setMaxWidth(1f * Integer.MAX_VALUE * 35); 
        columnas.get(3).setMaxWidth(1f * Integer.MAX_VALUE * 25);
    }
    
}
