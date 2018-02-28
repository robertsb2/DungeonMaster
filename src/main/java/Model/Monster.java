package Model;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Monster extends Creature{
    private int strength;
    private int defense;
    private double currHealth;
    private double maxHealth;
    private int escapeRoll;
    private String type;
    private Image image;
    private int imgX;
    private int imgY;
    private int width;
    private int height;

    public Monster() {
    }

    public Monster(Types type) {
        this.setStrength(type.getStrength());
        this.setDefense(type.getDefense());
        this.setCurrHealth(type.getHealth());
        this.setEscapeRoll(type.getEscape());
        this.setType(type.getType());
        this.setImage(type.getPath());
        this.setImgX(type.getImgX());
        this.setImgY(type.getImgY());
        this.setWidth(type.getWidth());
        this.setHeight(type.getHeight());
        this.setMaxHealth(type.getHealth());
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

    public double getCurrHealth() {
        return currHealth;
    }

    public void setCurrHealth(int currHealth) {
        this.currHealth = this.currHealth + currHealth;
    }

    public int getEscapeRoll() {
        return escapeRoll;
    }

    public void setEscapeRoll(int escapeRoll) {
        this.escapeRoll = escapeRoll;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setImage(String path) {
        try {
            this.image = new Image(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Image getImage() {
        return image;
    }

    public int getImgX() {
        return imgX;
    }

    public void setImgX(int imgX) {
        this.imgX = imgX;
    }

    public int getImgY() {
        return imgY;
    }

    public void setImgY(int imgY) {
        this.imgY = imgY;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    @Override
    public String toString() {
        return this.getType() + "\n" +
                "Strength: ";
    }
}
