package Model;

public class BossRoom extends Room {

    public BossRoom(String type, String path) {
        super(type, path);
    }

    @Override
    public void encounter(int floor, Creature... creatures) {

    }
}
