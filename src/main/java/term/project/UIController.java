package term.project;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import java.io.File;
import java.util.ArrayList;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;

public class UIController {
    private static String filePath;
    private static ArrayList<Product> products;
    private static Stage stage;
    private static String appName = "CSV to SQL";
    private static int width = 600;
    private static int height = 400;
    private static String backgroundColor = "-fx-background-color: #1D1D1D;";
    private static String buttonColor = "-fx-background-color: #434343";
    private static String buttonColorLight = "-fx-background-color: #888686";
    private static String textColor = "#DFDFDF";
    
    public static void buildScene1(Stage s) {
        stage = s;

        // Grid pane setup
        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setVgap(10);
        root.setHgap(10);
        root.setAlignment(Pos.CENTER);
        root.setStyle(backgroundColor);

        // Set up title label
        Label titleLabel = new Label("Browse For Local CSV File");
        titleLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 24));
        titleLabel.setTextFill(Color.valueOf(textColor));
        root.add(titleLabel, 0, 0, 2, 1);
        GridPane.setHalignment(titleLabel, HPos.CENTER);

        // Add space beneath title label
        root.addRow(4);
        
        // Create the TextField for the file URL
        TextField fileUrlField = new TextField();
        fileUrlField.setPrefWidth(300);
        root.add(fileUrlField, 0, 5);
        
        // Create the Browse button
        Button browseButton = new Button("Browse");
        browseButton.setStyle(buttonColor);
        browseButton.setTextFill(Color.valueOf(textColor));
        root.add(browseButton, 1, 5);
        
        // Create the Confirm button
        Button confirmButton = new Button("Confirm");
        confirmButton.setDisable(true);
        confirmButton.setStyle(buttonColorLight);
        confirmButton.setTextFill(Color.valueOf(textColor));
        confirmButton.setOnAction(e -> {
            products = ProductConverter.GetProducts(filePath);
            buildScene2(stage);
        });
        root.add(confirmButton, 0, 6, 2, 1);
        GridPane.setHalignment(confirmButton, HPos.CENTER);

        // Set up message label
        Label messageLabel = new Label();
        root.add(messageLabel, 0, 7, 2, 1);
        GridPane.setHalignment(messageLabel, HPos.CENTER);
        messageLabel.setTextFill(Color.valueOf(textColor));
        
        browseButton.setOnAction(e -> {
            // Open a file chooser dialog
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
            );
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            File selectedFile = fileChooser.showOpenDialog(stage);

            // If file exists
            if (selectedFile != null) {
                filePath = selectedFile.getAbsolutePath();
                // If file is CSV
                if(filePath.endsWith(".csv")) {
                    fileUrlField.setText(filePath);
                    messageLabel.setText("");
                    confirmButton.setDisable(false);
                } else {
                    messageLabel.setText("Please make sure you are only selecting CSV files.");
                    confirmButton.setDisable(true);
                }
            }
        });

        
        // Create the Scene
        Scene scene = new Scene(root, width, height);
        stage.setTitle(appName);
        stage.setScene(scene);
        stage.show();
    }

    public static void buildScene2(Stage s) {
        stage = s;
        BorderPane root = new BorderPane();
        root.setStyle(backgroundColor);

        // Listview requires an observableList so we will convert products to an observableList
        ObservableList<Product> observableList = FXCollections.observableArrayList(products);
        ListView<Product> listView = new ListView<>(observableList);
        listView.setStyle(backgroundColor);

        // Setup buttons
        Button editButton = new Button("Edit");
        editButton.setStyle(buttonColor);
        editButton.setTextFill(Color.valueOf(textColor));
        Button deleteButton = new Button("Delete");
        deleteButton.setStyle(buttonColor);
        deleteButton.setTextFill(Color.valueOf(textColor));
        Button sqlButton = new Button("Generate SQL");
        sqlButton.setStyle(buttonColorLight);
        sqlButton.setTextFill(Color.valueOf(textColor));

        editButton.setOnAction(e -> {
            Product selectedProduct = listView.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                editProduct(selectedProduct); 
            }
        });

        deleteButton.setOnAction(e -> {
            // Get the selected product and remove it from the list
            Product selectedProduct = listView.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                products.remove(selectedProduct);

                // Rerender scene with updated product info
                buildScene2(stage);
            }
        });

        sqlButton.setOnAction(e -> {
            buildScene3(s);
        });

        // Align edit, delete and sql buttons at the bottom with a dynamic spacer between
        Region r = new Region();
        HBox buttonBox = new HBox(10, editButton, deleteButton, r, sqlButton);
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        buttonBox.setPadding(new Insets(10));
        HBox.setHgrow(r, Priority.ALWAYS);


        // Set up title label
        Label titleLabel = new Label("Review Products");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setPadding(new Insets(10));
        titleLabel.setTextFill(Color.valueOf(textColor));

        // Add components to root
        root.setTop(titleLabel);
        root.setCenter(listView);
        root.setBottom(buttonBox);

        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

    public static void buildScene3(Stage s) {
        stage = s;
        String Sql = SQLGenerator.ConvertProductToSQL(products);
        BorderPane root = new BorderPane();
        root.setStyle(backgroundColor);

        // Set up text area
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setStyle("-fx-font-size: 14pt;");
        textArea.setText(Sql);

        // Set up copy button
        Button copyButton = new Button("Copy");
        copyButton.setStyle(buttonColor);
        copyButton.setTextFill(Color.valueOf(textColor));
        copyButton.setOnAction(e -> {
            String text = textArea.getText();
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(text);
            clipboard.setContent(content);
        });

        // Set up start over button
        Button startOverButton = new Button("Start Over");
        startOverButton.setStyle(buttonColor);
        startOverButton.setTextFill(Color.valueOf(textColor));
        startOverButton.setOnAction(e -> {
            buildScene1(s);
        });

        // Set up quit button
        Button quitButton = new Button("Quit");
        quitButton.setStyle(buttonColor);
        quitButton.setTextFill(Color.valueOf(textColor));
        quitButton.setOnAction(e -> {
            System.exit(0);
        });

        // Set up button box
        Region r = new Region();
        HBox buttonBox = new HBox(10, startOverButton, quitButton, r, copyButton);
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setStyle(backgroundColor);
        HBox.setHgrow(r, Priority.ALWAYS);

        // Set up title label
        Label titleLabel = new Label("SQL Result");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setPadding(new Insets(10));
        titleLabel.setTextFill(Color.valueOf(textColor));

        // Add components to root
        root.setTop(titleLabel);
        root.setCenter(textArea);
        root.setBottom(buttonBox);

        // Set up scene
        Scene scene = new Scene(root, width, height);
        stage.setTitle(appName);
        stage.setScene(scene);
        stage.show();
    }

    private static void editProduct(Product selectedProduct) {
         // Open the edit dialog
        Dialog<Product> dialog = new Dialog<>();
        dialog.setTitle("Edit Product");

        // Set the button types
        ButtonType saveButtonType = new ButtonType("Save", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // Create the dialog form
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(20));

        // Add form fields for each property
        TextField nameField = new TextField(selectedProduct.getName());
        TextField skuField = new TextField(selectedProduct.getSKU());
        TextField colorField = new TextField(selectedProduct.getColor());
        TextField quantityField = new TextField(selectedProduct.getQuantity());
        TextField dateAddedField = new TextField(selectedProduct.getDateAdded());
        TextField sizeField = new TextField(selectedProduct.getSize());

        form.add(new Label("Name:"), 0, 0);
        form.add(nameField, 1, 0);
        form.add(new Label("SKU:"), 0, 1);
        form.add(skuField, 1, 1);
        form.add(new Label("Color:"), 0, 2);
        form.add(colorField, 1, 2);
        form.add(new Label("Quantity:"), 0, 3);
        form.add(quantityField, 1, 3);
        form.add(new Label("Date Added:"), 0, 4);
        form.add(dateAddedField, 1, 4);
        form.add(new Label("Size:"), 0, 5);
        form.add(sizeField, 1, 5);

        dialog.getDialogPane().setContent(form);

        // Update the selectedProduct object when the save button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                selectedProduct.Name = nameField.getText();
                selectedProduct.SKU = skuField.getText();
                selectedProduct.Color = colorField.getText();
                selectedProduct.Quantity = quantityField.getText();
                selectedProduct.DateAdded = dateAddedField.getText();
                selectedProduct.Size = sizeField.getText();

                // Rerender scene with updated product info
                buildScene2(stage);
            }
            return null;
        });

        // Show the dialog and update the selected product if a new product was created
        Optional<Product> result = dialog.showAndWait();
        result.ifPresent(newProduct -> {
            // Remove the old product and add the new product to the list
            products.remove(selectedProduct);
            products.add(newProduct);
        });
    }
}
