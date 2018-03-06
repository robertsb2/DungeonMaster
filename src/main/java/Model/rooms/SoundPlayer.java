package Model.rooms;

import Model.Utilities;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URISyntaxException;

public class SoundPlayer {

    MediaPlayer mediaPlayer;

    public void playSound(String audioFile, boolean fadeIn, boolean loop, double volume){

        Media sound = null;
        try {
            sound = new Media(Utilities.class.getClass().getResource(audioFile).toURI().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        mediaPlayer = new javafx.scene.media.MediaPlayer(sound);
        if(fadeIn){
            mediaPlayer.setVolume(0);
        } else {
            mediaPlayer.setVolume(volume);
        }
        mediaPlayer.play();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(mediaPlayer.volumeProperty(), volume)));
        timeline.play();
        if(loop){
            mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
        }
    }


    public void musicTransition(String audioFile, boolean fadeIn, boolean loop, double volume) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(mediaPlayer.volumeProperty(), 0)));
        timeline.setOnFinished(event -> playSound(audioFile,fadeIn,loop,volume));
        timeline.play();
    }



}
