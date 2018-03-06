package Model;

public enum Types {
    //TYPE("name", Health, Strength, Defense, Escape Requirement,imageX, imageY, width, height)
    GOBLIN("Goblin", 20, 8, 8, 10,"/Images/goblin.png",220,255,200,206),
    ZOMBIE("Zombie", 25, 10, 11, 12,"/Images/zombie.png",230,200,129,250),
    BANSHEE("Banshee", 20, 12, 12, 14,"/Images/banshee.png",210,80,225,250),
    VAMPIRE("Vampire", 23, 14, 13, 16,"/Images/vampire.png",230,170,127,250),
    GHOUL("Ghoul", 25, 16, 14, 18,"/Images/ghoul.png",210,190,96,225),
    SPIDER("Spider", 28, 18, 15, 20,"/Images/spider.png",80,80,360,400),
    MINOTAUR("Minotaur", 30, 20, 16, 22,"/Images/minotaur.png",80,100,250,300),
    CYCLOPS("Cyclops", 35, 22, 18, 24,"/Images/cyclops.png",40,80,344,350),
    DEMON("Demon", 100, 26, 25, 999,"/Images/demon.png",180,140,283,300);

    public final String type;
    public final int health;
    public final int strength;
    public final int defense;
    public final int escape;
    public final String path;
    public final int imgX;
    public final int imgY;
    public final int width;
    public final int height;

    Types(String type, int health, int strength, int defense, int escapeRoll, String path, int imgX, int imgY, int width, int height){
        this.type = type;
        this.health = health;
        this.strength = strength;
        this.defense = defense;
        this.escape = escapeRoll;
        this.path = path;
        this.imgX = imgX;
        this.imgY = imgY;
        this.width = width;
        this.height = height;
    }

    public int getStrength() {
        return strength;
    }

    public int getDefense() {
        return defense;
    }

    public int getHealth() {
        return health;
    }

    public int getEscape() {
        return escape;
    }

    public String getType() {
        return type;
    }

    public String getPath() {
        return path;
    }

    public int getImgX() {
        return imgX;
    }

    public int getImgY() {
        return imgY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}