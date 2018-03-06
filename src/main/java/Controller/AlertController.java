package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AlertController {

    @FXML
    private Button confirm;

    private GameController gameController = Main.getGameController();

    @FXML
    private void confirm(){
        gameController.deleteHero();
        Stage stage = (Stage) confirm.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancel() {
        Stage stage = (Stage) confirm.getScene().getWindow();
        stage.close();
    }
}
