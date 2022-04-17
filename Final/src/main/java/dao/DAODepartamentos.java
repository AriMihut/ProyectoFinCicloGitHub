package dao;
import com.amm.finciclo.proyectofinciclo.Departamento;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;

public class DAODepartamentos {
    
    public static final String URL_CONEXION = "jdbc:mysql://localhost:3306/proyectofinciclo";
    public static final String USUARIO_BDD = "root";
    public static final String PASSWORD_BDD = "";
    
    public DAODepartamentos() throws SQLException{
        crearTablaSiNoExiste();
    }

    private void crearTablaSiNoExiste() throws SQLException {
        
        try(
                Connection conexion = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
                Statement sentencia = conexion.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS departamento" +
                          "(idDepartamento INTEGER(15) auto_increment, " +
                          "nombreDepartamento VARCHAR(50), " +
                          "FOREIGN KEY (idPersonal) REFERENCES personal (id) )";
                sentencia.executeUpdate(sql);
    }
   }
    
    @FXML
    public void anadir(Departamento departamento){
           
        try (
            Connection conexionDataBase =
            DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
            Statement statement = conexionDataBase.createStatement();
            String sql = "INSERT INTO departamento(idDepartamento, nombreDepartamento, idPersonal) " 
                    + "VALUES ('" + departamento.getIdDepartamento()+ "', '" 
                    + departamento.getNombreDepartamento()+ "', '" 
                    + departamento.getIdPersonal() + "')";
            statement.executeUpdate(sql);  
            
          } catch (SQLException ex) {
                System.out.println("Error al añadir en departamento");
          }      
        
        }
    
    @FXML
    public void modificar(Departamento departamento) {
        
         try(
            Connection conexionDataBase =
            DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
            Statement statement = conexionDataBase.createStatement();
            String sql = "UPDATE departamento set idDepartamento='" + 
                    departamento.getIdDepartamento() + 
                    "', nombreDepartamento='" + departamento.getNombreDepartamento() + 
                    "', idPersonal='" + departamento.getIdPersonal()+
                    "' WHERE idDepartamento=" + departamento.getIdDepartamento();
            statement.executeUpdate(sql);
          } catch (SQLException ex) {
                System.out.println("Error al modificar la tabla departamento");
            }      
            
        }
    
    @FXML
    public void eliminar(Departamento departamento) {
        try{
            Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD);
            Statement statement = conexionDB.createStatement();
            String sql = "DELETE FROM departamento WHERE idDepartamento=" + departamento.getIdDepartamento();
            statement.executeUpdate(sql);
        }catch(Exception e){
            throw new RuntimeException("Ocurrió un error al eliminar registro de departamento " + e.getMessage());
        }
     }
    
    public List<Departamento> buscarTodos(){
        
        List<Departamento> departamentos = new ArrayList<>();
        try{
            Connection conexionDataBase = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD);
            Statement  statement = conexionDataBase.createStatement();
            String sql = "SELECT * FROM departamento ORDER BY idDepartamento";
            ResultSet resultset = statement.executeQuery(sql);
            
        while(resultset.next()){
             Departamento departamento = new Departamento();
             departamento.setIdDepartamento(resultset.getInt("idDepartamento"));
             departamento.setNombreDepartamento(resultset.getString("nombreDepartamento"));
             departamento.setIdPersonal(resultset.getInt("idPersonal"));
             departamentos.add(departamento);
            }
          
        }catch (SQLException ex) {
          System.out.println("No posible mostrar los datos de la tabla departamento");
            System.out.println(ex.getMessage());
       }
        return departamentos;
    }
    
    
}
