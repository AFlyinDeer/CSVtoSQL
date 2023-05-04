package term.project;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        UIController.buildScene1(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}