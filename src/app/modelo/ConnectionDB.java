package app.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author wrath
 */
public class ConnectionDB {
    
    public static Connection con = null;
    public static String usuario = "root";
    public static String passw = "root";
    public static String url = "jdbc:derby://localhost:1527/abarrotes";
    Statement stm = null;
    int id, monto;
    String nombre, direccion, telefono, fecha;
    char realizado;

    public static Connection abrir() {
        try {
            //Class.forName("oracle.jdbc.driver.OracleDriver");
            //Class.forName("com.mysql.jdbc.Driver");
            //Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection(url, usuario, passw);
            System.out.println("Conexión exitosa:" + con);
        } catch (Exception e) {
            System.out.println("Error en la conexion...");
            e.printStackTrace();
            return null;
        }
        return con;
    }

    public static void cerrar() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            System.out.println("Error: No se logro cerrar "
                    + "la conexion:\n"+e);
        }
    }
    
    public boolean insertClient(String nombre, String direccion, String telefono){
        int resultUpdate = 0;
        try {
            
            con = abrir();
            stm = con.createStatement();
            resultUpdate = stm.executeUpdate("INSERT INTO clientes VALUES("+
                    "default,'" + nombre+ "','" + direccion + "','" + 
                    telefono + "')");
            if (resultUpdate != 0) {
                cerrar();
                return true;
            } else {
                cerrar();
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error en la base de datos.");
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean insertProvider(String nombre, String fecha, int monto, 
            char realizado){
        int resultUpdate = 0;
        try {
            
            con = abrir();
            stm = con.createStatement();
            resultUpdate = stm.executeUpdate("INSERT INTO proveedores VALUES("+
                    "default,'" + nombre+ "','" + fecha + "'," + 
                    monto + ",'"+ realizado+ "',)");
            if (resultUpdate != 0) {
                cerrar();
                return true;
            } else {
                cerrar();
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error en la base de datos.");
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean insertProduct(int providerKey, String nombre, float precio, 
            int existencia){
        int resultUpdate = 0;
        try {
            
            con = abrir();
            stm = con.createStatement();
            resultUpdate = stm.executeUpdate("INSERT INTO productos VALUES("+
                    "default," + providerKey + ",'" + nombre+ "'," + precio + "," + 
                    existencia + ")");
            if (resultUpdate != 0) {
                cerrar();
                return true;
            } else {
                cerrar();
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error en la base de datos.");
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean insertSupply(int productKey, int clientKey, int monto, 
            String fecha, float adeudo){
        int resultUpdate = 0;
        try {
            
            con = abrir();
            stm = con.createStatement();
            resultUpdate = stm.executeUpdate("INSERT INTO compras VALUES("+
                    "default," + productKey + "," + clientKey+ "," + monto + ",'" + 
                    fecha + "'," + adeudo + ")");
            if (resultUpdate != 0) {
                cerrar();
                return true;
            } else {
                cerrar();
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error en la base de datos.");
            e.printStackTrace();
            return false;
        }
    }
    
    //All tables get an update except usuario & compras
    public boolean updateClient(int id, String nombre, String direccion, 
            String telefono){
        int resultUpdate = 0;
        try {
            
            con = abrir();
            stm = con.createStatement();
            resultUpdate = stm.executeUpdate("UPDATE clientes SET "+
                    "nombre_cliente='" + nombre+ "',direccion_cliente='" + 
                    direccion + "',telefono_cliente='" +
                    telefono + "' WHERE id_cliente="+id);
            if (resultUpdate != 0) {
                cerrar();
                return true;
            } else {
                cerrar();
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error en la base de datos.");
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateProvider(int id, String nombre, String fecha, 
            int monto, char realizado){
        int resultUpdate = 0;
        try {
            
            con = abrir();
            stm = con.createStatement();
            resultUpdate = stm.executeUpdate("UPDATE proveedores SET "+
                    "nombre_proveedor='" + nombre+ "',fecha='" + 
                    fecha + "',monto=" + monto + ",realizado='"+ 
                    realizado +"' WHERE id_proveedor="+id);
            if (resultUpdate != 0) {
                cerrar();
                return true;
            } else {
                cerrar();
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error en la base de datos.");
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateProduct(int id, int id_proveedor, String nombre, 
            float precio, int existencia){
        int resultUpdate = 0;
        try {
            
            con = abrir();
            stm = con.createStatement();
            resultUpdate = stm.executeUpdate("UPDATE productos SET "+
                    "id_proveedor=" + id_proveedor+ ",nombre_producto='" +
                    nombre + "',precio=" + precio + ",existencia=" + 
                    existencia + " WHERE id_producto="+id);
            if (resultUpdate != 0) {
                cerrar();
                return true;
            } else {
                cerrar();
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error en la base de datos.");
            e.printStackTrace();
            return false;
        }
    }
    
    //All tables get a select all except usuarios
    public ArrayList<Client> getAllClients() {
        ArrayList<Client> clients = new ArrayList<Client>();
        ResultSet clientRS;
        Client clientFound;
        try {
            con = abrir();
            stm = con.createStatement();
            clientRS = stm.executeQuery("SELECT * FROM clientes");
            if (!clientRS.next()) {
                System.out.println(" No se encontraron registros");
                cerrar();
                return null;
            } else {
                do {
                    id = clientRS.getInt("id_cliente");
                    nombre = clientRS.getString("nombre_cliente");
                    direccion = clientRS.getString("direccion_cliente");
                    telefono = clientRS.getString("telefono_cliente");
                    clientFound = new Client(id, nombre, direccion, telefono);
                    clients.add(clientFound);
                } while (clientRS.next());
                cerrar();
                return clients;
            }
        } catch (Exception e) {
            System.out.println("Error en la base de datos.");
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Provider> getAllProviders() {
        ArrayList<Provider> providers = new ArrayList<Provider>();
        ResultSet providerRS;
        Provider providerFound;
        try {
            con = abrir();
            stm = con.createStatement();
            providerRS = stm.executeQuery("SELECT * FROM proveedores");
            if (!providerRS.next()) {
                System.out.println(" No se encontraron registros");
                cerrar();
                return null;
            } else {
                do {
                    id = providerRS.getInt("id_proveedor");
                    nombre = providerRS.getString("nombre_proveedor");
                    fecha = providerRS.getString("fecha");
                    monto = providerRS.getInt("monto");
                    realizado = providerRS.getString("realizado").charAt(0);
                    providerFound = new Provider(id, nombre, fecha, monto, realizado);
                    providers.add(providerFound);
                } while (providerRS.next());
                cerrar();
                return providers;
            }
        } catch (Exception e) {
            System.out.println("Error en la base de datos.");
            e.printStackTrace();
            return null;
        }
    }
    public ArrayList<Client> getAllProducts() {
        ArrayList<Client> clients = new ArrayList<Client>();
        ResultSet clientRS;
        Client clientFound;
        try {
            con = abrir();
            stm = con.createStatement();
            clientRS = stm.executeQuery("SELECT * FROM clientes");
            if (!clientRS.next()) {
                System.out.println(" No se encontraron registros");
                cerrar();
                return null;
            } else {
                do {
                    id = clientRS.getInt("id_cliente");
                    nombre = clientRS.getString("nombre_cliente");
                    direccion = clientRS.getString("direccion_cliente");
                    telefono = clientRS.getString("telefono_cliente");
                    clientFound = new Client(id, nombre, direccion, telefono);
                    clients.add(clientFound);
                } while (clientRS.next());
                cerrar();
                return clients;
            }
        } catch (Exception e) {
            System.out.println("Error en la base de datos.");
            e.printStackTrace();
            return null;
        }
    }
    
    
}
