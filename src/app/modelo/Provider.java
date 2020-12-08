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
    //Workaround for editable TableView
    private String montoString;

    public Provider(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Provider(int id, String nombre, String fecha, int monto, char realizado) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.monto = monto;
        this.realizado = realizado;
        montoString = String.valueOf(monto);
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

    public String getMontoString() {
        return montoString;
    }

    public void setMontoString(String montoString) {
        this.montoString = montoString;
    }

    

}
