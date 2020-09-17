package Homework_7;

import Homework_7.controller.Controller;
import Homework_7.util.DBManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.SQLException;

public class App extends Application {
    public static Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Orders");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        scene = new Scene(root, 600, 400);
        Controller controller = loader.getController();
        stage.setScene(scene);
        stage.setX(900);
        stage.setY(400);
        stage.show();
    }

    @Override
    public void stop() {
        try {
            DBManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
