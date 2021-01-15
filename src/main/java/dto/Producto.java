package dto;

public class Producto {

    private int id;
    private String nombre;
    private double precio;
    private int cantidad;

    /**
     * Crear objetos a partir de la clase producto estos objetos no son
     * parametrizados
     */
    public Producto() {
    }

    /**
     * Sobrecarga del constructor, eliminar o encontrar
     *
     * @param id
     */
    public Producto(int id) {
        this.id = id;
    }

    /**
     * se utiliza este constructor para agregar/insertar se ignora el id porque
     * es auto incrementable
     *
     * @param nombre
     * @param precio
     * @param cantidad
     */
    public Producto(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    /**
     * este constructor sirve para modificar elementos, o en este caso UPDATE
     * sobre la bdd
     *
     * @param id
     * @param nombre
     * @param precio
     * @param cantidad
     */
    public Producto(int id, String nombre, double precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "producto: "+nombre+" - "+precio+" - "+cantidad;
    }
}
