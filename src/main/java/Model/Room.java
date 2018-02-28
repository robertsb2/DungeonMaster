package Model;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

abstract public class Room {
    private String type;
    private Image room;

    public Room() {
    }

    public Room(String type, String path) {
        this.setType(type);
        this.setRoom(path);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRoom(String room){
        try {
            this.room = new Image(new FileInputStream(room));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Image getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return this.getType();
    }

    abstract public void encounter(int floor, Creature... creatures);
}
