package com.amm.finciclo.proyectofinciclo;

import dao.DAOServicio;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class CompraController extends ControladorConNavegabilidad implements Initializable{
    
    private @FXML Button btnAnadir, btnDescartar, contactar, volverAtras;
    private @FXML ComboBox<Servicio> comboServicios;
    private @FXML Label labelCantidad;
    private @FXML ListView servicioListView;
    
    private DAOServicio servicioDao;
    private ObservableList<Compra> compras = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            servicioDao = new DAOServicio();
            configurarComboServicios(servicioDao.buscarTodos());
            configurarBotones();
            configurarServiciosListView();

            
        }catch (SQLException ex) {
            System.out.println("Error en el initialize de CompraController " + ex.getMessage());
        }
    }
    
    private void configurarComboServicios(ArrayList<Servicio> listaServicios){
        List<Servicio> serviciosEncontrados = listaServicios;
        comboServicios.getItems().addAll(serviciosEncontrados); 
        comboServicios.setCellFactory(new Callback<ListView<Servicio>,ListCell<Servicio>>(){
                @Override
                public ListCell<Servicio> call(ListView<Servicio> servicio){
                    return new ListCell<Servicio>(){
                        @Override
                        protected void updateItem(Servicio servicio, boolean empty) {
                            super.updateItem(servicio, empty);
                            if (servicio != null && !empty) {
                                setText(servicio.getNombreServicio()+ " " + servicio.getPrecio() + "€");
                                setGraphic(null);
                            } else {
                                setText("Seleccione uno");
                                setGraphic(null);
                            }
                        }
                    } ;
                }
            });  
        comboServicios.setButtonCell(new ListCell<Servicio>() {
            @Override
            public void updateItem(Servicio servicio, boolean empty) {
                super.updateItem(servicio, empty);
                if (servicio != null) {
                    setText(servicio.getNombreServicio()+ " " + servicio.getPrecio() + "€");;
                    setGraphic(null);
                } else {
                    setText("Seleccione uno");
                    setGraphic(null);
                }

            }
        }); 
    }
    
    @FXML
    public void anadirACesta(){
        Servicio servicioSeleccionado = comboServicios.getSelectionModel().getSelectedItem();
        servicioListView.getItems().add(servicioSeleccionado);
        calcularPrecioTotalServicios();
    }
    
    @FXML
    public void descartar(){
        Servicio servicioParaDescartar = (Servicio) servicioListView.getSelectionModel().getSelectedItem();
        servicioListView.getItems().remove(servicioParaDescartar);
        calcularPrecioTotalServicios();
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

    private void configurarBotones() {
        btnAnadir.disableProperty().bind(comboServicios.getSelectionModel().selectedItemProperty().isNull());
        btnDescartar.disableProperty().bind(servicioListView.getSelectionModel().selectedItemProperty().isNull());  
    }

    private void configurarServiciosListView() {
       servicioListView.setCellFactory(new Callback<ListView<Servicio>,ListCell<Servicio>>(){
                @Override
                public ListCell<Servicio> call(ListView<Servicio> servicio){
                    return new ListCell<Servicio>(){
                        @Override
                        protected void updateItem(Servicio servicio, boolean empty) {
                            super.updateItem(servicio, empty);
                            if (servicio != null && !empty) {
                                setText(servicio.getNombreServicio()+ " " + servicio.getPrecio() + "€");
                                setGraphic(null);
                            } else {
                                setText("");
                                setGraphic(null);
                            }
                        }
                    };
                }
        });
    }
    
    private void calcularPrecioTotalServicios(){
      /*  int precioTotal = 0;
        //Forech
        for(Object  servicio : servicioListView.getItems()){
            precioTotal += ((Servicio)servicio).getPrecio();
        }
        
        //for
        for(int i = 0; i < servicioListView.getItems().size(); i++){
            precioTotal += ((Servicio) servicioListView.getItems().get(i)).getPrecio();
        }*/
     
        double precioTotalServicios = servicioListView.getItems().stream().mapToDouble(servicio -> ((Servicio)servicio).getPrecio()).sum();
        labelCantidad.setText(String.valueOf(precioTotalServicios));
    }
    
}
