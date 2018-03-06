package Model.rooms;

import Model.Creature;

public class BossRoom extends Room {

    public BossRoom(String type, String path) {
        super(type, path);
    }

    @Override
    public String encounter(int floor, Creature... creatures) {
        return null;
    }
}
