package app.vista;

import app.modelo.ConnectionDB;
import app.modelo.Product;
import app.modelo.Provider;
import java.util.ArrayList;
import java.util.function.Consumer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;

/**
 *
 * @author wrath
 */
public class Inventario {
    static GridPane startingPane;
    public static GridPane inventarioPane(Tab tab){
        startingPane = new GridPane();
        
        Button register = new Button("Registrar Producto");
        Button modify = new Button("Modificar Producto");
        Button consult = new Button("Consultar Inventario");
        
        register.setOnAction(e -> showRegister(tab));
        modify.setOnAction(e -> showModify(tab));
        consult.setOnAction(e -> showConsult(tab));
        startingPane.setAlignment(Pos.CENTER);
        
        startingPane.add(register, 0,0,1,2);
        startingPane.add(modify, 1,0);
        startingPane.add(consult, 1,1);
                
        return startingPane;
    }
    
    private static void showRegister(Tab tab){
        GridPane gp = new GridPane();
        ConnectionDB db = new ConnectionDB();
        
        ObservableList<String> typeList = 
            FXCollections.observableArrayList("Proveedor", "Producto");
        
        //For the providers combobox
        ObservableList<String> providersList;
        ArrayList<Provider> allProviders = db.getAllProviders();
        if(allProviders != null){
            ArrayList<String> providersListString = new ArrayList<String>();
            for (Provider provider : allProviders) {
                providersListString.add(provider.getName());
            }
            providersList = FXCollections.observableArrayList(providersListString);
        } else 
            providersList = FXCollections.observableArrayList(
                "Gamesa", "Coca Cola", "La Costeña");
        
        Label lType = new Label("Tipo:");
        Label lName = new Label("Nombre:");
        Label lProvider = new Label("Proveedor:");
        Label lPrice = new Label("Precio:");
        Label lQuantity = new Label("Cantidad:");
        
        ComboBox type = new ComboBox(typeList);
        TextField tfName = new TextField();
        ComboBox providers = new ComboBox(providersList);
        TextField tfPrice = new TextField();
        TextField tfQuantity = new TextField();
        
        Button close = new Button("Cancelar");
        Button save = new Button("Guardar");
        
        close.getStyleClass().add("close");
        save.getStyleClass().add("save");
        close.setOnAction(e -> tab.setContent(startingPane));
        save.setOnAction(e -> {
            if(type.getValue().toString().equals("Proveedor")){
                db.insertProvider(tfName.getText(), "1999-01-01", 0, 'N');
            }
            else if(type.getValue().toString().equals("Producto")){
                String providerName = providers.getValue().toString();
                Provider provider = db.getProvider(providerName);
                db.insertProduct(provider.getId(), tfName.getText(), 
                        Float.parseFloat(tfPrice.getText()), 
                        Integer.parseInt(tfQuantity.getText()));
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Se guardo el registro correctamente");
            alert.showAndWait()
            .filter(response -> response == ButtonType.OK)
            .ifPresent(new Consumer<ButtonType>() {
                @Override
                public void accept(ButtonType response) {
                    ;
                }
            });
        });
        gp.setAlignment(Pos.CENTER);
        
        gp.addRow(0, lType, lName, lProvider, lPrice, lQuantity);
        gp.addRow(1, type, tfName, providers, tfPrice, tfQuantity);
        gp.add(close, 3, 2);
        gp.add(save, 4, 2);
        
        tab.setContent(gp);
    }
        
    private static void showModify(Tab tab){
        BorderPane bp = new BorderPane();
        ConnectionDB db = new ConnectionDB();
        
        TableView tv = new TableView<Product>();
        
        TableColumn<Product, String> name = new TableColumn<>("Nombre");
//        TableColumn providerName = new TableColumn("Proveedor");
        TableColumn<Product, String> price = new TableColumn("Precio");
        TableColumn<Product, String> quantity = new TableColumn("Cantidad");
        
        HBox footer = new HBox();
        Button delete = new Button("Borrar");
        Button close = new Button("Cancelar");
        Button save = new Button("Guardar");
        
        name.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        price.setCellValueFactory(new PropertyValueFactory<>("precioString"));
        price.setCellFactory(TextFieldTableCell.forTableColumn());
        quantity.setCellValueFactory(new PropertyValueFactory<>("existenciaString"));
        quantity.setCellFactory(TextFieldTableCell.forTableColumn());
//        valueCol.setCellFactory(col -> new IntegerEditingCell());
//        providerName.setCellValueFactory(new PropertyValueFactory<>("precio"));
        
        delete.getStyleClass().add("delete");
        delete.setAlignment(Pos.CENTER_RIGHT);
        close.getStyleClass().add("close");
        close.setAlignment(Pos.CENTER_RIGHT);
        close.setOnAction(e -> tab.setContent(startingPane));
        save.getStyleClass().add("save");
        save.setAlignment(Pos.CENTER_RIGHT);
        tv.setPrefSize(600, 500);
        tv.setEditable(true);
        
        //update row on database when a field has been edited confirmed
        name.setOnEditCommit(e -> {
            Product p = e.getRowValue();
            db.updateProduct(p.getId(), p.getId_proveedor(), e.getNewValue(), 
                    p.getPrecio(), p.getExistencia()); 
        });
        price.setOnEditCommit(e -> {
            Product p = e.getRowValue();
            db.updateProduct(p.getId(), p.getId_proveedor(), p.getNombre(), 
                    Float.parseFloat(e.getNewValue()), p.getExistencia()); 
        });
        quantity.setOnEditCommit(e -> {
            Product p = e.getRowValue();
            db.updateProduct(p.getId(), p.getId_proveedor(), p.getNombre(), 
                    p.getPrecio(), Integer.parseInt(e.getNewValue())); 
        });
        
        tv.getColumns().addAll(name, price, quantity);
        tv.getItems().addAll(db.getAllProducts());
        footer.getChildren().addAll(delete, close, save);
        
        bp.setCenter(tv);
        bp.setBottom(footer);
        
        tab.setContent(bp);
    }

    private static void showConsult(Tab tab){
        BorderPane bp = new BorderPane();
        ConnectionDB db = new ConnectionDB();
        
        TextField tfSearch = new TextField();
        TableView tv = new TableView<Product>();
        
        TableColumn name = new TableColumn("Nombre");
        TableColumn amount = new TableColumn("Precio");
        TableColumn quantity = new TableColumn("Cantidad");
        
        Button close = new Button("Cancelar");
                
        name.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        amount.setCellValueFactory(new PropertyValueFactory<>("precio"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("existencia"));
        
        close.getStyleClass().add("close");
        close.setOnAction(e -> tab.setContent(startingPane));
        tfSearch.setPrefWidth(400);
        tfSearch.setId("search");
        tv.setEditable(true);
        
        tv.getColumns().addAll(name, amount, quantity);
        tv.getItems().addAll(db.getAllProducts());
        
        bp.setTop(tfSearch);
        bp.setCenter(tv);
        bp.setBottom(close);
        
        tab.setContent(bp);
    }
    
}
