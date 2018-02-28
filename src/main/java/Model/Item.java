package Model;

import java.io.Serializable;

abstract public class Item implements Serializable{
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private int cost;

    public Item() {
    }

    public Item(String name, String description, int cost) {
        this.setName(name);
        this.setDescription(description);
        this.setCost(cost);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return this.getName() + "\n" +
                this.getDescription();
    }


    abstract public void useItem (Creature creature);

}
