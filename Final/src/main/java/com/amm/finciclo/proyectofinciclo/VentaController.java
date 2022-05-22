package com.amm.finciclo.proyectofinciclo;

import dao.DAOVenta;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;

public class VentaController extends ControladorConNavegabilidad implements Initializable{

    @FXML TextField filtroIdVenta, filtroValorTotalVenta;
    @FXML private DatePicker fechaVenta;
    @FXML private TableView<Venta> tablaVentas;
    private ObservableList<Venta> ventas = FXCollections.observableArrayList();
    private DAOVenta ventaDao;
  
    @FXML private TableColumn<Venta, String> codigoConjuntoColumn;
    @FXML private TableColumn<Venta, DatePicker> fechaVentaColumn;
    @FXML private TableColumn<Venta, Double> valorTotalVentaColumn;
    @FXML private TableColumn<Venta, String> nombreUsuarioColumn;
    @FXML private TableColumn<Venta, ArrayList<String>> nombreServiciosColumn;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            ventaDao = new DAOVenta();
            configuracionTabla();
            controlarTamanoColumnas();
            mostrar();
        } catch (SQLException ex) {
            System.out.println("Error en el initialize venta " + ex.getMessage());
        }
    }
    
    @FXML
    public void atras(){
        this.layout.cargarPantalla("pagHome", PantallaHomeController.class.getResource("PantallaHome.fxml"));
        this.layout.mostrarComoPantallaActual("pagHome");
    }

    public void mostrar() {
        tablaVentas.getItems().clear();
     
        List<Venta> ventasAMostrar = ventaDao.buscarTodas();
        ventas.addAll(ventasAMostrar);
        tablaVentas.setItems(ventas);
    }
    
    private void configuracionTabla() {
        codigoConjuntoColumn.setCellValueFactory(new PropertyValueFactory<Venta, String>("codigoConjunto"));
        fechaVentaColumn.setCellValueFactory(new PropertyValueFactory<Venta, DatePicker>("fechaVenta"));
        valorTotalVentaColumn.setCellValueFactory(new PropertyValueFactory<Venta, Double>("valorTotalVenta"));
        nombreUsuarioColumn.setCellValueFactory(new PropertyValueFactory<Venta, String>("nombreUsuario"));
        nombreServiciosColumn.setCellValueFactory(new PropertyValueFactory<Venta, ArrayList<String>>("nombreServicios"));
        nombreServiciosColumn.setCellFactory(tc -> new TableCell<Venta, ArrayList<String>>() {
          @Override
            protected void updateItem(ArrayList<String> nombresServicios, boolean empty) {
            super.updateItem(nombresServicios, empty);
            if(!empty && nombresServicios != null) {
                setText(nombresServicios.size() + " servicios");
                Tooltip tooltip = new Tooltip();
                tooltip.setText(nombresServicios.stream().collect(Collectors.joining(", ")));
                this.setTooltip(tooltip);
                setGraphic(null);
            } else {
                 setText("");
                 setGraphic(null);
            }
              
           }
        });
    }

    private void controlarTamanoColumnas() {
       tablaVentas.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<TableColumn<Venta, ?>> columnas = tablaVentas.getColumns();
        
        columnas.get(0).setMaxWidth(1f * Integer.MAX_VALUE * 25); 
        columnas.get(1).setMaxWidth(1f * Integer.MAX_VALUE * 20); 
        columnas.get(2).setMaxWidth(1f * Integer.MAX_VALUE * 15); 
        columnas.get(3).setMaxWidth(1f * Integer.MAX_VALUE * 20); 
        columnas.get(4).setMaxWidth(1f * Integer.MAX_VALUE * 20); 
    }
    
}

    

