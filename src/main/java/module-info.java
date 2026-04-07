module com.example.scacchi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.net.http;


    opens controller to javafx.fxml;
    opens chessApplication to javafx.fxml;
    exports chessApplication;
}