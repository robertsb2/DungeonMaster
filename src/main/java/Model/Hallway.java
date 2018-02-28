package Model;

public class Hallway extends Room {
    public Hallway(String type, String path){
        super(type, path);
    }

    @Override
    public void encounter(int floor, Creature... creatures) {

    }
}
