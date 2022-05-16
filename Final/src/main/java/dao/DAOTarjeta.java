package dao;

import com.amm.finciclo.proyectofinciclo.Tarjeta;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXML;

public class DAOTarjeta {
    
    public static final String URL_CONEXION = "jdbc:mysql://localhost:3306/proyectofinciclo";
    public static final String USUARIO_BDD = "root";
    public static final String PASSWORD_BDD = "";
    
    public DAOTarjeta() throws SQLException{
       crearTablaSiNoExiste();
    }

    private void crearTablaSiNoExiste() throws SQLException {
        
        try(
                Connection conexion = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
                Statement sentencia = conexion.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS tarjeta" +
                          "(id INTEGER auto_increment NOT NULL PRIMARY KEY, " +
                          "numTarjeta LONG, " +
                          "cvv INTEGER, " +
                          "idCliente INTEGER, " +
                          "FOREIGN KEY (idCliente) REFERENCES cliente (id) )";
                sentencia.executeUpdate(sql);
        }
   }
    
    @FXML
    public void anadir(Tarjeta tarjeta){
           
        try (
            Connection conexionDataBase =
            DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
            Statement statement = conexionDataBase.createStatement();
            String sql = "INSERT INTO tarjeta(numTarjeta,cvv) " +
                   "VALUES ('" + tarjeta.getNumTarjeta()+ "', " 
                    + tarjeta.getCvv()+ ");";
            statement.executeUpdate(sql);  
            
          } catch (SQLException ex) {
                System.out.println("Error al introducir información en la tabla tarjeta " + ex.getMessage());
          }        
        
        }
   
    @FXML
    public void eliminar(Tarjeta tarjeta) {
        try{
            Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD);
            Statement statement = conexionDB.createStatement();
            String sql = "DELETE FROM tarjeta WHERE id=" + tarjeta.getId();
            statement.executeUpdate(sql);
        }catch(Exception e){
            throw new RuntimeException("Ocurrió un error al eliminar registro de la tabla tarjeta " + e.getMessage());
        }
     } 
    
}
