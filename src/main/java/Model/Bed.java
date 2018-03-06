package Model;

public class Bed extends Item {



    /**
     *
     * @param name item name
     * @param description item description
     * @param cost item cost
     */
    public Bed(String name, String description, int cost) {
        super(name, description, cost);

    }

    /**
     * grants healing effect to a hero creature
     * @param creature the creature the item will affect
     */
    @Override
    public void useItem(Creature creature) {
        if (creature instanceof Hero){
            Hero hero = (Hero) creature;
            double recover = hero.getMaxHealth();
            hero.setCurHealth(recover);
        }
    }
}
