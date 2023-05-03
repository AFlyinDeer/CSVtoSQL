package term.project;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
        test();
    }

    public static void main(String[] args) {
        launch();
        
    }
    public static void test() {
        // Eventually, the UI will ask for the URL to a file instead of this hardcoded string
        String TestURL = "C:\\Users\\Darth Wader\\Desktop\\School\\Semester 5\\Java\\TermProject\\TermProjectMain\\main\\TestProducts.csv";
    
        ArrayList<Product> Products = ProductConverter.GetProducts(TestURL);
        
        String Sql = SQLGenerator.ConvertProductToSQL(Products);
        System.out.println(Sql);


        // TODO: UI
        // User Flow
            // Allow user to browse for CSV url
            // Allow user to click button that gets Products lsit from CSV File (DONE)
            // Display list of products to User
            // Allow the User to edit or delete products (Half Done)
            // Allow user to click button that generates SQL code (DONE)
        
    }

}