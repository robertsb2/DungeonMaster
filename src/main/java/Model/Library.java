package Model;


public class Library extends Room {
    public Library(String type, String imgPath) {
        super(type, imgPath);
    }

    @Override
    public void encounter(int floor, Creature... creatures) {

    }
}
