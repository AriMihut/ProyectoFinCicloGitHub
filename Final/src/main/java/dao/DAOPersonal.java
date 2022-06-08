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
                          "nombre VARCHAR(255) NULL, " +
                          "fechaAlta TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                          "fechaBaja TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                          "sueldo DOUBLE NULL, " +
                          "idServicio INTEGER, " +  
                          "FOREIGN KEY (idServicio) REFERENCES servicio (id) )" ;
                    System.out.println("SQL personal " + sql);
                    sentencia.executeUpdate(sql);
        }
    }
 
    @FXML
    public void anadir(Personal personal){
           
        try (
            Connection conexionDataBase =
            DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
            Statement statement = conexionDataBase.createStatement();
            String sql = "INSERT INTO personal(dni, nombre, fechaAlta, fechaBaja, sueldo, idServicio) " +
                   "VALUES ('" + personal.getDni()+ 
                    "', '" + personal.getNombre()+ 
                    "', '" + new Timestamp(personal.getFechaAlta().getTime())+ 
                    "', '" + new Timestamp(personal.getFechaBaja().getTime())+ 
                    "', " + personal.getSueldo() +
                    ", " + personal.getIdServicio()+ ")";
            System.out.println("Insertar en personal " + sql
            );
            statement.executeUpdate(sql);  
            
        } catch (SQLException ex) {
            System.out.println("Error al introducir información en la tabla personal " + ex.getMessage());
        }     
        
    }
    
    @FXML
    public void modificar(Personal personal) {
        
         try(
            Connection conexionDataBase =
            DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
            Statement statement = conexionDataBase.createStatement();
            String sql = "UPDATE personal set dni='" + personal.getDni() + 
                    "', nombre='" + personal.getNombre() + 
                    "', fechaAlta='" + new Timestamp(personal.getFechaAlta().getTime()) +
                    "', fechaBaja='" + new Timestamp(personal.getFechaBaja().getTime()) +
                    "', sueldo=" + personal.getSueldo() +
                    " WHERE id=" + personal.getId();
            System.out.println("SQL modificar " + sql);
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
            throw new RuntimeException("Ocurrió un error al eliminar la información de la tabla personal " + e.getMessage());
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
             personal.setIdServicio(resultset.getInt("idServicio"));
             empleados.add(personal);
            }
          
        }catch (SQLException ex) {
          System.out.println("Error buscarTodos DAOPersonal " + ex.getMessage());
       }
        return empleados;
    }
}
