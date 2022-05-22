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
                        "codigoConjunto VARCHAR (50), " +
                        "fechaVenta TIMESTAMP, " +
                        "valorTotalVenta DOUBLE, " +
                        "idUsuario INTEGER, " +
                        "idServicio INTEGER, " +
                        "FOREIGN KEY (idUsuario) REFERENCES usuario(id), " +
                        "FOREIGN KEY (idServicio) REFERENCES servicio(id))";
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
            String sql = "INSERT INTO venta(codigoConjunto, fechaVenta, valorTotalVenta, idUsuario, idServicio) " 
                    + "VALUES ('" + venta.getCodigoConjunto()+ "', '" 
                    + new Timestamp(System.currentTimeMillis()) + "', "
                    + venta.getValorTotalVenta()+", "
                    + venta.getIdUsuario()+", "
                    + venta.getIdServicio() + ")";
            statement.executeUpdate(sql);  
            } catch (SQLException ex) {
                System.out.println("Error al añadir en venta " + ex.getMessage());
            }      
    }
    
    public ArrayList<Venta> buscarTodas(){
        
        ArrayList<Venta> ventas = new ArrayList<>();
       
        try{
            String codigoConjunto = "";
            java.sql.Date fechaVenta = null ;
            int idUsuario = 0;
            String nombreUsuario = "";
            String nombreServicio = "";
            double valorTotalVenta = 0;
            
            
            Connection conexionDataBase = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD);
            Statement  statement = conexionDataBase.createStatement();
            String sql = "select v.codigoConjunto, v.fechaVenta, v.valorTotalVenta,"
                    + " v.idUsuario, u.nombreUsuario, s.nombreServicio "
                    + "from venta v "
                    + "inner join servicio s "
                    + "on v.idServicio = s.id "
                    + "inner join usuario u " 
                    + "on v.idUsuario = u.id"; 
            ResultSet resultset = statement.executeQuery(sql);
        
            while(resultset.next()){
                codigoConjunto = resultset.getString("codigoConjunto");
                fechaVenta = resultset.getDate("fechaVenta");
                valorTotalVenta = resultset.getDouble("valorTotalVenta");
                idUsuario = resultset.getInt("idUsuario");
                nombreUsuario = resultset.getString("nombreUsuario");
                nombreServicio = resultset.getString("nombreServicio");
                
                if(ventas.isEmpty()) {
                    Venta nuevaVenta = new Venta(codigoConjunto, 
                            fechaVenta, valorTotalVenta,
                            idUsuario, nombreUsuario);
                   nuevaVenta.getNombreServicios().add(nombreServicio);
                    System.out.println("venta nueva =" + nuevaVenta);
                   ventas.add(nuevaVenta);
                   
                } else { 
                    for(Venta venta : ventas) {
                        if(ventas.size() >= 1 && venta.getCodigoConjunto().equals(codigoConjunto)) {
                            System.out.println("venta existente =" + venta);
                            venta.getNombreServicios().add(nombreServicio);
                            venta.setValorTotalVenta(venta.getValorTotalVenta() +  valorTotalVenta);
                        
                        } else {
                            System.out.println("venta nueva =" + venta);
                            Venta nuevaVenta = new Venta(codigoConjunto, 
                                 fechaVenta, valorTotalVenta,
                                 idUsuario, nombreUsuario);
                             nuevaVenta.getNombreServicios().add(nombreServicio); 
                             ventas.add(nuevaVenta);
                        }
                    }
                } 
            }
        }catch (Exception ex) {
          System.out.println("No posible mostrar los datos de la tabla venta " + ex.getMessage());
        }
        
        ventas.forEach(v -> System.out.println(v));
        
        return ventas;
        
    }
    
}
