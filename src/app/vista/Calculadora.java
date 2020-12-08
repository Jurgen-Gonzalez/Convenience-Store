package app.vista;

import app.modelo.ConnectionDB;
import app.modelo.Product;
import java.util.ArrayList;
import java.util.function.Consumer;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author wrath
 */
public class Calculadora {

    static ArrayList<Product> productsBought;
    static double buttonWidth = 80, buttonHeight = 60;
    static float amount = 0;
    static Label total;

    public static VBox calculadoraPane() {
        //Create Components
        VBox v = new VBox();
        GridPane gp = new GridPane();
        ConnectionDB db = new ConnectionDB();

        HBox h = new HBox();
        HBox hb = new HBox();
        FlowPane availableProducts = new FlowPane();
        FlowPane shoppingCart = new FlowPane();

        TextField tfSearch = new TextField();
        Label lPayment = new Label("Pago Recibido:");
        TextField tfPayment = new TextField();
        total = new Label("Total: $");
        Label lChange = new Label("Cambio: $");

        Button calculate = new Button("Calcular");
        Button accept = new Button("Aceptar");

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
        shoppingCart.setPrefSize(250, 300);
        calculate.setAlignment(Pos.CENTER_RIGHT);
        accept.setAlignment(Pos.CENTER_RIGHT);

        ArrayList<Product> products = db.getAllProducts();
        Button bProduct;
        productsBought = new ArrayList<>();
        for (Product product : products) {
            bProduct = new Button();
            bProduct.getStyleClass().add("product");
            bProduct.setPrefSize(buttonWidth, buttonHeight);
            bProduct.setWrapText(true);
            bProduct.setOnAction(e -> buyProductGraphical(shoppingCart, product));
            availableProducts.getChildren().add(getProductGraphical(product,
                    bProduct, shoppingCart));
        }

        calculate.setOnAction(e -> {
            try {
                float change = Float.parseFloat(tfPayment.getText()) - amount;
                if (change >= 0) {
                    lChange.setText("   Cambio: $" + change);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR,
                            "El pago recibido fue menor de lo que cuestan los productos");
                    alert.showAndWait()
                            .filter(response -> response == ButtonType.OK)
                            .ifPresent(new Consumer<ButtonType>() {
                                @Override
                                public void accept(ButtonType response) {
                                    ;
                                }
                            });
                }
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Ingresa el pago recibido primero");
                alert.showAndWait()
                        .filter(response -> response == ButtonType.OK)
                        .ifPresent(new Consumer<ButtonType>() {
                            @Override
                            public void accept(ButtonType response) {
                                ;
                            }
                        });
            }
        });

        accept.setOnAction(e -> {
            shoppingCart.getChildren().clear();
            amount = 0;
            total.setText("Total: $0");
            lChange.setText("   Cambio: $0");
            for (Product product : productsBought) {
                if (product.getExistencia() != 0) {
                    product.setExistencia(product.getExistencia() - 1);
                }
                db.updateProduct(product.getId(), product.getId_proveedor(),
                        product.getNombre(), product.getPrecio(),
                        product.getExistencia());

            }
            productsBought = new ArrayList<>();
        });

        // Add components to GridPane
        int col = 0, row = 0, row2 = 1, row3 = 2;
//        gp.add(availableProducts, col, row, 3,1);
//        gp.add(shoppingCart, col+3, row, 2,1);
        gp.addRow(row2, lPayment, tfPayment, total, lChange);
        gp.addRow(row3, calculate, accept);
        hb.getChildren().addAll(tfSearch);
        h.getChildren().addAll(availableProducts, shoppingCart);
        v.getChildren().addAll(hb, h, gp);
        return v;
    }

    private static StackPane getProductGraphical(Product product,
            Button bProduct, FlowPane shoppingCart) {
        StackPane sp = new StackPane();
        Label t1, t2, t3;
        t1 = new Label(product.getNombre());
        t2 = new Label("$" + String.valueOf(product.getPrecio()));
        t3 = new Label("#" + String.valueOf(product.getExistencia()));
        /*bProduct.setText(product.getNombre() + "\n$" + product.getPrecio()
                    + "  #" + product.getExistencia());*/
        t1.setId("prodName");
        t1.setWrapText(true);
        t1.setMouseTransparent(true);
        t2.setId("price");
        t2.setWrapText(true);
        t2.setMouseTransparent(true);
        t3.setId("quantity");
        t3.setWrapText(true);
        t3.setMouseTransparent(true);
        sp.getChildren().addAll(bProduct, t1, t2, t3);
        sp.setAlignment(t1, Pos.CENTER);
        sp.setAlignment(t2, Pos.BOTTOM_LEFT);
        sp.setAlignment(t3, Pos.BOTTOM_RIGHT);
        sp.setMaxSize(buttonWidth, buttonHeight);
        return sp;
    }

    //Equivalent to sending the product to shoppingCart
    private static void buyProductGraphical(FlowPane shoppingCart, Product product) {
        Button bProduct = new Button();
        bProduct.getStyleClass().add("product");
        bProduct.setPrefSize(buttonWidth, buttonHeight);
        bProduct.setWrapText(true);
        shoppingCart.getChildren().add(getProductGraphical(product,
                bProduct, shoppingCart));
        productsBought.add(product);
        amount += product.getPrecio();
        total.setText("Total: $" + amount);
    }

}
