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
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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
            String sql = "INSERT INTO venta(codigoConjunto, fechaVenta, valorTotalVenta, idUsuario, nombreUsuario, nombreServicio) " 
                    + "VALUES ('" + venta.getCodigoConjunto()+ "', '" 
                    + new Timestamp(System.currentTimeMillis()) + "', "
                    + venta.getValorTotalVenta()+", "
                    + venta.getIdUsuario()+", '"
                    + venta.getNombreUsuario() + "', '"
                    + venta.getNombreServicios().stream().findFirst().get() + "')";            
            statement.executeUpdate(sql);
            
            } catch (SQLException ex) {
                System.out.println("Error al añadir en venta " + ex.getMessage());
            }      
    }
    
    public ArrayList<Venta> buscarTodas(){
        
        ArrayList<Venta> ventas = new ArrayList<>();
        ArrayList<Venta> ventasAgrupadas = new ArrayList<>();
       
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
                    + " v.idUsuario, u.nombreUsuario, v.nombreServicio "
                    + "from venta v "
                    + "inner join usuario u " 
                    + "on v.idUsuario = u.id"; 
            System.out.println("SQL sdfghjkhfgjlk=== " + sql);
            ResultSet resultset = statement.executeQuery(sql);
        
            while(resultset.next()){
              
                codigoConjunto = resultset.getString("codigoConjunto");
                fechaVenta = resultset.getDate("fechaVenta");
                valorTotalVenta = resultset.getDouble("valorTotalVenta");
                idUsuario = resultset.getInt("idUsuario");
                nombreUsuario = resultset.getString("nombreUsuario");
                nombreServicio = resultset.getString("nombreServicio");
                
                Venta nuevaVenta = new Venta(codigoConjunto, 
                    fechaVenta, valorTotalVenta,
                    idUsuario, nombreUsuario, nombreServicio);
                ventas.add(nuevaVenta);
                
            } 
                
                
                
                
            /*if(ventas.isEmpty()) {
            // añadirServicioAventa(nuevaVenta, nombreServicio, valorTotalVenta);
            añadirVenta(nuevaVenta, ventas);
            }   */
            /*if(venta ) {
            System.out.println("Venta NO esta vacia");*/
            /*  for(Venta venta : ventas) {
            if(venta.getCodigoConjunto().equals(codigoConjunto) && !venta.getNombreServicios().contains(nombreServicio)) {
            System.out.println("esta venta ya xiste, asi que añadimos un nuevo servicio ");
            //System.out.println("venta existente =" + venta);
            añadirServicioAventa(venta, nombreServicio, valorTotalVenta);
            } else if(!venta.getCodigoConjunto().equals(codigoConjunto)) {
            System.out.println("esta venta no xiste, asi que creamos nueva venta");
            Venta nuevaVenta = new Venta(codigoConjunto,
            fechaVenta, valorTotalVenta,
            idUsuario, nombreUsuario, nombreServicio);
            añadirVenta(nuevaVenta, ventas);
            }
            }*/
            //}
            
            
            Set<String> codigosConjunto = ventas.stream().map(v -> v.getCodigoConjunto()).collect(Collectors.toSet());
            System.out.println("codigo = " + codigosConjunto.toString());
            for(String cc : codigosConjunto) {
                System.out.println("codigo = "+ cc);
                List<Venta> ventasAgrupoadasPorCodigo = ventas.stream()
                        .filter(v -> v.getCodigoConjunto().equals(cc))
                        .collect(Collectors.toList());
                
                Venta ventaAgrupada = new Venta(cc,
                        ventasAgrupoadasPorCodigo.get(0).getFechaVenta(),
                        ventasAgrupoadasPorCodigo.get(0).getValorTotalVenta(),
                        ventasAgrupoadasPorCodigo.get(0).getIdUsuario(),
                        ventasAgrupoadasPorCodigo.get(0).getNombreUsuario(),
                        ventasAgrupoadasPorCodigo.get(0).getNombreServicios().stream().findFirst().get());

                
                
                
                
                
                for(Venta v : ventasAgrupoadasPorCodigo) {
                    if(ventas.indexOf(v) != 0) {
                        ventaAgrupada.getNombreServicios()
                            .add(v.getNombreServicios().stream().findFirst().get());
                    }
                }
                
                ventasAgrupadas.add(ventaAgrupada);
               
                
            }
           
            
            
        }catch (Exception ex) {
          System.out.println("No posible mostrar los datos de la tabla venta " + ex.getMessage());
        }
        
        
        return ventasAgrupadas;
        
    }

    private void añadirVenta(Venta venta, ArrayList<Venta> ventas) {
      ventas.add(venta);
    }

    private void añadirServicioAventa(Venta venta, String nombreServicio, double valorTotalVenta) {
       venta.getNombreServicios().add(nombreServicio);
       venta.setValorTotalVenta(venta.getValorTotalVenta() +  valorTotalVenta);
    }
    
}
