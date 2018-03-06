package Controller;

import Model.Utilities;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import javax.rmi.CORBA.Util;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class Main extends Application{
    private static Stage primaryStage;
    private static GameController gameController;
    private Utilities utilities = new Utilities();;

    /**
     * Program entry point
     * @param args arguments
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        gameController = new GameController();
        Application.launch(Main.class, args);
    }

    static GameController getGameController() {
        return gameController;
    }

    static Stage getStage() {
        return primaryStage;
    }

    /**
     * Sets and displays primary stage
     * @param stage the primary stage
     * @throws Exception
     */
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

    static void exit(){
        System.exit(0);
    }
}
