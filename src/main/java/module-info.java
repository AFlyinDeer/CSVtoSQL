module term.project {
    requires javafx.controls;
    requires javafx.fxml;

    opens term.project to javafx.fxml;
    exports term.project;
}
