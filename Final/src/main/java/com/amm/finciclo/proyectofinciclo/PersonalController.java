package com.amm.finciclo.proyectofinciclo;

import dao.DAOPersonal;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PersonalController extends ControladorConNavegabilidad implements Initializable {
    
    @FXML TextField filtroIdPersonal, filtroDniPersonal, filtroNombrePersonal, filtroSueldo;

    @FXML DatePicker filtroFechaAlta, filtroFechaBaja;
    
    @FXML ComboBox<Servicio> servicios;
    
    @FXML TableView<Personal> tablaPersonal;
    
    private Personal personalAModificar;
    private ObservableList<Personal> empleados = FXCollections.observableArrayList();
    
    private DAOPersonal daoPersonal;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            daoPersonal = new DAOPersonal();
            /*   configurarDepartamentos();
            configurarServicios();
            try {
            daoPersonal = new DAOPersonal();
            daoDepartamento = new DAODepartamento();
            cargarEmpleadosDeLaBase();
            } catch (SQLException ex) {
            System.out.println("Error en PersonalController " + ex.getMessage());
            }*/
        } catch (SQLException ex) {
            System.out.println("Error initialize en PersonalController " + ex.getMessage());
        }
        
    }  
    
 /*   private void configurarDepartamentos(){
        departamentos.getSelectionModel().selectFirst();
    }
    
    private void configurarServicios(){
        departamentos.getSelectionModel().selectFirst();
    }*/
    
    @FXML
    public void anadir(){
        /*Personal personal = new Personal();
        personal.setId(personalAModificar == null ? 0 : personalAModificar.getId());
        personal.setDni(filtroDniPersonal.getText());
        personal.setNombre(filtroNombrePersonal.getText());
        LocalDate localDateAlta = filtroFechaAlta.getValue();
        personal.setFechaAlta(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        LocalDate localDateBaja = filtroFechaBaja.getValue();
        personal.setFechaBaja(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        personal.setSueldo(filtroNombrePersonal.getText());
        personal.setIdDepartamento(departamentos.getSelectionModel().getSelectedItem().getIdDepartamento());
        personal.setTipoServicio(servicios.getSelectionModel().getSelectedItem().getTipoServicio());
        
        if(personal.getId() == 0){
                daoPersonal.anadir(personal); 
            } else {
                daoPersonal.modificar(personal);
                personalAModificar = null;
            }
        
            mostrar();
            clear();*/
    }
    
    @FXML
    public void prepararModificar(){
        /*Personal personal = tablaPersonal.getSelectionModel().getSelectedItem();
        dni.setText(personal.getDni());
        nombre.setText(personal.getNombre());
        date = personal.getFechaAlta();
        localDate = new java.sql.Date(date.getTime()).toLocalDate();
        fechaAlta.setValue(localDate);
        date = personal.getFechaBaja();
        localDate = new java.sql.Date(date.getTime()).toLocalDate();
        fechaBaja.setValue(localDate);
        idServicioOfrecido.setSelected(true);
        idDepartamento = personal.getIdDepartamento();
        esEncargado.setSelected(personal.getEsEncargado());
        id = personal.getId();*/
        
        /*List<Departamento> itemsDepartamentos = departamentos.getItems();
        for(Departamento departamento: itemsDepartamentos){
            if(departamento.getIdDepartamento() == personal.getIdDepartamento()){
                departamentos.setValue(departamento);
                return;
            }
        }*/
    }
    
    @FXML
    public void eliminar(){
        Personal personal = tablaPersonal.getSelectionModel().getSelectedItem();
        daoPersonal.eliminar(personal);
        //cargarEmpleadosDeLaBase();
    }
    
    public void atras() throws IOException{
        this.layout.mostrarComoPantallaActual("pagHome");
    }

    /*private void cargarEmpleadosDeLaBase() {
        ObservableList<Personal> empleados = FXCollections.observableArrayList();
        List<Personal> empleadosEncontrados = daoEmpleado.buscarTodos();
        empleados.addAll(empleadosEncontrados);
        tablaPersonal.setItems(empleados);
    }*/

    private void mostrar() {
       
    }
    private void clear() {
        
    }
    
}
