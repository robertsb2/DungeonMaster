package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AlertController {

    @FXML
    public Button confirm;
    @FXML
    public Button cancel;

    GameController gameController = Main.getGameController();

    public void confirm(MouseEvent mouseEvent){
        gameController.deleteHero();
        Stage stage = (Stage) confirm.getScene().getWindow();
        stage.close();
    }

    public void cancel(MouseEvent mouseEvent) {
        Stage stage = (Stage) confirm.getScene().getWindow();
        stage.close();
    }
}
