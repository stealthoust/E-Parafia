module edu.pwste {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.kordamp.bootstrapfx.core;
    opens edu.pwste to javafx.fxml;
    exports edu.pwste;
}
