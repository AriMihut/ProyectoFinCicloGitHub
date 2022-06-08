package dao;

import com.amm.finciclo.proyectofinciclo.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
                          "(id INTEGER auto_increment NOT NULL PRIMARY KEY, " +
                          "dni VARCHAR(50), " +
                          "nombre VARCHAR(50), " +
                          "sexo VARCHAR(15), " +
                          "telefono LONG, " +
                          "email VARCHAR(50), " +
                          "idServicio INTEGER, " +
                          "FOREIGN KEY (idServicio) REFERENCES servicio (id) )";
                sentencia.executeUpdate(sql);
        }
   }
    
    public void modificar(Cliente cliente) {
        
         try(
            Connection conexionDataBase =
            DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
            Statement statement = conexionDataBase.createStatement();
            String sql = "UPDATE cliente set dni='" + cliente.getDni()+ 
                    "', nombre='" + cliente.getNombre() + 
                    "', sexo='" + cliente.getSexo() + 
                    "', telefono=" + cliente.getTelefono() +
                    ", email='" + cliente.getEmail() +
                    "' WHERE id=" + cliente.getId();
            statement.executeUpdate(sql);
          } catch (SQLException ex) {
                System.out.println("Error al modificar la tabla cliente " + ex.getMessage());
            }      
        }
    
    public ArrayList<Cliente> buscarTodos(){
        
        ArrayList<Cliente> clientes = new ArrayList<>();
        try{
            Connection conexionDataBase = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD);
            Statement  statement = conexionDataBase.createStatement();
            String sql = "SELECT * FROM servicio ORDER BY id";
            ResultSet resultset = statement.executeQuery(sql);
            
        while(resultset.next()){
            Cliente cliente = new Cliente();
            cliente.setId(resultset.getInt("id"));
            /*servicio.setTipoServicio(Servicio.TipoServicio.valueOf(resultset.getString("tipoServicio")));
            servicio.setNombreServicio(resultset.getString("nombreServicio"));
            servicio.setPrecio(resultset.getDouble("precio"));
            servicios.add(servicio);*/
            }
          
        }catch (SQLException ex) {
          System.out.println("No posible mostrar los datos de la tabla cliente " + ex.getMessage());
       }
        return clientes;
    }
    
}
