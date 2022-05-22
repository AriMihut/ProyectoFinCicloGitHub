package com.amm.finciclo.proyectofinciclo;

import dao.DAOPersonal;
import dao.DAOServicio;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PersonalController extends ControladorConNavegabilidad implements Initializable {
    
    @FXML TextField filtroIdPersonal, filtroDniPersonal, filtroNombrePersonal, filtroSueldo;
    @FXML DatePicker filtroFechaAlta, filtroFechaBaja;
    @FXML ComboBox<Servicio> servicios;
    @FXML TableView<Personal> tablaPersonal;
    
    private Personal personalAModificar;
    private ObservableList<Personal> empleados = FXCollections.observableArrayList();
    
    private DAOPersonal personalDao;
    private DAOServicio servicioDao;
    
    private int id;
    
    @FXML private TableColumn<Personal, Integer> idColumn;
    @FXML private TableColumn<Personal, String> dniColumn;
    @FXML private TableColumn<Personal, String> nombreColumn;
    @FXML private TableColumn<Personal, DatePicker> fechaAltaColumn;
    @FXML private TableColumn<Personal, DatePicker> fechaBajaColumn;
    @FXML private TableColumn<Personal, Double> sueldoColumn;
    @FXML private TableColumn<Personal, Integer> idServicioColumn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            personalDao = new DAOPersonal();
            servicioDao = new DAOServicio();
            controlarTamanoColumnas();
            configurarServicios();
            mostrar();
            
            List<Servicio> serviciosEncontrados = servicioDao.buscarTodos();
            servicios.getItems().addAll(serviciosEncontrados);
        
        } catch (SQLException ex) {
            System.out.println("Error initialize en PersonalController " + ex.getMessage());
        }
    }  
    
    private void configurarServicios(){
        servicios.getSelectionModel().selectFirst();
    }
    
    @FXML
    public void anadir(){
        Personal personal = new Personal();
        personal.setId(personalAModificar == null ? 0 : personalAModificar.getId());
        personal.setDni(filtroDniPersonal.getText());
        personal.setNombre(filtroNombrePersonal.getText());
        LocalDate localDateAlta = filtroFechaAlta.getValue();
        personal.setFechaAlta(Date.from(localDateAlta.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        LocalDate localDateBaja = filtroFechaBaja.getValue();
        personal.setFechaBaja(Date.from(localDateBaja.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        personal.setSueldo(Double.parseDouble(filtroSueldo.getText()));
        personal.setIdServicio(servicios.getSelectionModel().getSelectedItem().getId());
    
        if(personal.getId() == 0){
                personalDao.anadir(personal); 
            } else {
                personalDao.modificar(personal);
                personalAModificar = null;
            }
        
            mostrar();
            clear();
    }
    
    @FXML
    public void prepararModificar(){
        Personal personal = tablaPersonal.getSelectionModel().getSelectedItem();
        filtroDniPersonal.setText(personal.getDni());
        filtroNombrePersonal.setText(personal.getNombre());
        Date date = personal.getFechaAlta();
        LocalDate localDate = new java.sql.Date(date.getTime()).toLocalDate();
        filtroFechaAlta.setValue(localDate);
        Date date2 = personal.getFechaBaja();
        LocalDate localDate2 = new java.sql.Date(date2.getTime()).toLocalDate();
        filtroFechaBaja.setValue(localDate2);
        filtroSueldo.setText(String.valueOf(personal.getSueldo()));
        personalAModificar = personal;
        //id = personal.getId();
     
        List<Servicio> itemsServicio = servicios.getItems();
        for(Servicio servicio: itemsServicio){
            if(servicio.getId()== personal.getIdServicio()){
                servicios.setValue(servicio);
            }
        }
    }
    
    @FXML
    public void eliminar(){
        Personal personal = tablaPersonal.getSelectionModel().getSelectedItem();
        personalDao.eliminar(personal);
        mostrar();
    }
    
    public void atras() throws IOException{
        this.layout.cargarPantalla("pagHome", PantallaHomeController.class.getResource("PantallaHome.fxml"));
        this.layout.mostrarComoPantallaActual("pagHome");
    }

    private void mostrar() {
        
        tablaPersonal.getItems().clear();
        
        idColumn.setCellValueFactory(new PropertyValueFactory<Personal, Integer>("id"));
        dniColumn.setCellValueFactory(new PropertyValueFactory<Personal, String>("dni"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<Personal, String>("nombre"));
        fechaAltaColumn.setCellValueFactory(new PropertyValueFactory<Personal, DatePicker>("fechaAlta"));
        fechaBajaColumn.setCellValueFactory(new PropertyValueFactory<Personal, DatePicker>("fechaBaja"));
        sueldoColumn.setCellValueFactory(new PropertyValueFactory<Personal, Double>("sueldo"));
        idServicioColumn.setCellValueFactory(new PropertyValueFactory<Personal, Integer>("idServicio"));
  
        ObservableList<Personal> empleados = FXCollections.observableArrayList();
        List<Personal> empleadosAMostrar = personalDao.buscarTodos();
        empleados.addAll(empleadosAMostrar);
        tablaPersonal.setItems(empleados);
       
    }
    private void clear() {
        filtroDniPersonal.clear();
        filtroNombrePersonal.clear();
        filtroSueldo.clear();
        filtroFechaAlta.setValue(null);
        filtroFechaBaja.setValue(null);
        servicios.setValue(null);  
    }
    
    private void controlarTamanoColumnas(){
       tablaPersonal.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<TableColumn<Personal, ?>> columnas = tablaPersonal.getColumns();
        
        columnas.get(0).setMaxWidth(1f * Integer.MAX_VALUE * 5); 
        columnas.get(1).setMaxWidth(1f * Integer.MAX_VALUE * 15); 
        columnas.get(2).setMaxWidth(1f * Integer.MAX_VALUE * 15); 
        columnas.get(3).setMaxWidth(1f * Integer.MAX_VALUE * 20); 
        columnas.get(4).setMaxWidth(1f * Integer.MAX_VALUE * 20);
        columnas.get(5).setMaxWidth(1f * Integer.MAX_VALUE * 15);
        columnas.get(6).setMaxWidth(1f * Integer.MAX_VALUE * 10);
    }
    
}
