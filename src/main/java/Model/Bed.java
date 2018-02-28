package Model;

public class Bed extends Item {

    private static final int FULL_HEAL_COST = 30;

    public Bed(String name, String description, int cost) {
        super(name, description, cost);

    }


    @Override
    public void useItem(Creature creature) {
        if (creature instanceof Hero){
            Hero hero = (Hero) creature;
            double recover = hero.getMaxHealth();
            if (this.getCost() < FULL_HEAL_COST){
                recover = recover / 2;
            }
            hero.setCurHealth(recover);
        }
    }
}
