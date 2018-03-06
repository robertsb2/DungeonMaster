package Controller;

import Model.rooms.SoundPlayer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application{
    private static Stage primaryStage;
    private static GameController gameController;
    private static SoundPlayer soundPlayer= new SoundPlayer();

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

    public static SoundPlayer getSoundPlayer() {
        return soundPlayer;
    }

    static void exit(){
        System.exit(0);
    }
}
