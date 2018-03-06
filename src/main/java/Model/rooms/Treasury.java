package Model.rooms;

import Model.Creature;
import Model.Hero;
import Model.Utilities;

public class Treasury extends Room {
    public Treasury(String type, String path) {
        super(type, path);
    }

    @Override
    public String encounter(int floor, Creature... creatures) {
        int gold = Utilities.getRandom(1,(3*floor),floor) + floor;

        for (Creature creature : creatures){
            if (creature instanceof Hero){
                ((Hero) creature).setGold(gold);
            }
        }
        return "You found " + gold + " gold!";
    }
}
