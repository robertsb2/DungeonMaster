package Model;

public class Stairs extends Room {
    public Stairs(String type, String path) {
        super(type, path);
    }

    @Override
    public void encounter(int floor, Creature... creatures) {

    }
}
