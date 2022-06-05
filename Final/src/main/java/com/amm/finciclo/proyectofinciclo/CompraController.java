package com.amm.finciclo.proyectofinciclo;

import dao.DAOServicio;
import dao.DAOVenta;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class CompraController extends ControladorConNavegabilidad implements Initializable{
    
    private final String ESTILO_ERROR = "campo-error";
    
    private @FXML TextField filtroNombre, filtroApellidos, filtroTelefono, filtroEmail, 
            filtroNumeroTarjeta, filtroCVVTarjeta;
    private @FXML DatePicker filtroValidezTarjeta;
    private @FXML Button btnAnadir, btnDescartar;
    private @FXML ComboBox<Servicio> comboServicios;
    private @FXML ToolBar buttons;
    private @FXML Label labelCantidad, nombreError, apellidoError, validezTelefonoError, validezEmail, validezNTarjetaError, 
            cvvTarjetaError, validezTarjetaError;
    private @FXML Label etiquetaAviso;
    private @FXML ListView servicioListView;
    private @FXML VBox pantallaElegirServicios;
    private @FXML HBox formularioFinalizarCompra;
    
    private DAOServicio servicioDao;
    private DAOVenta ventaDao;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
            servicioDao = new DAOServicio();
            ventaDao = new DAOVenta();       
     
            configurarComboServicios(servicioDao.buscarTodos());
            
            configurarBotones();
            
            configurarServiciosListView();
            
            formularioFinalizarCompra.managedProperty().bind(formularioFinalizarCompra.visibleProperty());
            pantallaElegirServicios.managedProperty().bind(pantallaElegirServicios.visibleProperty());
            buttons.managedProperty().bind(buttons.visibleProperty());
        
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
                                setText("Seleccione un servicio: ");
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
                    setText("Seleccione un servicio: ");
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
    public void finalizarCompra(){
       prepararDatosUsuario();
       formularioFinalizarCompra.setVisible(true);
       pantallaElegirServicios.setVisible(false);     
    }
    
    @FXML
    public void pagar(){
        
        if(todosCamposCubiertos() && sonEtiquetasErrorInvisibles()){            
                   
            
            servicioListView.getItems().forEach(servicio -> {
                ventaDao.anadir(new Venta(this.layout.getUsuario().getId(), 
                    getCodigoConjunto(),
                    ((Servicio) servicio).getId(),
                    ((Servicio) servicio).getPrecio()));
            });
            clear();
            mostrarAviso("Compra realizada con éxito. Muchas gracias!");
        } else {
            mostrarAviso("Todos los campos deben estar correctamente cubiertos");
            System.out.println("El pago no se ha podido finalizar. Por favor, inténtelo de nuevo. "
                    + "Si vuelve a tener el mismo error, contacte con su banco. Muchas gracias!");
            sonEtiquetasErrorInvisibles();
        }
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
    
    @FXML
    public void volverAPantallaElegirServicios(){
        pantallaElegirServicios.setVisible(true);
        formularioFinalizarCompra.setVisible(false);
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
        double precioTotalServicios = servicioListView.getItems().stream().mapToDouble(servicio -> ((Servicio)servicio).getPrecio()).sum();
        labelCantidad.setText(String.valueOf(precioTotalServicios));
    }

    private boolean todosCamposCubiertos() {
        return !filtroNombre.getText().isEmpty() && !filtroApellidos.getText().isEmpty() && !filtroTelefono.getText().isEmpty() 
                && !filtroEmail.getText().isEmpty() && !filtroNumeroTarjeta.getText().isEmpty() && !filtroCVVTarjeta.getText().isEmpty()
                && filtroValidezTarjeta.getValue() != null;
    }
    
    public void clear(){
        filtroNumeroTarjeta.clear();
        filtroCVVTarjeta.clear();
        filtroValidezTarjeta.setValue(null);
        comboServicios.setValue(null);
        servicioListView.getItems().clear();
        labelCantidad.setText(null);
    }

    private void mostrarAviso(String text) {
       etiquetaAviso.setText(text);
       etiquetaAviso.setVisible(true);
    }

    private void prepararDatosUsuario() {
       Usuario usuario = this.layout.getUsuario();
       filtroNombre.setText(!usuario.getNombre().isEmpty()? usuario.getNombre(): "");
       filtroApellidos.setText(!usuario.getApellido().isEmpty()? usuario.getApellido(): "" );
       filtroEmail.setText(!usuario.getEmail().isEmpty() ? usuario.getEmail() : "");
       filtroTelefono.setText(!String.valueOf(usuario.getTelefono()).isEmpty()? 
               String.valueOf(usuario.getTelefono()): "");
    }

    private String getCodigoConjunto() {
        LocalDateTime fecha = LocalDateTime.now();
        return fecha.toString().replaceAll("[^0-9]", "").substring(0, 14);
    }
    
    private boolean esLimiteDigitosCorrecto(){
        return this.filtroNumeroTarjeta.getText().matches("^\\d{16}$");
    }
    
    private boolean esNombreCorrecto() {
        return this.filtroNombre.getText().matches("[A-Z][a-zA-Z]*");
    }

    private boolean esApellidoCorrecto() {
        return this.filtroApellidos.getText().matches( "[a-zA-z]+([ '-][a-zA-Z]+)*" );
    } 
    
    private boolean esTelefonoCorrecto() {
        return this.filtroTelefono.getText().matches("(\\+34|0034|34)?[ -]*(6|7)[ -]*([0-9][ -]*){8}");
    }
    
    private boolean esEmailCorrecto() {
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(this.filtroEmail.getText());
        return matcher.matches();
    }

    static final private String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private boolean esCVVCorrecto() {
        return this.filtroCVVTarjeta.getText().matches("\\d{3}");
    }
    
    public static boolean validarFecha(String fecha) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
     try {
          format.parse(fecha);
          return true;
    }
     catch(ParseException e){
          return false;
    }
    }

    private boolean sonEtiquetasErrorInvisibles() {
        
        nombreError.setVisible(!esNombreCorrecto());
        aplicarEstilosDeError(filtroNombre, !esNombreCorrecto());
        apellidoError.setVisible(!esApellidoCorrecto());
        aplicarEstilosDeError(filtroApellidos, !esApellidoCorrecto());
        validezTelefonoError.setVisible(!esTelefonoCorrecto());
        aplicarEstilosDeError(filtroTelefono, !esTelefonoCorrecto());
        validezEmail.setVisible(!esEmailCorrecto());
        aplicarEstilosDeError(filtroEmail, !esEmailCorrecto());
        validezNTarjetaError.setVisible(!esLimiteDigitosCorrecto());
        aplicarEstilosDeError(filtroNumeroTarjeta, !esLimiteDigitosCorrecto());
        cvvTarjetaError.setVisible(!esCVVCorrecto());
        aplicarEstilosDeError(filtroCVVTarjeta, !esCVVCorrecto());
        
        return !nombreError.isVisible() && !apellidoError.isVisible() && !validezTelefonoError.isVisible() 
                && !validezEmail.isVisible() && !validezNTarjetaError.isVisible() && !cvvTarjetaError.isVisible();
    }
    
    private void aplicarEstilosDeError(Node nodo, boolean condicion) {
       
        if(condicion) {      
            nodo.getStyleClass().add(ESTILO_ERROR);
        } else {
            nodo.getStyleClass().removeIf(s -> s.equals(ESTILO_ERROR));
        }
    }
    
}
