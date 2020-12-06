package app.modelo;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 *
 * @author wrath
 */
public class Product {
    private int id;
    private String nombre;
    private float precio;
    private int existencia;

    public Product(int id, String nombre, float precio, int existencia) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.existencia = existencia;
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
    
}
