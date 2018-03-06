package Model.rooms;

import Model.Creature;

public class Cavern extends Room {

    public Cavern(String type, String imgPath) {
        super(type,imgPath);
    }

    public String encounter(int floor, Creature... creatures) {
        return null;
    }

}
