package dao;
import com.amm.finciclo.proyectofinciclo.Cliente;
import com.amm.finciclo.proyectofinciclo.Proveedor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;

public class DAOProveedor {
    
    public static final String URL_CONEXION = "jdbc:mysql://localhost:3306/proyectofinciclo";
    public static final String USUARIO_BDD = "root";
    public static final String PASSWORD_BDD = "";
    
    public DAOProveedor() throws SQLException{
        crearTablaSiNoExiste();
    }

    private void crearTablaSiNoExiste() throws SQLException {
        
        try(
                  Connection conexion = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
                  Statement sentencia = conexion.createStatement();
                  String sql = "CREATE TABLE IF NOT EXISTS proveedor" +
                          "(id INTEGER(15) auto_increment, " +
                          "nombre VARCHAR(50), " +
                          "apellido VARCHAR(50), " +
                          "idProductoOfrecido INTEGER(15))";
                  sentencia.executeUpdate(sql);
    }
   }
    
    @FXML
    public void anadir(Proveedor proveedor){
           
        try (
            Connection conexionDataBase =
            DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
            Statement statement = conexionDataBase.createStatement();
            String sql = "INSERT INTO proveedor(id, nombre, apellido, idProductoOfrecido) " +
                   "VALUES ('" + proveedor.getId()+ "', '" +  
                    proveedor.getNombre()+ ", '" + 
                    proveedor.getApellido()+ ", '" +
                    proveedor.getIdProductoOfrecido()+ "')";
            statement.executeUpdate(sql);  
            
          } catch (SQLException ex) {
                System.out.println("Error al introducir información en la base de datos.");
          }        
        
        }
    
    public void modificar(Proveedor proveedor) {
        
         try(
            Connection conexionDataBase =
            DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
            Statement statement = conexionDataBase.createStatement();
            String sql = "UPDATE proveedor set id='" + proveedor.getId() + "', "
                    + "', nombre=" + proveedor.getNombre() + "', "
                    + "apellido='" + proveedor.getApellido()+ "', "
                    + "idProductoOfrecido='" + proveedor.getIdProductoOfrecido()+
                    "' WHERE id=" + proveedor.getId();
            statement.executeUpdate(sql);
          } catch (SQLException ex) {
                System.out.println("Error al introducir información en la base de datos.");
            }      
            
        }
    
    @FXML
    public void eliminar(Proveedor proveedor) {
        try{
            Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD);
            Statement statement = conexionDB.createStatement();
            String sql = "DELETE FROM proveedor WHERE id=" + proveedor.getId();
            statement.executeUpdate(sql);
        }catch(Exception e){
            throw new RuntimeException("Ocurrió un error al eliminar la información " + e.getMessage());
        }
     }
    
    public List<Proveedor> buscarTodos(){
        
        List<Proveedor> proveedores = new ArrayList<>();
        try{
            Connection conexionDataBase = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD);
            Statement  statement = conexionDataBase.createStatement();
            String sql = "SELECT * FROM proveedor ORDER BY id";
            ResultSet resultset = statement.executeQuery(sql);
            
        while(resultset.next()){
             Proveedor proveedor = new Proveedor();
             proveedor.setId(resultset.getInt("id"));
             proveedor.setNombre(resultset.getString("nombre"));
             proveedor.setApellido(resultset.getString("apellido"));
             proveedor.setIdProductoOfrecido(resultset.getInt("idProductoOfrecido"));
             proveedores.add(proveedor);
            }
          
        }catch (SQLException ex) {
          System.out.println("No posible mostrar los datos de la tabla");
            System.out.println(ex.getMessage());
       }
        return proveedores;
    }
    
    
}
