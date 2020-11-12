package app.vista;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author wrath
 */
public class Calculadora {
    public static VBox calculadoraPane(){
            //Create Components
        VBox v = new VBox();
        GridPane gp = new GridPane();
        
        HBox h = new HBox();
        HBox hb = new HBox();
        FlowPane availableProducts = new FlowPane();
        FlowPane shoppingCart = new FlowPane();
                
        Label lPayment = new Label("Pago Recibido:");
        TextField tfPayment = new TextField();
        Label total = new Label("Total: $");
        Label change = new Label("Cambio: $");
        
        Button calculate = new Button("Calcular");
        Button accept = new Button("Aceptar");       
        TextField tfSearch = new TextField();
        tfSearch.setPrefWidth(400);
        tfSearch.setId("search");
        // Configure components
        h.setSpacing(10);
        hb.setSpacing(10);
        v.setAlignment(Pos.CENTER);
        gp.setAlignment(Pos.CENTER);
        availableProducts.getStyleClass().add("flowpane");
        shoppingCart.getStyleClass().add("flowpane");
        availableProducts.setPrefSize(500, 300);
        shoppingCart.setPrefSize(200, 300);
        calculate.setAlignment(Pos.CENTER_RIGHT);
        accept.setAlignment(Pos.CENTER_RIGHT);
        
        
        availableProducts.getChildren().addAll(new Button("Chokis"),new Button("Coca Cola"));
        shoppingCart.getChildren().add(new Button("Chokis"));
        // Add components to GridPane
        int col = 0, row = 0, row2 = 1, row3 = 2;
//        gp.add(availableProducts, col, row, 3,1);
//        gp.add(shoppingCart, col+3, row, 2,1);
        gp.addRow(row2, lPayment, tfPayment, total, change);
        gp.addRow(row3, calculate, accept);
        hb.getChildren().addAll(tfSearch);
        h.getChildren().addAll(availableProducts, shoppingCart);
        v.getChildren().addAll(hb,h,gp);
        return v;
    }
    
}
