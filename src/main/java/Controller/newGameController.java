package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class newGameController implements Initializable{
    @FXML
    private TextField name;

    private GameController gameController;

    /**
     * Runs initial setup for the scene
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameController = Main.getGameController();

    }

    @FXML
    private void start() throws IOException {
        gameController.newHero(name.getCharacters().toString());
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/intro.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
