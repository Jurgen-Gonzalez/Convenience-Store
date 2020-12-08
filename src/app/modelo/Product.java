package app.modelo;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 *
 * @author wrath
 */
public class Product {
    private int id;
    private int id_proveedor;
    private String nombre;
    private float precio;
    private int existencia;
    //Workaround for the JavaFX TableView edit cell
    private String precioString;
    private String existenciaString;

    public Product(int id, int id_proveedor, String nombre, float precio, int existencia) {
        this.id = id;
        this.id_proveedor = id_proveedor;
        this.nombre = nombre;
        this.precio = precio;
        this.existencia = existencia;
        precioString = String.valueOf(precio);
        existenciaString = String.valueOf(existencia);
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }
    
    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getPrecioString() {
        return String.valueOf(precio);
    }

    public void setPrecioString(String precioString) {
        this.precioString = precioString;
    }

    public String getExistenciaString() {
        return String.valueOf(existencia);        
    }

    public void setExistenciaString(String existenciaString) {
        this.existenciaString = existenciaString;
    }
    
    
}
