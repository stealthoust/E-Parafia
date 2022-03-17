module edu.pwste {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.pwste to javafx.fxml;
    exports edu.pwste;
}
