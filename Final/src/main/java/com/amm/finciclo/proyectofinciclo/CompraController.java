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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CompraController extends ControladorConNavegabilidad implements Initializable{
    
    private @FXML Button anadirACesta, descartar, contactar, volverAtras;
    private @FXML ComboBox<Servicio> servicios;
    private @FXML TextField filtroTotalServicios;
    
    private DAOServicio servicioDao;
    private ObservableList<Compra> compras = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            servicioDao = new DAOServicio();
            
            List<Servicio> serviciosEncontrados = servicioDao.buscarTodos();
            servicios.getItems().addAll(serviciosEncontrados);
            configurarServicios();
            
        }catch (SQLException ex) {
            System.out.println("Error en el initialize de CompraController " + ex.getMessage());
        }
    }
    
    private void configurarServicios(){
        servicios.getSelectionModel().selectFirst();      
    }
    
    @FXML
    public void anadirACesta(){
        
    }
    
    @FXML
    public void descartar(){
        
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
    
}
