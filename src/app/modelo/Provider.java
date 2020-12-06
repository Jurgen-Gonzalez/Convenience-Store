package app.modelo;

/**
 *
 * @author wrath
 */
public class Provider {
    private int id;

    private String nombre;
    private String fecha;
    private int monto;
    private char realizado;

    public Provider(int id, String nombre, String fecha, int monto, char realizado) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.monto = monto;
        this.realizado = realizado;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return nombre;
    }

    public void setName(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public char getRealizado() {
        return realizado;
    }

    public void setRealizado(char realizado) {
        this.realizado = realizado;
    }


}
