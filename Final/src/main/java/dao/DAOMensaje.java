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
import java.util.Optional;
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
                        "idAutor INTEGER(15), " +
                        "nombreAutor VARCHAR(50), " +
                        "asunto VARCHAR(50), " +
                        "tipoUsuario ENUM('ADMIN', 'CLIENTE', 'EMPLEADO', 'PROVEEDOR'), " +
                        "esUrgente BOOLEAN, " +
                        "esLeido BOOLEAN, " +
                        "idDestinatario INTEGER(15), " +
                        "texto VARCHAR (255), " +
                        "FOREIGN KEY (idAutor) REFERENCES usuario(id)," +
                        "FOREIGN KEY (idDestinatario) REFERENCES usuario(id))";
                System.out.println("SQL mensaje " + sql);
                sentencia.executeUpdate(sql);
        }
   }
    
    @FXML
    public void anadir(Mensaje mensaje){
           
        try (
            Connection conexionDataBase =
            DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
            Statement statement = conexionDataBase.createStatement();
            String sql = "INSERT INTO mensaje(idAutor, nombreAutor, asunto, tipoUsuario, esUrgente, esLeido, idDestinatario, texto) " +
                   "VALUES (" + mensaje.getIdAutor() + ", '" 
                    + mensaje.getNombreAutor() + "', '"
                    + mensaje.getAsunto() + "', '"       
                    + mensaje.getTipoUsuario().name() + "', " 
                    + mensaje.isEsUrgente() + ", " 
                    + mensaje.getEsLeido()+ ", " 
                    + mensaje.getIdDestinatario() + ", '"
                    + mensaje.getTexto() + "');";
            System.out.println("SQL === " + sql);
            statement.executeUpdate(sql);  
          } catch (SQLException ex) {
                System.out.println("Error al introducir información en la tabla mensaje " + ex.getMessage());
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
    
    public List<Mensaje> buscarTodos(Optional<Integer> idDestinatario){
       
        List<Mensaje> mensajes = new ArrayList<>();
        try{
            
            Connection conexionDataBase = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD);
            Statement  statement = conexionDataBase.createStatement();
            String sql;
            if(idDestinatario.isPresent()) {
                sql = "SELECT m.id, m.idAutor, m.nombreAutor, m.asunto, m.tipoUsuario, m.esUrgente, m.esLeido, m.idDestinatario, " 
                    + "m.texto "
                    + "FROM mensaje m" + " "
                    + "inner join usuario ua "
                    + "on m.idAutor = ua.id "
                    + "inner join usuario ud "
                    + "on m.idDestinatario = ud.id " 
                    + "WHERE m.idDestinatario = " + idDestinatario.get() + " "
                    + "AND m.idDestinatario != m.idAutor " 
                    + "ORDER BY id";
            } else {
                sql = "SELECT m.id, m.idAutor, m.nombreAutor, m.asunto, m.tipoUsuario, m.esUrgente, m.esLeido, m.idDestinatario, " 
                    + "m.texto "
                    + "FROM mensaje m" + " "
                    + "inner join usuario ua "
                    + "on m.idAutor = ua.id "
                    + "inner join usuario ud "
                    + "on m.idDestinatario = ud.id "
                    + "WHERE m.idDestinatario = m.idAutor "
                    + "ORDER BY id";
             
            }            
            ResultSet resultset = statement.executeQuery(sql);
            while(resultset.next()){
                Mensaje mensaje = new Mensaje();
                mensaje.setId(resultset.getInt("id"));
                mensaje.setIdAutor(resultset.getInt("idAutor"));
                mensaje.setNombreAutor(resultset.getString("nombreAutor"));
                mensaje.setAsunto(resultset.getString("asunto"));
                mensaje.setTipoUsuario(TipoUsuario.valueOf(resultset.getString("tipoUsuario").toUpperCase()));
                mensaje.setEsUrgente(resultset.getBoolean("esUrgente"));
                mensaje.setEsLeido(resultset.getBoolean("esLeido"));
                mensaje.setIdDestinatario(resultset.getInt("idDestinatario"));
                mensaje.setTexto(resultset.getString("texto"));
                mensajes.add(mensaje);
            }
           
        }catch (SQLException ex) {
          System.out.println("No posible mostrar los datos de la tabla mensaje " + ex.getMessage());
       }
        return mensajes;
    }

    public void marcarComoLeido(int idMensaje) {
        try{
            
            Connection conexionDataBase = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD);
            Statement  statement = conexionDataBase.createStatement();
            String sql = "update mensaje "
                    + "set esLeido = true "
                    + "where id = " + idMensaje+ ";";
            statement.executeUpdate(sql);
        }catch (SQLException ex) {
          System.out.println("No posible mostrar los datos de la tabla mensaje " + ex.getMessage());
       }
        
    }

    
    
}
