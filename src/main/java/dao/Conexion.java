package dao;

import java.sql.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;


public class Conexion {
    //esto es como tener los datos de la casa
    private static final String URL = "jdbc:mysql://localhost:3306/gastos?useSSL=false"
            + "&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    //esto seria como tener la llave de la casa
    public static DataSource getDataSource(){
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(URL);
        ds.setUsername(USER);
        ds.setPassword(PASSWORD);
        ds.setInitialSize(30);  
        return ds;
        
    }
    
    //usando los elementos anteriores, puedo entrar a la casa
    public static Connection getConnection() throws SQLException{
        return getDataSource().getConnection();
    }
    
    public static void close(ResultSet rs){
        try{
            rs.close();
        }catch(SQLException ex){
            System.out.println("Error: "+ex);
        }
    }
    
    public static void close(PreparedStatement stmt){
        try{
            stmt.close();
        }catch(SQLException ex){
            System.out.println("Error: "+ex);
        }
    }
    
    public static void close(Connection conn){
        try{
            conn.close();
        }catch(SQLException ex){
            System.out.println("Error: "+ex);
        }
    }
}
