package app.vista;

import app.modelo.ConnectionDB;
import app.modelo.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.function.Consumer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author wrath
 */
public class Login {

    public VBox loginPane(Stage stage) {
        VBox v = new VBox();
        GridPane gp = new GridPane();

        ImageView loginIV = new ImageView();

        Label lUser = new Label("Usuario");
        Label lPwd = new Label("Contraseña");
        TextField tfUser = new TextField();
        TextField tfPwd = new TextField();

        Button bLogin = new Button("Ingresar");
        Principal p = new Principal();
        ConnectionDB db = new ConnectionDB();
        bLogin.setOnAction(e -> {
            //checkIdentification();
            User user = db.getUser(1);
            if (tfUser.getText().trim().equals(user.getNombre().trim())
                    && tfPwd.getText().trim().equals(user.getContrasena().trim())) {
                stage.setScene(new Scene(p.principalPane(stage), 800, 500));
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Usuario no encontrado");
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

            try {
                loginIV.setImage(new Image(new FileInputStream(
                        new File("src/images/login.png"))));
            } catch (FileNotFoundException fnfe) {
                fnfe.printStackTrace();
            }
            loginIV.setFitHeight(70);
            loginIV.setFitWidth(70);
            loginIV.setPreserveRatio(true);

            gp.add(lUser, 0, 0);
            gp.add(lPwd, 0, 1);
            gp.add(tfUser, 1, 0);
            gp.add(tfPwd, 1, 1);
            gp.setAlignment(Pos.CENTER);

            v.getChildren().addAll(loginIV, gp, bLogin);
            v.setAlignment(Pos.CENTER);
            return v;
        }
    }
