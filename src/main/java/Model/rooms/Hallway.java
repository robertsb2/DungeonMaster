package Model.rooms;

import Model.Creature;

public class Hallway extends Room {
    public Hallway(String type, String path){
        super(type, path);
    }

    @Override
    public String encounter(int floor, Creature... creatures) {
        return null;
    }
}
