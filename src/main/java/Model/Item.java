package Model;

import java.io.Serializable;

abstract public class Item implements Serializable{
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private int cost;

    /**
     * default constructor
     */
    public Item() {
    }
    /**
     *
     * @param name item name
     * @param description item description
     * @param cost item cost
     */
    public Item(String name, String description, int cost) {
        this.setName(name);
        this.setDescription(description);
        this.setCost(cost);
    }

    /**
     *
     * @return item name
     */
    public String getName() {
        return name;
    }

    /**
     * sets item name
     * @param name item name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return item description
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets item description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return item cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * sets item cost
     * @param cost item cost
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     *
     * @return item name and description
     */
    @Override
    public String toString() {
        return this.getName() + "\n" +
                this.getDescription();
    }

    /**
     * abstract method intended to handle item usage
     * @param creature the creature the item will affect
     */
    abstract public void useItem (Creature creature);

}
