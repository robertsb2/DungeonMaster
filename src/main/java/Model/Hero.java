package Model;

import java.io.Serializable;

public class Hero extends Creature implements Serializable{
    private static final long serialVersionUID = 1L;

    private String name;
    private int strength;
    private int defense;
    private double maxHealth;
    private double curHealth;
    private int gold;
    private int pieces;
    private Pack pack;
    private Weapon weapon;
    private boolean hasQuest = false;



    public Hero() {
    }

    public Hero(String name, int strength, int defense, double maxHealth, double curHealth, int gold, int pieces) {
        this.setName(name);
        this.setStrength(strength);
        this.setDefense(defense);
        this.setMaxHealth(maxHealth);
        this.setCurHealth(curHealth);
        this.setGold(gold);
        this.setPieces(pieces);
        this.setPack(new Pack());
        this.setWeapon(new Weapon("Dagger","Attack Power +1",10,1));
    }

    public boolean isHasQuest() {
        return hasQuest;
    }

    public void setHasQuest(boolean hasQuest) {
        this.hasQuest = hasQuest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public double getCurHealth() {
        return curHealth;
    }

    public void setCurHealth(double curHealth) {
        this.curHealth = this.curHealth + curHealth;
        if (this.curHealth >  this.maxHealth){
            this.curHealth = this.maxHealth;
        }
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = this.gold + gold;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public Pack getPack() {
        return pack;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public String toString() {
        return this.getName() + " " +
                "Strength: " + this.getStrength() + " " +
                "Defense: " + this.getDefense() + " " +
                "Health: " + this.getCurHealth() + " " +
                "Gold: " + this.getGold() + " " +
                "Talisman Pieces " + this.getPieces();
    }
}
