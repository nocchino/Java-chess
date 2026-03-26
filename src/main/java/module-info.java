module com.example.scacchi {
    requires javafx.controls;
    requires javafx.fxml;


    opens controller to javafx.fxml;
    opens chessApplication to javafx.fxml;
    exports chessApplication;
}