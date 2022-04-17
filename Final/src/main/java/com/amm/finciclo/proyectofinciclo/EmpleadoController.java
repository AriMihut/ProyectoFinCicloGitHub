package com.amm.finciclo.proyectofinciclo;

import dao.DAODepartamentos;
import dao.DAOEmpleado;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class EmpleadoController extends ControladorConNavegabilidad implements Initializable {
    
   /* @FXML
    TextField dni, nombre, apellido;

    @FXML
    DatePicker fechaAlta;
    
    @FXML
    DatePicker fechaBaja;
    
    @FXML
    ComboBox<String> departamentos;
    
    @FXML
    RadioButton idServicioOfrecido;
    
    @FXML
    CheckBox esEncargado;
    
    @FXML
    TableView<Empleado> tablaPersonal;*/
    
    DAOEmpleado daoEmpleado;
    DAODepartamentos daoDepartamento;
    
    int id;
    int idDepartamento;
    private Date date;
    private LocalDate localDate;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       /*     configurarComboBox();
        try {
            daoEmpleado = new DAOEmpleado();
            daoDepartamento = new DAODepartamentos();
            cargarEmpleadosDeLaBase();
        } catch (SQLException ex) {
            System.out.println("ex");
        }*/
        
    }  
    
   /* private void configurarComboBox(){
        departamentos.getItems().addAll("1", "2", "3", "4", "5");
        departamentos.getSelectionModel().selectFirst();
        
    }
    
    private void configurarCheckBox() {
        esEncargado.setSelected(true);
        esEncargado.selectedProperty().addListener((obs, oldV, newV) -> {
            if(newV){
                esEncargado.setSelected(!newV);
            }
        });
         esEncargado.selectedProperty().addListener((obs, oldV, newV) -> {
            if(newV){
                esEncargado.setSelected(!newV);
            }
        });
    }
    
    @FXML
    public void anadir(){
        Empleado personal = new Empleado();
        personal.setId(id);
        personal.setDni(dni.getText());
        personal.setNombre(nombre.getText());
        personal.setApellido(apellido.getText());
        personal.setFechaAlta(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        personal.setFechaBaja(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        personal.setIdServicioOfrecido(id);
        personal.setEsEncargado(true);
        id = 0;
    }
    
    @FXML
    public void prepararModificar(){
        Empleado personal = tablaPersonal.getSelectionModel().getSelectedItem();
        dni.setText(personal.getDni());
        nombre.setText(personal.getNombre());
        apellido.setText(personal.getApellido());
        date = personal.getFechaAlta();
        localDate = new java.sql.Date(date.getTime()).toLocalDate();
        fechaAlta.setValue(localDate);
        date = personal.getFechaBaja();
        localDate = new java.sql.Date(date.getTime()).toLocalDate();
        fechaBaja.setValue(localDate);
        idServicioOfrecido.setSelected(true);
        idDepartamento = personal.getIdDepartamento();
        esEncargado.setSelected(personal.getEsEncargado());
        id = personal.getId();
        
        /*List<Departamento> itemsDepartamentos = departamentos.getItems();
        for(Departamento departamento: itemsDepartamentos){
            if(departamento.getIdDepartamento() == personal.getIdDepartamento()){
                departamentos.setValue(departamento);
                return;
            }
        }*/
  /*  }*/
    
  /*  @FXML
    public void eliminar(){
        Empleado personal = tablaPersonal.getSelectionModel().getSelectedItem();
        daoEmpleado.eliminar(personal);
        cargarEmpleadosDeLaBase();
    }
    
    public void atras() throws IOException{
      //  App.setRoot("PantallaHome");
    }

    private void cargarEmpleadosDeLaBase() {
       /* ObservableList<Personal> empleados = FXCollections.observableArrayList();
        List<Personal> empleadosEncontrados = daoEmpleado.buscarTodos();
        empleados.addAll(empleadosEncontrados);
        tablaPersonal.setItems(empleados);*/
   /* }*/
    
}
