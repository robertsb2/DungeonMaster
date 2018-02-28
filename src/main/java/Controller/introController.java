package Controller;

import Model.Utilities;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class introController implements Initializable{
    private int DIALOGUE_FILE_COUNT = 1;
    private final int MAX_FILES = 2;

    @FXML
    private TextArea text;

    public void displayText() throws IOException {
        if(DIALOGUE_FILE_COUNT <= MAX_FILES) {
            text.appendText(Utilities.reader("src/main/resources/dialogue/Intro" + DIALOGUE_FILE_COUNT + ".txt"));
            text.appendText("\n");
            text.appendText("\n");
            DIALOGUE_FILE_COUNT++;
        } else {
            next();
        }
    }

    public void next() throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/base.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        text.setWrapText(true);
        text.setDisable(true);
    }
}
