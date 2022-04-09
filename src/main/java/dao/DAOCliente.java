package dao;
import com.amm.finciclo.proyectofinciclo.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;

public class DAOCliente {
    
    public static final String URL_CONEXION = "jdbc:mysql://localhost:3306/proyectofinciclo";
    public static final String USUARIO_BDD = "root";
    public static final String PASSWORD_BDD = "";
    
    public DAOCliente() throws SQLException{
        crearTablaSiNoExiste();
    }

    private void crearTablaSiNoExiste() throws SQLException {
        
        try(
                  Connection conexion = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
                  Statement sentencia = conexion.createStatement();
                  String sql = "CREATE TABLE IF NOT EXISTS cliente" +
                          "(id INTEGER(15) auto_increment, " +
                          "dni VARCHAR(50), " +
                          "nombre VARCHAR(50), " +
                          "apellido VARCHAR(50), " +
                          "FOREIGN KEY (idServicioContratado) REFERENCES servicio (idServicio)," +
                          "FOREIGN KEY (idProductoComprado) REFERENCES producto (idProducto)," +
                          "pagoRealizado INTEGER(50))";
                  sentencia.executeUpdate(sql);
    }
   }
    
    @FXML
    public void anadir(Cliente cliente){
           
        try (
            Connection conexionDataBase =
            DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
            Statement statement = conexionDataBase.createStatement();
            String sql = "INSERT INTO cliente(id, dni, nombre, apellido, idServicioContratado, idProductoComprado, pagoRealizado) " +
                   "VALUES ('" + cliente.getId()+ "', '" + cliente.getDni()+ "', " + 
                    cliente.getNombre()+ ", '" + 
                    cliente.getApellido()+ ", '" +
                    cliente.getIdServicioContratado()+ ", '" +
                    cliente.getIdProductoComprado()+ ", '" +
                    cliente.getPagoRealizado()+ "')";
            statement.executeUpdate(sql);  
            
          } catch (SQLException ex) {
                System.out.println("Error al introducir información en la base de datos.");
          }        
        
        }
    
    public void modificar(Cliente cliente) {
        
         try(
            Connection conexionDataBase =
            DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
            Statement statement = conexionDataBase.createStatement();
            String sql = "UPDATE cliente set id='" + cliente.getId() + "', "
                    + "dni='" + cliente.getDni()+ "', nombre=" + cliente.getNombre() + "', "
                    + "apellido='" + cliente.getApellido()+ "', "
                    + "idServicioContratado='" + cliente.getIdServicioContratado()+"', " 
                    + "idProductoComprado='" + cliente.getIdProductoComprado()+"', "
                    + "pagoRealizado='" + cliente.getPagoRealizado() +
                    "' WHERE id=" + cliente.getId();
            statement.executeUpdate(sql);
          } catch (SQLException ex) {
                System.out.println("Error al introducir información en la base de datos.");
            }      
            
        }
    
    @FXML
    public void eliminar(Cliente cliente) {
        try{
            Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD);
            Statement statement = conexionDB.createStatement();
            String sql = "DELETE FROM cliente WHERE id=" + cliente.getId();
            statement.executeUpdate(sql);
        }catch(Exception e){
            throw new RuntimeException("Ocurrió un error al eliminar la información " + e.getMessage());
        }
     }
    
    public List<Cliente> buscarTodos(){
        
        List<Cliente> clientes = new ArrayList<>();
        try{
            Connection conexionDataBase = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD);
            Statement  statement = conexionDataBase.createStatement();
            String sql = "SELECT * FROM cliente ORDER BY id";
            ResultSet resultset = statement.executeQuery(sql);
            
        while(resultset.next()){
             Cliente cliente = new Cliente();
             cliente.setId(resultset.getInt("id"));
             cliente.setDni(resultset.getString("dni"));
             cliente.setNombre(resultset.getString("nombre"));
             cliente.setApellido(resultset.getString("apellido"));
             cliente.setIdServicioContratado(resultset.getInt("idServicioContratado"));
             cliente.setIdProductoComprado(resultset.getInt("idProductoComprado"));
             cliente.setPagoRealizado(resultset.getInt("pagoRealizado"));
             clientes.add(cliente);
            }
          
        }catch (SQLException ex) {
          System.out.println("No posible mostrar los datos de la tabla");
            System.out.println(ex.getMessage());
       }
        return clientes;
    }
    
    
}
