package dao;

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
    }
    
    
    public void anadir(Usuario usuario){
           
        try (
            Connection conexionDataBase =
            DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
            Statement statement = conexionDataBase.createStatement();
            String sql = "INSERT INTO usuario(nombre, contrasena, tipoUsuario) " 
                    + "VALUES ('" + usuario.getNombre() + "', '" + usuario.getContrasena()
                    + "', '" + usuario.getTipoUsuario().name() + "');";
            statement.executeUpdate(sql);  
            System.out.println("sql ===> " + sql);
            
          } catch (SQLException ex) {
                System.out.println("Error al introducir información en la tabla usuario " + ex.getMessage());
          }        
        
        }
    
    public void modificar(Usuario usuario) {
        
         try(
            Connection conexionDataBase =
            DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
            Statement statement = conexionDataBase.createStatement();
            String sql = "UPDATE usuario set nombre='" + usuario.getNombre() + "', "
                    + "contrasena='" + usuario.getContrasena() + "' WHERE id=" + usuario.getId();
            statement.executeUpdate(sql);
          } catch (SQLException ex) {
                System.out.println("Error al modificar la información en la tabla usuario");
            }      
            
        }
    
    public List<Usuario> buscarTodas(){
        
        List<Usuario> usuarios = new ArrayList<>();
        try{
            Connection conexionDataBase = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD);
            Statement  statement = conexionDataBase.createStatement();
            String sql = "SELECT * FROM usuario ORDER BY id";
            ResultSet resultset = statement.executeQuery(sql);
            
        while(resultset.next()){
             Usuario usuario = new Usuario();
             usuario.setNombre(resultset.getString("nombre"));
             usuario.setContrasena(resultset.getString("contrasena"));
             usuarios.add(usuario);
            }
          
        }catch (SQLException ex) {
          System.out.println("No posible mostrar los datos de la tabla usuario");
            System.out.println(ex.getMessage());
       }
        return usuarios;
    }
    
    @FXML
    public void eliminar(Usuario usuario) {
        try{
            Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD);
            Statement statement = conexionDB.createStatement();
            String sql = "DELETE FROM usuario WHERE id=" + usuario.getId();
            statement.executeUpdate(sql);
        }catch(Exception e){
            throw new RuntimeException("Ocurrió un error al eliminar la información " + e.getMessage());
        }
     }

      
      public static boolean comprobarExistenciaUsuario(Usuario usuario) throws SQLException {
          
        Connection conexionDataBase = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD);
        Statement  statement = conexionDataBase.createStatement();
        String sql = "SELECT * FROM usuario WHERE "
                + "nombre = '" + usuario.getNombre() + 
                "' and contrasena ='" + usuario.getContrasena() 
                + "' and tipoUsuario = '" + usuario.getTipoUsuario().name() + "';";
         ResultSet resultset = statement.executeQuery(sql);
        return resultset.next();
      }

}
