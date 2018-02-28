package Controller;

import Model.Hero;
import Model.Savable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SaveSelectController implements Initializable {

    public SaveSelectController() {
    }
    private ArrayList<Savable> saveData;
    private final ObservableList<String> savedGames = FXCollections.observableArrayList();
    private GameController gameController;

    @FXML
    private Button load;
    @FXML
    private Button delete;
    @FXML
    private ListView saveFiles;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameController = Main.getGameController();
        updateList();
    }

    private void updateList() {
        saveData = gameController.getSaveData();
        savedGames.clear();
        if (saveData != null) {
            for (Savable hero : saveData) {
                if (hero instanceof Hero) {
                    savedGames.add(((Hero) hero).getName());
                }
            }
            saveFiles.setItems(savedGames);
        }

        if(savedGames.isEmpty() || !saveFiles.isFocused()) {
            load.setDisable(true);
            delete.setDisable(true);
        }
    }

    public void activateButtons(){
        if (!saveFiles.getSelectionModel().isEmpty()){
            load.setDisable(false);
            delete.setDisable(false);
        }
    }


    @FXML
    public void newGame() throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/newGame.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void load() throws IOException {
        gameController.loadHero(saveFiles.getSelectionModel().getSelectedItem().toString());
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/base.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void delete() throws IOException {
        gameController.setDeleteHero(saveFiles.getSelectionModel().getSelectedItem().toString());
        Stage alert = new Stage();
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/alert.fxml"));
        alert.setScene(new Scene(parent));
        alert.setAlwaysOnTop(true);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.initOwner(Main.getStage());
        alert.initModality(Modality.WINDOW_MODAL);
        alert.showAndWait();
        updateList();

    }

}
