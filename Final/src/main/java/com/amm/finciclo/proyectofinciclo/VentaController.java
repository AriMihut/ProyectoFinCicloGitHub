package com.amm.finciclo.proyectofinciclo;

import dao.DAOVenta;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class VentaController extends ControladorConNavegabilidad implements Initializable{

    @FXML TextField filtroIdVenta, filtroValorTotalVenta, filtroIdUsuario, filtroIdServicio;
    @FXML private DatePicker fechaVenta;
    @FXML private TableView<Venta> tablaVentas;
    private ObservableList<Venta> ventas = FXCollections.observableArrayList();
    private Venta ventasParaModificar;
    private DAOVenta ventaDao;
    
    @FXML private TableColumn<Venta, Integer> idVentaColumn;
    @FXML private TableColumn<Venta, DatePicker> fechaVentaColumn;
    @FXML private TableColumn<Venta, Double> valorTotalVentaColumn;
    @FXML private TableColumn<Venta, Integer> idClienteColumn;
    @FXML private TableColumn<Venta, Integer> idServicioColumn;
    
    private int id;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            ventaDao = new DAOVenta();
            controlarTamanoColumnas();
        } catch (SQLException ex) {
            System.out.println("Error en el initialize venta " + ex.getMessage());
        }
    }
    
    @FXML
    public void anadir(){
                  
    Venta venta = new Venta();
        venta.setId(ventasParaModificar == null ? 0 : ventasParaModificar.getId());
        LocalDate localDate = fechaVenta.getValue();
            venta.setFechaVenta(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            venta.setValorTotalVenta(Double.parseDouble(filtroValorTotalVenta.getText()));
            venta.setIdUsuario(Integer.parseInt(filtroIdUsuario.getText()));
            venta.setIdServicio(Integer.parseInt(filtroIdServicio.getText()));
         
        if(venta.getId() == 0){
            ventaDao.anadir(venta); 
        } else {
            ventaDao.modificar(venta);
            ventasParaModificar = null;
        }
        mostrar();
        clear();
        
    } 
    
    @FXML
    public void prepararModificar(){
        Venta venta = tablaVentas.getSelectionModel().getSelectedItem();
        Date date = venta.getFechaVenta();
        LocalDate localDate = new java.sql.Date(date.getTime()).toLocalDate();
        fechaVenta.setValue(localDate);
        filtroValorTotalVenta.setText(String.valueOf(venta.getValorTotalVenta()));
        
        //idCliente, idServicio
        
        id = venta.getId();
    }
 
    @FXML
    public void eliminar(){
        Venta venta = tablaVentas.getSelectionModel().getSelectedItem();
        ventaDao.eliminar(venta);
        mostrar();
    }
    
    @FXML
    public void atras(){
        this.layout.cargarPantalla("pagHome", PantallaHomeController.class.getResource("PantallaHome.fxml"));
        this.layout.mostrarComoPantallaActual("pagHome");
    }

    public void mostrar() {
        
        tablaVentas.getItems().clear();
        
        idVentaColumn.setCellValueFactory(new PropertyValueFactory<Venta, Integer>("id"));
        fechaVentaColumn.setCellValueFactory(new PropertyValueFactory<Venta, DatePicker>("fechaVenta"));
        valorTotalVentaColumn.setCellValueFactory(new PropertyValueFactory<Venta, Double>("valorTotalVenta"));
        idClienteColumn.setCellValueFactory(new PropertyValueFactory<Venta, Integer>("idCliente"));
        idServicioColumn.setCellValueFactory(new PropertyValueFactory<Venta, Integer>("idServicio"));
     
        List<Venta> ventasAMostrar = ventaDao.buscarTodas();
        ventas.addAll(ventasAMostrar);
        tablaVentas.setItems(ventas);
       
    }

    private void clear() {
       
        fechaVenta.setValue(null);
        filtroIdVenta.clear();
        filtroValorTotalVenta.clear();
        filtroIdUsuario.clear();
        filtroIdServicio.clear();
       
    }

    private void controlarTamanoColumnas() {
       tablaVentas.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<TableColumn<Venta, ?>> columnas = tablaVentas.getColumns();
        
        columnas.get(0).setMaxWidth(1f * Integer.MAX_VALUE * 10); 
        columnas.get(1).setMaxWidth(1f * Integer.MAX_VALUE * 35); 
        columnas.get(2).setMaxWidth(1f * Integer.MAX_VALUE * 35); 
        columnas.get(3).setMaxWidth(1f * Integer.MAX_VALUE * 10); 
        columnas.get(4).setMaxWidth(1f * Integer.MAX_VALUE * 10); 
        
    }
    
}

    

