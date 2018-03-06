package Model.rooms;

import Model.Creature;
import javafx.scene.image.Image;

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
        this.room = new Image(String.valueOf(getClass().getResource(room)));
    }

    public Image getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return this.getType();
    }

    abstract public String encounter(int floor, Creature... creatures);
}
