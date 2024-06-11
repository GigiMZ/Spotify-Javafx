module com.example.spotify {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires java.dotenv;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires javafx.media;

    opens com.example.spotify to javafx.fxml;
    exports com.example.spotify;
}