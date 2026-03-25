module com.example.scacchi {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.scacchi to javafx.fxml;
    exports com.example.scacchi;
}