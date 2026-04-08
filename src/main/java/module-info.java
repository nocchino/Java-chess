module com.example.scacchi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.net.http;
    requires com.google.gson;


    opens controller to javafx.fxml, com.google.gson;
    opens chessApplication to javafx.fxml;

    exports chessApplication;

}