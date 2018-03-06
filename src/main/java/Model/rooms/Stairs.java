package Model.rooms;

import Model.Creature;
import Model.rooms.Room;

public class Stairs extends Room {
    public Stairs(String type, String path) {
        super(type, path);
    }

    @Override
    public String encounter(int floor, Creature... creatures) {
        return "You found the stairs for this floor.";
    }
}
