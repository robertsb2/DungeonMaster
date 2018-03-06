package Controller;

import Model.Utilities;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TitleController implements Initializable{
    Utilities utilities = new Utilities();

    @FXML
    private void gameSelect() {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/gameSelect.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = Main.getStage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void exit(){
        Main.exit();
    }

    public void tutorial() {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/tutorial.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = Main.getStage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        utilities.playSound("/Audio/Magna_Ingress_-_01_-_Bloody_Shadows.mp3", false);
    }
}
