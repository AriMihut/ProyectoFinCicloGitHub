package dao;

import com.amm.finciclo.proyectofinciclo.Servicio;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
                        "tipoServicio ENUM('CEREMONIA', 'GASTRONOMIA', 'MUSICA', 'FOTOGRAFIA', 'VIDEO', 'TRANSPORTE'), " +
                        "nombreServicio VARCHAR(50), " +
                        "precio DOUBLE)";   
                sentencia.executeUpdate(sql);
    }
   }
    
    @FXML
    public void anadir(Servicio servicio){
           
        try (
            Connection conexionDataBase =
            DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
            Statement statement = conexionDataBase.createStatement();
            String sql = "INSERT INTO servicio(tipoServicio, nombreServicio, precio) " 
                    + "VALUES ('" + servicio.getTipoServicio()+ "', '" 
                    + servicio.getNombreServicio()+"', "
                    + servicio.getPrecio()+ ");";  
            statement.executeUpdate(sql);  
            
          } catch (SQLException ex) {
                System.out.println("Error al añadir en servicio " + ex.getMessage());
          }      
        
        }
    
    @FXML
    public void modificar(Servicio servicio) {
        
         try(
            Connection conexionDataBase =
            DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
            Statement statement = conexionDataBase.createStatement();
            String sql = "UPDATE servicio set tipoServicio='" + servicio.getTipoServicio()+ 
                    "', nombreServicio='" + servicio.getNombreServicio()+
                    "', precio=" + servicio.getPrecio()+
                    " WHERE id=" + servicio.getId();
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
            throw new RuntimeException("Ocurrió un error al eliminar el registro de servicio " + e.getMessage());
        }
     }
    
    public ArrayList<Servicio> buscarTodos(){
        
        ArrayList<Servicio> servicios = new ArrayList<>();
        try{
            Connection conexionDataBase = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD);
            Statement  statement = conexionDataBase.createStatement();
            String sql = "SELECT * FROM servicio ORDER BY id";
            ResultSet resultset = statement.executeQuery(sql);
            
        while(resultset.next()){
            Servicio servicio = new Servicio();
            servicio.setId(resultset.getInt("id"));
            servicio.setTipoServicio(Servicio.TipoServicio.valueOf(resultset.getString("tipoServicio")));
            servicio.setNombreServicio(resultset.getString("nombreServicio"));
            servicio.setPrecio(resultset.getDouble("precio"));
            servicios.add(servicio);
            }
          
        }catch (SQLException ex) {
          System.out.println("No posible mostrar los datos de la tabla servicio " + ex.getMessage());
       }
        return servicios;
    }
    
}
