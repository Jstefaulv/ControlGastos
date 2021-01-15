package dao;

import dto.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductosDAO {
    //consultas
    private static final String SELECT = "SELECT id,nombre,precio,cantidad FROM productos";
    private static final String SELECTBYID = "SELECT id,nombre,precio,cantidad FROM productos where id=?";
    private static final String INSERT = "INSERT INTO productos(nombre,precio,cantidad) VALUES(?,?,?)";
    private static final String UPDATE = "UPDATE productos SET nombre=?,precio=?,cantidad=? WHERE id=?";
    private static final String DELETE = "DELETE FROM productos WHERE id=?";
    //fin consultas
    
    public List<Producto> listar(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Producto producto=null;
        List<Producto> listaProductos = new ArrayList<>();
        
        /**
         * try catch maneja fragmentos de codigo que son propensos a fallar.
         */
        try {
            conn = Conexion.getConnection();
            /**
             * pre-compilar la consulta para ejecutarla muchas veces y que el pobre servidor,
             * no se muera en el intento.
             */
            stmt =conn.prepareStatement(SELECT);
            /**
             * contiene todas las filas que puede retornar una sentencia SQL, puedo ocupar el objeto de RS
             * permite que pueda acceder a todo este conjunto de datos, get, next, moverse en diferentes
             * registros recorriendo toda la tabla hasta que no hayan mas datos que mostrar.
             */
            rs = stmt.executeQuery();
            
            while(rs.next()){
                int id= rs.getInt("id");
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                int cantidad = rs.getInt("cantidad");
                
                producto = new Producto(id,nombre,precio,cantidad);
                listaProductos.add(producto);
            }
        } catch (SQLException ex) {
            System.out.println("Error:" + ex);
        }finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return listaProductos;
    }
    
    public Producto encontrar(Producto producto){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SELECTBYID);
            stmt.setInt(1, producto.getId());
            rs = stmt.executeQuery();
            rs.absolute(1);
            
            //recibir los elementos desde la bd
            String nombre = rs.getString("nombre");
            double precio = rs.getDouble("precio");
            int cantidad = rs.getInt("cantidad");
            
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setCantidad(cantidad);
            
        } catch (SQLException ex) {
            System.out.println("Error"+ex);
        }finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return producto;
    }
    
    public int insertar(Producto producto){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(INSERT);
            stmt.setString(1, producto.getNombre());
            stmt.setDouble(2, producto.getPrecio());
            stmt.setInt(3, producto.getCantidad());
            
            registros = stmt.executeUpdate(); 
            
        } catch (SQLException ex) {
            System.out.println("Error: "+ex);
        }finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return registros;
    }
    
    public int actualizar(Producto producto){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, producto.getNombre());
            stmt.setDouble(2, producto.getPrecio());
            stmt.setInt(3, producto.getCantidad());
            stmt.setInt(4, producto.getId());
            
            registros = stmt.executeUpdate(); 
            
        } catch (SQLException ex) {
            System.out.println("Error: "+ex);
        }finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return registros;
    }
    
     public int eliminar(Producto producto){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, producto.getId());
            
            registros = stmt.executeUpdate(); 
            
        } catch (SQLException ex) {
            System.out.println("Error: "+ex);
        }finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return registros;
    }  
}