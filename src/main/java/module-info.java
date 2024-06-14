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
    requires google.api.client;
    requires google.api.services.youtube.v3.rev222;
    requires commons.io;
    requires vget;
    requires jdk.compiler;
    requires com.google.api.client;
    requires com.google.api.client.json.gson;

    opens com.example.spotify to javafx.fxml;
    exports com.example.spotify;
}