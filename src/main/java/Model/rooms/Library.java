package Model.rooms;


import Model.Creature;
import Model.Hero;
import Model.Monster;
import Model.Utilities;

public class Library extends Room {
    public Library(String type, String imgPath) {
        super(type, imgPath);
    }

    @Override
    public String encounter(int floor, Creature... creatures) {
        int chance = Utilities.getRandom(1 ,100,1);
        Hero hero = null;
        Monster monster = null;
        for (Creature creature : creatures){
            if (creature instanceof Hero){
                hero = (Hero) creature;
            } else if (creature instanceof Monster){
                monster = (Monster) creature;
            }
        }
        if (hero != null && monster != null) {
            if (chance > 90) {
                hero.setDefense(1);
                return "You found a book about " + monster.getType() + "s. Your defense has increased by 1";
            } else if (chance > 80) {
                hero.setStrength(1);
                return "You found a book about " + monster.getType() +"s. Your strength has increased by 1";
            } else if (chance > 50){
                int gold = Utilities.getRandom(1,5,1);
                hero.setGold(gold);
                return "You found " + gold + " gold!";
            } else {
                return "You find nothing of interest here";
            }
        }
        return null;
    }
}
