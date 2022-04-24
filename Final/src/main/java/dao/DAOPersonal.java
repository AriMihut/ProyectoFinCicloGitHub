package dao;
import com.amm.finciclo.proyectofinciclo.Personal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;

public class DAOPersonal {
    
    public static final String URL_CONEXION = "jdbc:mysql://localhost:3306/proyectofinciclo";
    public static final String USUARIO_BDD = "root";
    public static final String PASSWORD_BDD = "";
    
    public DAOPersonal() throws SQLException{
        crearTablaSiNoExiste();
    }

    private void crearTablaSiNoExiste() throws SQLException {
        
        try(
                Connection conexion = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
                    Statement sentencia = conexion.createStatement();
                    String sql = "CREATE TABLE IF NOT EXISTS personal" +
                          "(id INTEGER auto_increment PRIMARY KEY NOT NULL, " +
                          "dni VARCHAR(50) NULL, " +
                          "nombre VARCHAR(50) NULL, " +
                          "fechaAlta TIMESTAMP NULL, " +
                          "fechaBaja TIMESTAMP NULL, " +
                          "sueldo DOUBLE NULL, " +
                          "idDepartamento INTEGER, " +
                          "tipoServicio VARCHAR, " +  
                          "FOREIGN KEY (idDepartamento) REFERENCES departamento (idDepartamento), " +
                          "FOREIGN KEY (tipoServicio) REFERENCES servicio (tipoServicio)"  ;
                    sentencia.executeUpdate(sql);
        }
    }
    
    @FXML
    public void anadir(Personal personal){
           
       /* try (
            Connection conexionDataBase =
            DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
            Statement statement = conexionDataBase.createStatement();
            String sql = "INSERT INTO empleado(id, dni, nombre, apellido, fechaAlta, "
                    + "fechaBaja, idServicioOfrecido, esEncargado) " +
                   "VALUES ('" + empleado.getId()+ "', '" + empleado.getDni()+ "', " + 
                    empleado.getNombre()+ ", '" + 
                    empleado.getApellido()+ ", '" +
                    new Timestamp(empleado.getFechaAlta().getTime())+ ", '" +
                    new Timestamp(empleado.getFechaBaja().getTime())+ ", '" +
                    empleado.getIdServicioOfrecido()+ ", '"+
                    empleado.getEsEncargado() + "')";
            statement.executeUpdate(sql);  
            
          } catch (SQLException ex) {
                System.out.println("Error al introducir información en la base de datos.");
          }  */    
        
        }
    
    @FXML
    public void modificar(Personal personal) {
        
         try(
            Connection conexionDataBase =
            DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
            Statement statement = conexionDataBase.createStatement();
            String sql = "UPDATE personal set id=" + personal.getId() + 
                    ", dni='" + personal.getDni() + 
                    "', nombre='" + personal.getNombre() + 
                    "', fechaAlta='" + new Timestamp(personal.getFechaAlta().getTime()) +
                    "', fechaBaja='" + new Timestamp(personal.getFechaBaja().getTime()) +
                    "', sueldo=" + personal.getSueldo() +
                    ", idDepartamento=" + personal.getIdDepartamento() +
                    ", tipoServicio'" + personal.getTipoServicio()+
                    "' WHERE id=" + personal.getId();
            statement.executeUpdate(sql);
          } catch (SQLException ex) {
                System.out.println("Error al modificar la tabla personal " + ex.getMessage());
            }      
            
        }
   
    @FXML
    public void eliminar(Personal personal) {
        try{
            Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD);
            Statement statement = conexionDB.createStatement();
            String sql = "DELETE FROM personal WHERE id=" + personal.getId();
            statement.executeUpdate(sql);
        }catch(Exception e){
            throw new RuntimeException("Ocurrió un error al eliminar la información " + e.getMessage());
        }
     }
    
    public List<Personal> buscarTodos(){
        
        List<Personal> empleados = new ArrayList<>();
        try{
            Connection conexionDataBase = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD);
            Statement  statement = conexionDataBase.createStatement();
            String sql = "SELECT * FROM personal ORDER BY id";
            ResultSet resultset = statement.executeQuery(sql);
            
        while(resultset.next()){
             Personal personal = new Personal();
             personal.setId(resultset.getInt("id"));
             personal.setDni(resultset.getString("dni"));
             personal.setNombre(resultset.getString("nombre"));
             personal.setFechaAlta(resultset.getDate("fechaAlta"));
             personal.setFechaBaja(resultset.getDate("fechaBaja"));
             personal.setSueldo(resultset.getDouble("sueldo"));
             personal.setIdDepartamento(resultset.getInt("idDepartamento"));
             personal.setTipoServicio(resultset.getString("tipoServicio"));
             empleados.add(personal);
            }
          
        }catch (SQLException ex) {
          System.out.println("No posible mostrar los datos de la tabla personal " + ex.getMessage());
            System.out.println(ex.getMessage());
       }
        return empleados;
    }
    
}
