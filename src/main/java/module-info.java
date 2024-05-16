module com.example.spotify {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.example.spotify to javafx.fxml;
    exports com.example.spotify;
}