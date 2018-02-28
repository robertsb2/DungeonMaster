package Controller;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Main extends Application{
    public static Stage primaryStage;
    private static GameController gameController;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        gameController = new GameController();
        Application.launch(Main.class, args);
    }

    public static GameController getGameController() {
        return gameController;
    }

    public static Stage getStage() {
        return primaryStage;
    }

    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/title.fxml"));
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.sizeToScene();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setOnCloseRequest(event -> gameController.save());
        stage.show();


    }
}
