package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TitleController{


    public TitleController() {
    }


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
        System.exit(0);
    }
}
