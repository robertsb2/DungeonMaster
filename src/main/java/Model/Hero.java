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
    private double xp;
    private Pack pack;
    private Weapon weapon;
    private boolean hasQuest = false;
    private int level = 1;



    public Hero() {
    }

    public Hero(String name, int strength, int defense, double maxHealth, double curHealth, int gold, double xp) {
        this.setName(name);
        this.setStrength(strength);
        this.setDefense(defense);
        this.setMaxHealth(maxHealth);
        this.setCurHealth(curHealth);
        this.setGold(gold);
        this.setXp(xp);
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
        this.strength += strength;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense += defense;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth += maxHealth;
    }

    public double getCurHealth() {
        return curHealth;
    }

    public void setCurHealth(double curHealth) {
        this.curHealth += curHealth;
        if (this.curHealth >  this.maxHealth){
            this.curHealth = this.maxHealth;
        }
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold += gold;
    }

    public double getXp() {
        return xp;
    }

    public void setXp(double xp) {
        this.xp += xp;
    }

    public int getLevel() {
        return level;
    }

    public void levelUp() {
        this.level++;
        if (this.getLevel()%2 == 0){
            this.setStrength(1);
        } else {
            this.setDefense(1);
        }
        this.setXp(-this.getXp());
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
                "Talisman Pieces " + this.getXp();
    }
}
