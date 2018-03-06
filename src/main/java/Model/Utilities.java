package Model;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import javax.rmi.CORBA.Util;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Random;

public class Utilities {

    private static final double XP_NEEDED = 9;
    private static final double XP_AWARDED = 4;
    private static MediaPlayer mediaPlayer;
    private Hero hero;



    public String reader(String filename) {
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(filename)));
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line);
            }
        } catch (Exception e){
            System.out.println("Reader Error");
            e.printStackTrace();
        }
        return text.toString();
    }

    public static int getRandom(int n, int bound, int min){
        Random random = new Random();
        int result = 0;
        for (int i = 0; i < n ; i++) {
            result += random.nextInt(bound)+ min;
        }
        return result;
    }

    public static double getXpNeeded(int level) {
        return (level * level) * XP_NEEDED;
    }

    public static double getXpRewarded(int floor){
        return (floor * floor) * XP_AWARDED;
    }

    public void playSound(String audioFile, boolean fadeIn){

        Media sound = null;
        try {
            sound = new Media(Utilities.class.getClass().getResource(audioFile).toURI().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        mediaPlayer = new MediaPlayer(sound);
        if(fadeIn){
            mediaPlayer.setVolume(0);
        }
        mediaPlayer.play();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(5),
                        new KeyValue(mediaPlayer.volumeProperty(), 1)));
        timeline.setOnFinished(event -> mediaPlayer.stop());
        timeline.play();
    }


    public static void stop() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(5),
                        new KeyValue(mediaPlayer.volumeProperty(), 0)));
        timeline.setOnFinished(event -> mediaPlayer.stop());
        timeline.play();
    }
}
