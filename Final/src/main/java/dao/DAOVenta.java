package dao;

import com.amm.finciclo.proyectofinciclo.Venta;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javafx.fxml.FXML;

public class DAOVenta {
    
    public static final String URL_CONEXION = "jdbc:mysql://localhost:3306/proyectofinciclo";
    public static final String USUARIO_BDD = "root";
    public static final String PASSWORD_BDD = "";
    
    public DAOVenta() throws SQLException{
        crearTablaSiNoExiste();
    }

    private void crearTablaSiNoExiste() throws SQLException {
        
        try(
                Connection conexion = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
                Statement sentencia = conexion.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS venta" +
                        "(id INTEGER auto_increment NOT NULL PRIMARY KEY, " +
                        "idConjunto INTEGER, " +
                        "fechaVenta TIMESTAMP, " +
                        "valorTotalVenta DOUBLE, " +
                        "idUsuario INTEGER, " +
                        "idServicio INTEGER, " +
                        "FOREIGN KEY (idUsuario) REFERENCES usuario(id), " +
                        "FOREIGN KEY (idServicio) REFERENCES servicio(id))";
                System.out.println("SQL === " + sql);
                sentencia.executeUpdate(sql);
    }
   }
    
    @FXML
    public void anadir(Venta venta){
        
        Date date = new Date();

        long timeInMilliSeconds = date.getTime();
        java.sql.Date date1 = new java.sql.Date(timeInMilliSeconds);
           
        try (
            Connection conexionDataBase =
            DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
            Statement statement = conexionDataBase.createStatement();
            String sql = "INSERT INTO venta(idConjunto, fechaVenta, valorTotalVenta, idUsuario, idServicio) " 
                    + "VALUES (" + venta.getIdConjunto()+ ", '" 
                    + new Timestamp(System.currentTimeMillis()) + "', "
                    + venta.getValorTotalVenta()+", "
                    + venta.getIdUsuario()+", "
                    + venta.getIdServicio() + ")";
            System.out.println("SQL venta = " + sql);
            statement.executeUpdate(sql);  
            
            
          } catch (SQLException ex) {
                System.out.println("Error al añadir en venta " + ex.getMessage());
          }      
        
        }
    
    @FXML
    public void modificar(Venta venta) {
         try(
            Connection conexionDataBase =
            DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
            Statement statement = conexionDataBase.createStatement();
            String sql = "UPDATE venta set fechaVenta='" + new Timestamp(venta.getFechaVenta().getTime())+ 
                    "', valorTotalVenta=" + venta.getValorTotalVenta()+
                    ", idUsuario=" + venta.getIdUsuario()+
                    ", idServicio=" + venta.getIdServicio()+
                    " WHERE id=" + venta.getId();
            statement.executeUpdate(sql);
          } catch (SQLException ex) {
                System.out.println("Error al modificar la tabla venta " + ex.getMessage());
            }      
        }
    
    @FXML
    public void eliminar(Venta venta) {
        try{
            Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD);
            Statement statement = conexionDB.createStatement();
            String sql = "DELETE FROM venta WHERE id=" + venta.getId();
            statement.executeUpdate(sql);
        }catch(Exception e){
            throw new RuntimeException("Ocurrió un error al eliminar registro de venta " + e.getMessage());
        }
     }
    
    public ArrayList<Venta> buscarTodas(){
        
        ArrayList<Venta> ventas = new ArrayList<>();
        try{
            Connection conexionDataBase = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD);
            Statement  statement = conexionDataBase.createStatement();
            String sql = "SELECT * FROM venta ORDER BY id";
            ResultSet resultset = statement.executeQuery(sql);
            
        while(resultset.next()){
            Venta venta = new Venta();
            venta.setId(resultset.getInt("id"));
            venta.setIdConjunto(resultset.getInt("idConjunto"));
            venta.setFechaVenta(resultset.getDate("fechaVenta"));
            venta.setValorTotalVenta(resultset.getDouble("valorTotalVenta"));
            venta.setIdUsuario(resultset.getInt("idUsuario"));
            venta.setIdServicio(resultset.getInt("idServicio"));
            ventas.add(venta);
            }
          
        }catch (SQLException ex) {
          System.out.println("No posible mostrar los datos de la tabla venta " + ex.getMessage());
       }
        return ventas;
    }
    
}
