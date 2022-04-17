package dao;
import com.amm.finciclo.proyectofinciclo.Empleado;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;

public class DAOEmpleado {
    
    public static final String URL_CONEXION = "jdbc:mysql://localhost:3306/proyectofinciclo";
    public static final String USUARIO_BDD = "root";
    public static final String PASSWORD_BDD = "";
    
    public DAOEmpleado() throws SQLException{
        crearTablaSiNoExiste();
    }

    private void crearTablaSiNoExiste() throws SQLException {
        
        try(
                Connection conexion = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
                    Statement sentencia = conexion.createStatement();
                    String sql = "CREATE TABLE IF NOT EXISTS empleado" +
                          "(id INTEGER(15) auto_increment, " +
                          "dni VARCHAR(50), " +
                          "nombre VARCHAR(50), " +
                          "apellido VARCHAR(50), " +
                          "fechaAlta TIMESTAMP, " +
                          "fechaBaja TIMESTAMP, " +
                          "sueldo DOUBLE, " +
                          "horasExtra INTEGER, " +
                          "idDepatamento INTEGER(15) REFERENCES depatamento (idDepartamento)";
                    sentencia.executeUpdate(sql);
    }
   }
    
    @FXML
    public void anadir(Empleado empleado){
           
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
    public void modificar(Empleado personal) {
        
         try(
            Connection conexionDataBase =
            DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
            Statement statement = conexionDataBase.createStatement();
            String sql = "UPDATE personal set id='" + personal.getId() + 
                    "', dni='" + personal.getDni() + 
                    "', nombre='" + personal.getNombre() + 
                    "', apellido='" + personal.getApellido()+ 
                    "', fechaAlta='" + new Timestamp(personal.getFechaAlta().getTime()) +
                    "', fechaBaja='" + new Timestamp(personal.getFechaBaja().getTime()) +
                    "', idServicioOfrecido'" + personal.getIdServicioOfrecido()+
                    "', esEncargado='" + personal.getEsEncargado()+
                    "' WHERE id=" + personal.getId();
            statement.executeUpdate(sql);
          } catch (SQLException ex) {
                System.out.println("Error al introducir información en la base de datos.");
            }      
            
        }
    
    @FXML
    public void eliminar(Empleado personal) {
        try{
            Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD);
            Statement statement = conexionDB.createStatement();
            String sql = "DELETE FROM personal WHERE id=" + personal.getId();
            statement.executeUpdate(sql);
        }catch(Exception e){
            throw new RuntimeException("Ocurrió un error al eliminar la información " + e.getMessage());
        }
     }
    
    public List<Empleado> buscarTodos(){
        
        List<Empleado> empleados = new ArrayList<>();
        try{
            Connection conexionDataBase = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD);
            Statement  statement = conexionDataBase.createStatement();
            String sql = "SELECT * FROM personal ORDER BY id";
            ResultSet resultset = statement.executeQuery(sql);
            
        while(resultset.next()){
             Empleado personal = new Empleado();
             personal.setId(resultset.getInt("id"));
             personal.setDni(resultset.getString("dni"));
             personal.setNombre(resultset.getString("nombre"));
             personal.setApellido(resultset.getString("apellido"));
             personal.setFechaAlta(resultset.getDate("fechaAlta"));
             personal.setFechaBaja(resultset.getDate("fechaBaja"));
             personal.setIdServicioOfrecido(resultset.getInt("idServicioOfrecido"));
             personal.setEsEncargado(resultset.getBoolean("esEncargado"));
             empleados.add(personal);
            }
          
        }catch (SQLException ex) {
          System.out.println("No posible mostrar los datos de la tabla");
            System.out.println(ex.getMessage());
       }
        return empleados;
    }
    
}
