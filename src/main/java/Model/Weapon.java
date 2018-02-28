package Model;

import java.io.Serializable;

public class Weapon extends Item implements Serializable{
    private static final long serialVersionUID = 1L;
    private int attackPower;

    public Weapon(String name, String description, int cost, int attackPower) {
        super(name, description, cost);
        this.setAttackPower(attackPower);
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    @Override
    public void useItem(Creature creature) {
    }
}
