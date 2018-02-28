package Model;

public class Shrine extends Room {

    public Shrine(String type, String path){
        super(type, path);
    }

    @Override
    public void encounter(int floor, Creature... creatures) {

    }
}
