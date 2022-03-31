module edu.pwste {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.kordamp.bootstrapfx.core;
    requires java.net.http;
    requires com.google.gson;
    opens edu.pwste to javafx.fxml;
    exports edu.pwste;
}
