package app.vista;

import app.modelo.Provider;
import app.modelo.Supply;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author wrath
 */
public class Agenda {
    static TableView<Supply> tv;
    public static TableView agendaPane(){
        tv = new TableView<Supply>();
        
        TableColumn day = new TableColumn("Dia");
        TableColumn provider = new TableColumn("Proveedor");
        TableColumn amount = new TableColumn("Monto");
        TableColumn products = new TableColumn("Productos");
        
        tv.setPrefSize(600, 500);
        tv.setEditable(true);
        insertSupplies();
        
        tv.getColumns().addAll(day, provider, amount, products);
        return tv;
    }
    
    private static void insertSupplies(){
        Date today = new Date();
        today.setTime(System.currentTimeMillis());
        System.out.println(today.getTime());
        /*ObservableList<Supply> data = FXCollections.observableArrayList(
                new Supply(today, new Provider("Gamesa"), 5000, null),
                new Supply()
        );
        tv.setItems(data);*/
    }
}
