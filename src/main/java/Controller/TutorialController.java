package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TutorialController implements Initializable{

    Image[]images = new Image[]{
            new Image(String.valueOf(getClass().getResource("/Images/howTo1.jpg"))),
            new Image(String.valueOf(getClass().getResource("/Images/howTo2.jpg"))),
            new Image(String.valueOf(getClass().getResource("/Images/howTo3.jpg"))),
            new Image(String.valueOf(getClass().getResource("/Images/howTo4.jpg"))),
            new Image(String.valueOf(getClass().getResource("/Images/howTo5.jpg"))),
    };

    GraphicsContext gc;
    private int i = 0;

    @FXML
    private Canvas canvas;
    @FXML
    private Button previous;
    @FXML
    private Button next;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        previous.setVisible(false);
        gc = canvas.getGraphicsContext2D();
        gc.drawImage(images[i],0,0,canvas.getWidth(),canvas.getHeight());
    }


    public void next() {
        i++;
        previous.setVisible(true);
        gc.drawImage(images[i],0,0,canvas.getWidth(),canvas.getHeight());
        if (i >= images.length - 1){
            next.setVisible(false);
        }
    }

    public void previous() {
        i--;
        next.setVisible(true);
        gc.drawImage(images[i],0,0,canvas.getWidth(),canvas.getHeight());
        if (i <= 0){
            previous.setVisible(false);
        }
    }

    public void back() throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/title.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
