package Model;

public class Cavern extends Room{
    Hero hero = null;
    Monster monster = null;

    public Cavern(String type, String imgPath) {
        super(type,imgPath);
    }

    public void encounter(int floor, Creature... creatures) {
    }

}
