package chessApplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChessApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChessApplication.class.getResource("chessView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Java Chess!");
        stage.setScene(scene);
        stage.show();
    }
}
