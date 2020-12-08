/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.modelo;

/**
 *
 * @author wrath
 */
public class SupplyTV {
    private int id_proveedor;
    private int id_producto;
    private String fecha;
    private int cantidad;
    private float monto;

    public SupplyTV(int id_proveedor, int id_producto, String fecha, int cantidad, float monto) {
        this.id_proveedor = id_proveedor;
        this.id_producto = id_producto;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.monto = monto;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }
    
    
}
