package dao;

import com.amm.finciclo.proyectofinciclo.Mensaje;
import com.amm.finciclo.proyectofinciclo.TipoUsuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;

public class DAOMensaje {
    
    public static final String URL_CONEXION = "jdbc:mysql://localhost:3306/proyectofinciclo";
    public static final String USUARIO_BDD = "root";
    public static final String PASSWORD_BDD = "";
    
    public DAOMensaje() throws SQLException{
       crearTablaSiNoExiste();
    }

    private void crearTablaSiNoExiste() throws SQLException {
        
        try(
                Connection conexion = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
                Statement sentencia = conexion.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS mensaje" +
                          "(id INTEGER auto_increment NOT NULL PRIMARY KEY, " +
                          "nombreAutor VARCHAR(50), " +
                          "asunto VARCHAR(50), " +
                          "tipoUsuario ENUM('admin', 'cliente', 'empleado', 'proveedor'), " +
                          "esUrgente BOOLEAN, " +
                          "FOREIGN KEY (idCliente) REFERENCES cliente (id) )";
                sentencia.executeUpdate(sql);
        }
   }
    
    @FXML
    public void anadir(Mensaje mensaje){
           
        try (
            Connection conexionDataBase =
            DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
            Statement statement = conexionDataBase.createStatement();
            String sql = "INSERT INTO mensaje(nombreAutor, asunto, tipoUsuario, esUrgente) " +
                   "VALUES ('" + mensaje.getNombreAutor()+ "', '" 
                    + mensaje.getAsunto()+ "', '" 
                    + mensaje.getTipoUsuario().name() + "', " 
                    + mensaje.getEsUrgente()+ "');";
            statement.executeUpdate(sql);  
            
          } catch (SQLException ex) {
                System.out.println("Error al introducir información en la tabla mensaje " + ex.getMessage());
          }        
        
        }
    
    public void modificar(Mensaje mensaje) {
        
         try(
            Connection conexionDataBase =
            DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
            Statement statement = conexionDataBase.createStatement();
            String sql = "UPDATE mensaje set nombreAutor='" + mensaje.getNombreAutor()+ 
                    "', asunto='" + mensaje.getAsunto()+ 
                    "', tipoUsuario='" + mensaje.getTipoUsuario().name()+ 
                    "', esUrgente=" + mensaje.getEsUrgente()+
                    "' WHERE id=" + mensaje.getId();
            statement.executeUpdate(sql);
          } catch (SQLException ex) {
                System.out.println("Error al modificar la tabla mensaje " + ex.getMessage());
            }      
            
        }
    
    @FXML
    public void eliminar(Mensaje mensaje) {
        try{
            Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD);
            Statement statement = conexionDB.createStatement();
            String sql = "DELETE FROM mensaje WHERE id=" + mensaje.getId();
            statement.executeUpdate(sql);
        }catch(Exception e){
            throw new RuntimeException("Ocurrió un error al eliminar registro de la tabla mensaje " + e.getMessage());
        }
     }
    
    public List<Mensaje> buscarTodos(){
        
        List<Mensaje> mensajes = new ArrayList<>();
        try{
            Connection conexionDataBase = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD);
            Statement  statement = conexionDataBase.createStatement();
            String sql = "SELECT * FROM mensaje ORDER BY id";
            ResultSet resultset = statement.executeQuery(sql);
            
        while(resultset.next()){
            Mensaje mensaje = new Mensaje();
            mensaje.setId(resultset.getInt("id"));
            mensaje.setNombreAutor(resultset.getString("nombreAutor"));
            mensaje.setAsunto(resultset.getString("asunto"));
            mensaje.setTipoUsuario(TipoUsuario.valueOf(resultset.getString("tipoUsuario").toUpperCase()));
            mensaje.setEsUrgente(resultset.getBoolean("esUrgente"));
            mensajes.add(mensaje);
            }
          
        }catch (SQLException ex) {
          System.out.println("No posible mostrar los datos de la tabla mensaje " + ex.getMessage());
       }
        return mensajes;
    }
    
}
