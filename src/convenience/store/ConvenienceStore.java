/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convenience.store;

import app.modelo.ConnectionDB;
import app.vista.Login;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author wrath
 */
public class ConvenienceStore extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Login l = new Login();
        
        ConnectionDB db = new ConnectionDB();
//        if(db.insertClient("General", "direccion", "telefono"))
//            System.out.println("Se agrego a la base de datos");
        primaryStage.setTitle("Abarrotes Gonzalez");
        Scene scene = new Scene(l.loginPane(primaryStage),800,500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
