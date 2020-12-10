package app.vista;

import app.modelo.ConnectionDB;
import app.modelo.Product;
import app.modelo.Provider;
import app.modelo.Supply;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Consumer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 *
 * @author wrath
 */
public class Agenda {

    static TableView<Provider> tv;

    public static TableView agendaPane() {
        tv = new TableView<Provider>();

        TableColumn<Provider, String> day = new TableColumn("Dia");
        TableColumn<Provider, String> name = new TableColumn("Proveedor");
        TableColumn<Provider, String> amount = new TableColumn("Monto");
        //TableColumn products = new TableColumn("Productos");

        day.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        name.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        amount.setCellValueFactory(new PropertyValueFactory<>("montoString"));
        amount.setCellFactory(TextFieldTableCell.forTableColumn());
        //products.setCellValueFactory(new PropertyValueFactory<>("realizado"));

        tv.setPrefSize(600, 500);
        tv.setEditable(true);
        ConnectionDB db = new ConnectionDB();
        ArrayList<Provider> providers = db.getAllProviders();
        LocalDateTime date = LocalDateTime.now();
        String dayS = String.valueOf(date.getDayOfMonth()) + "-"
                + String.valueOf(date.getMonthValue()) + "-"
                + String.valueOf(date.getYear());
        ArrayList<Provider> tempProviders = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(date.toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (providers != null) {
            for (int i = 0; i < 14; i++) {
                c.add(Calendar.DAY_OF_MONTH, 1);
                String[] dayArray = sdf.format(c.getTime()).split("-");
                String dayReverse = dayArray[2] + "-" + dayArray[1] + "-" + dayArray[0];
                tempProviders.add(new Provider(1, "", dayReverse, 0, 'N'));
                boolean flag = false;
                for (Provider provider : providers) {
                    if (provider.getFecha().equals(sdf.format(c.getTime()))) {
                        provider.setFecha(dayReverse);
                        tv.getItems().add(provider);
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    tv.getItems().add(tempProviders.get(tempProviders.size() - 1));
                }
            }
        } else {
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
        }

        name.setOnEditCommit(e -> {
            Provider p = e.getRowValue();
            String[] dayArray = p.getFecha().split("-");
            String dayReverse = dayArray[2] + "-" + dayArray[1] + "-" + dayArray[0];
            ArrayList<Provider> providers2 = db.getAllProviders();
            for (Provider provider : providers2) {
                if (provider.getNombre().trim().equals(e.getNewValue())) {
                    db.updateProvider(provider.getId(), provider.getNombre(),
                            dayReverse, provider.getMonto(), 'N');
                }
            }
        });
        amount.setOnEditCommit(e -> {
            Provider p = e.getRowValue();
            ArrayList<Provider> providers2 = db.getAllProviders();
            for (Provider provider : providers2) {
                if (provider.getNombre().trim().equals(p.getNombre().trim())) {
                    db.updateProvider(provider.getId(), provider.getNombre(),
                            provider.getFecha(),
                            Integer.parseInt(e.getNewValue()), 'N');
                }
            }
        });

        tv.getColumns().addAll(day, name, amount);
        return tv;
    }

}
