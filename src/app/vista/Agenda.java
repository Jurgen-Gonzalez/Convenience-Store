package app.vista;

import app.modelo.ConnectionDB;
import app.modelo.Provider;
import app.modelo.Supply;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.Consumer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author wrath
 */
public class Agenda {
    static TableView<Provider> tv;
    public static TableView agendaPane(){
        tv = new TableView<Provider>();
        
        TableColumn day = new TableColumn("Dia");
        TableColumn name = new TableColumn("Proveedor");
        TableColumn amount = new TableColumn("Monto");
        TableColumn products = new TableColumn("Productos");
        
        day.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        name.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        amount.setCellValueFactory(new PropertyValueFactory<>("monto"));
        products.setCellValueFactory(new PropertyValueFactory<>("realizado"));
        
        tv.setPrefSize(600, 500);
        tv.setEditable(true);
        insertSupplies();
        ConnectionDB db = new ConnectionDB();
        ArrayList<Provider> providers = db.getAllProviders();
        /*if(providers != null)
            for (Provider provider : providers) {
                tv.getItems().add(provider);
            }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "No se encontraron registros de proveedores");
            alert.showAndWait()
            .filter(response -> response == ButtonType.OK)
            .ifPresent(new Consumer<ButtonType>() {
                @Override
                public void accept(ButtonType response) {
                    ;
                }
            });
        }*/
        tv.getColumns().addAll(day, name, amount, products);
        return tv;
    }
    
    private static void insertSupplies(){
        Date today = new Date();
        today.setTime(System.currentTimeMillis());
        //System.out.println(today.getTime());
        
    }
}
