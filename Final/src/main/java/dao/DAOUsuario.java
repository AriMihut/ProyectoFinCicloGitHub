package dao;

import com.amm.finciclo.proyectofinciclo.TipoUsuario;
import com.amm.finciclo.proyectofinciclo.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;

public class DAOUsuario {
    
    public static final String URL_CONEXION = "jdbc:mysql://localhost:3306/proyectofinciclo";
    public static final String USUARIO_BDD = "root";
    public static final String PASSWORD_BDD = "";

    public DAOUsuario() throws SQLException{
        
        crearTablaSiNoExiste();
    }
    
     private void crearTablaSiNoExiste() throws SQLException {
        
        try(
                 Connection conexion = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
                Statement sentencia = conexion.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS usuario" +
                          "(id INTEGER auto_increment NOT NULL PRIMARY KEY, " +
                            "nombreUsuario VARCHAR(50) NULL, " +
                            "contrasena VARCHAR(50) NULL, " +
                            "tipoUsuario ENUM('CLIENTE', 'EMPLEADO', 'ADMIN'), " +
                            "dni VARCHAR(50) NULL, " +
                            "nombre VARCHAR(50) NULL, " +
                            "apellido VARCHAR(50) NULL, " +
                            "sexo VARCHAR(15) NULL, " +
                            "telefono LONG NULL, " +
                            "email VARCHAR(50) NULL)";
                sentencia.executeUpdate(sql);
        }
   }
    
    public void anadir(Usuario usuario){
        
        try (
            Connection conexionDataBase =
            DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
            Statement statement = conexionDataBase.createStatement();
            String sql = "INSERT INTO usuario(nombreUsuario, contrasena, tipoUsuario, "
                    + "dni, nombre, apellido, sexo, telefono, email) " 
                    + "VALUES ('" + usuario.getNombreUsuario() + 
                    "', '" + usuario.getContrasena() +
                    "', '" + usuario.getTipoUsuario().name() + 
                    "', '" + usuario.getDni()+
                    "', '" + usuario.getNombre() +
                    "', '" + usuario.getApellido() +
                    "', '" + usuario.getSexo() +
                    "', " + usuario.getTelefono() + 
                    ", '" + usuario.getEmail() + "');";
            System.out.println("sql " + sql);
            statement.executeUpdate(sql);         
          } catch (SQLException ex) {
                System.out.println("Error al introducir información en la tabla usuario " + ex.getMessage());
          }        
        
        }
    
    public void modificar(Usuario usuario) {
        
         try(
            Connection conexionDataBase =
            DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
            Statement statement = conexionDataBase.createStatement();
            String sql = "UPDATE usuario set dni='" + usuario.getDni()+
                    "', nombre='" + usuario.getNombre() +
                    "', apellido='" + usuario.getApellido() +
                    "', sexo='" + usuario.getSexo() +
                    "', telefono=" + usuario.getTelefono() + 
                    ", email='" + usuario.getEmail() + 
                    "' WHERE id=" + usuario.getId();
            statement.executeUpdate(sql); 
          } catch (SQLException ex) {
                System.out.println("Error al modificar la información en la tabla usuario" + ex.getMessage());
            }          
        }
    
    public List<Usuario> buscarTodos(){
        
        List<Usuario> usuarios = new ArrayList<>();
        try{
            Connection conexionDataBase = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD);
            Statement  statement = conexionDataBase.createStatement();
            String sql = "SELECT * FROM usuario ORDER BY id";
            ResultSet resultset = statement.executeQuery(sql);
            
        while(resultset.next()){
             Usuario usuario = new Usuario();
             usuario.setNombreUsuario(resultset.getString("nombreUsuario"));
             usuario.setContrasena(resultset.getString("contrasena"));
             usuario.setDni(resultset.getString("dni"));
             usuario.setNombre(resultset.getString("nombre"));
             usuario.setApellido(resultset.getString("apellido"));
             usuario.setSexo(resultset.getString("sexo"));
             usuario.setTelefono(resultset.getLong("telefono"));
             usuario.setEmail(resultset.getString("email"));
             usuarios.add(usuario);
            }
          
        }catch (SQLException ex) {
            System.out.println("No posible mostrar los datos de la tabla usuario" + ex.getMessage());
       }
        return usuarios;
    }
    
    public List<Usuario> buscarClientes(){
        
        List<Usuario> clientes = new ArrayList<>();
        try{
            Connection conexionDataBase = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD);
            Statement  statement = conexionDataBase.createStatement();
            String sql = "SELECT * FROM usuario ORDER BY id";
            ResultSet resultset = statement.executeQuery(sql);
            
        while(resultset.next()){
             Usuario usuario = new Usuario();
             usuario.setDni(resultset.getString("dni"));
             usuario.setNombre(resultset.getString("nombre"));
             usuario.setSexo(resultset.getString("sexo"));
             usuario.setTelefono(resultset.getLong("telefono"));
             usuario.setEmail(resultset.getString("email"));
             clientes.add(usuario);
            }
          
        }catch (SQLException ex) {
            System.out.println("No posible mostrar los datos de la tabla clientes " + ex.getMessage());
       }
        return clientes;
        
    }
    
    @FXML
    public void eliminar(Usuario usuario) {
        try{
            Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD);
            Statement statement = conexionDB.createStatement();
            String sql = "DELETE FROM usuario WHERE id=" + usuario.getId();
            statement.executeUpdate(sql);
        }catch(Exception e){
            throw new RuntimeException("Ocurrió un error al eliminar la información de la tabla usuario " + e.getMessage());
        }
     }

      
    public static ArrayList<Usuario> comprobarExistenciaUsuario(Usuario usuario) throws SQLException {
          
        ArrayList<Usuario> usuarios = new ArrayList<>();
        
        Connection conexionDataBase = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD);
        Statement  statement = conexionDataBase.createStatement();
        String sql = "SELECT * FROM usuario WHERE "
                + "nombreUsuario = '" + usuario.getNombreUsuario()+ 
                "' and contrasena ='" + usuario.getContrasena() 
                + "' and tipoUsuario = '" + usuario.getTipoUsuario().getValue() + "';";
         ResultSet resultSet = statement.executeQuery(sql);
         Usuario usuarioExistente = null;
         while(resultSet.next()) {
            usuarioExistente = new Usuario();
            usuarioExistente.setId(resultSet.getInt("id")); 
            usuarioExistente.setNombreUsuario(resultSet.getString("nombreUsuario"));
            usuarioExistente.setContrasena(resultSet.getString("contrasena"));
            usuarioExistente.setTipoUsuario(TipoUsuario.valueOf(resultSet.getString("tipoUsuario").toUpperCase()));
            usuarioExistente.setDni(resultSet.getString("dni"));
            usuarioExistente.setNombre(resultSet.getString("nombre"));
            usuarioExistente.setApellido(resultSet.getString("apellido"));
            usuarioExistente.setSexo(resultSet.getString("sexo"));
            usuarioExistente.setTelefono(resultSet.getLong("telefono"));
            usuarioExistente.setEmail(resultSet.getString("email"));
            usuarios.add(usuarioExistente);
         }
        return usuarios;
      }

}
