package Model.rooms;

import Model.Creature;
import Model.rooms.Room;

public class Shrine extends Room {

    public Shrine(String type, String path){
        super(type, path);
    }

    @Override
    public String encounter(int floor, Creature... creatures) {
        return "You found a suspicious altar... Pray at it?";
    }
}
