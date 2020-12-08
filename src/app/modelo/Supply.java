package app.modelo;

/**
 *
 * @author wrath
 */
public class Supply {
    private int producto;
    private int cliente;
    private int monto;
    private String fecha;
    private float adeudo;

    public Supply(int producto, int cliente, int monto, String fecha, float adeudo) {
        this.producto = producto;
        this.cliente = cliente;
        this.monto = monto;
        this.fecha = fecha;
        this.adeudo = adeudo;
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }


    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getAdeudo() {
        return adeudo;
    }

    public void setAdeudo(float adeudo) {
        this.adeudo = adeudo;
    }
    
        
}