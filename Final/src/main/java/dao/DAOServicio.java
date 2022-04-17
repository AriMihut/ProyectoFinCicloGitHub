package dao;

import com.amm.finciclo.proyectofinciclo.Servicio;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;

public class DAOServicio {
    
    public static final String URL_CONEXION = "jdbc:mysql://localhost:3306/proyectofinciclo";
    public static final String USUARIO_BDD = "root";
    public static final String PASSWORD_BDD = "";
    
    public DAOServicio() throws SQLException{
        crearTablaSiNoExiste();
    }

    private void crearTablaSiNoExiste() throws SQLException {
        
        try(
                Connection conexion = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
                Statement sentencia = conexion.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS servicio" +
                        "(id INTEGER auto_increment NOT NULL PRIMARY KEY, " +
                        "tipoServicio VARCHAR(50) NOT NULL UNIQUE, " +
                        "precio DOUBLE )";
                       /* "FOREIGN KEY (idCliente) REFERENCES cliente(id), " +
                        "FOREIGN KEY (idEmpleado) REFERENCES empleado(id)*/ 
                sentencia.executeUpdate(sql);
    }
   }
    
    @FXML
    public void anadir(Servicio servicio){
           
        try (
            Connection conexionDataBase =
            DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
            Statement statement = conexionDataBase.createStatement();
            String sql = "INSERT INTO servicio(tipoServicio, precio) " 
                    + "VALUES ('" + servicio.getTipoServicio()+ "', '" 
                    + servicio.getPrecio()+  "')";
            statement.executeUpdate(sql);  
            
          } catch (SQLException ex) {
                System.out.println("Error al añadir en servicio" + ex.getMessage());
          }      
        
        }
    
    @FXML
    public void modificar(Servicio servicio) {
        
         try(
            Connection conexionDataBase =
            DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
            Statement statement = conexionDataBase.createStatement();
            String sql = "UPDATE servicio set id='" + 
                    servicio.getId()+ 
                    "', tipoServicio='" + servicio.getTipoServicio()+ 
                    "', precio='" + servicio.getPrecio()+
                    "' WHERE id=" + servicio.getId();
            statement.executeUpdate(sql);
          } catch (SQLException ex) {
                System.out.println("Error al modificar la tabla servicio " + ex.getMessage());
            }      
            
        }
    
    @FXML
    public void eliminar(Servicio servicio) {
        try{
            Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD);
            Statement statement = conexionDB.createStatement();
            String sql = "DELETE FROM servicio WHERE id=" + servicio.getId();
            statement.executeUpdate(sql);
        }catch(Exception e){
            throw new RuntimeException("Ocurrió un error al eliminar registro de servicio " + e.getMessage());
        }
     }
    
    public List<Servicio> buscarTodos(){
        
        List<Servicio> servicios = new ArrayList<>();
        try{
            Connection conexionDataBase = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD);
            Statement  statement = conexionDataBase.createStatement();
            String sql = "SELECT * FROM servicio ORDER BY id";
            ResultSet resultset = statement.executeQuery(sql);
            
        while(resultset.next()){
             Servicio servicio = new Servicio();
             servicio.setId(resultset.getInt("id"));
             servicio.setTipoServicio(resultset.getString("tipoServicio"));
             servicio.setPrecio(resultset.getDouble("precio"));
             servicios.add(servicio);
            }
          
        }catch (SQLException ex) {
          System.out.println("No posible mostrar los datos de la tabla servicio " + ex.getMessage());
       }
        return servicios;
    }
    
}
