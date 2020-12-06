package app.vista;

import app.modelo.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

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
        
        ObservableList<String> typeList = 
            FXCollections.observableArrayList("Proveedor", "Producto");
        ObservableList<String> providersList = 
            FXCollections.observableArrayList(
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
            
        });
        gp.setAlignment(Pos.CENTER);
//        save.setOnAction(e -> register());
        
        gp.addRow(0, lType, lName, lProvider, lPrice, lQuantity);
        gp.addRow(1, type, tfName, providers, tfPrice, tfQuantity);
        gp.add(close, 3, 2);
        gp.add(save, 4, 2);
        
        tab.setContent(gp);
    }
    
    
    private static void showModify(Tab tab){
        BorderPane bp = new BorderPane();
        
        TableView tv = new TableView<Product>();
        
        TableColumn day = new TableColumn("Imagen");
        TableColumn provider = new TableColumn("Nombre");
        TableColumn amount = new TableColumn("Precio");
        TableColumn products = new TableColumn("Cantidad");
        TableColumn delete = new TableColumn("Borrar");
        
        HBox footer = new HBox();
        Button close = new Button("Cancelar");
        Button save = new Button("Guardar");
        
        close.getStyleClass().add("close");
        close.setAlignment(Pos.CENTER_RIGHT);
        close.setOnAction(e -> tab.setContent(startingPane));
        save.getStyleClass().add("save");
        save.setAlignment(Pos.CENTER_RIGHT);
        tv.setPrefSize(600, 500);
        tv.setEditable(true);
        
        tv.getColumns().addAll(day, provider, amount, products, delete);
        footer.getChildren().addAll(close, save);
        
        bp.setCenter(tv);
        bp.setBottom(footer);
        
        tab.setContent(bp);
    }

    private static void showConsult(Tab tab){
        BorderPane bp = new BorderPane();
        
        TextField tfSearch = new TextField();
        TableView tv = new TableView<Product>();
        
        TableColumn day = new TableColumn("Imagen");
        TableColumn provider = new TableColumn("Nombre");
        TableColumn amount = new TableColumn("Precio");
        TableColumn products = new TableColumn("Cantidad");
                
        Button close = new Button("Cancelar");
        
        close.getStyleClass().add("close");
        close.setOnAction(e -> tab.setContent(startingPane));
        tfSearch.setPrefWidth(400);
        tfSearch.setId("search");
        tv.setEditable(true);
        
        tv.getColumns().addAll(day, provider, amount, products);
        
        bp.setTop(tfSearch);
        bp.setCenter(tv);
        bp.setBottom(close);
        
        tab.setContent(bp);
    }
}
