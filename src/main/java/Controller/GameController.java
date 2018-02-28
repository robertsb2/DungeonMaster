package Controller;

import Model.*;

import java.io.*;
import java.util.ArrayList;

public class GameController {

    private ArrayList<Savable> saveData;
    private String filename = "src/main/resources/saves/data.sav";
    private Hero currentHero;
    private Pack pack;
    private Room currentRoom;
    private String deleteHero;
    private final int INIT_STRENGTH = 10;
    private final int INIT_DEFENSE = 10;
    private final double INIT_HEALTH = 30;
    private final double INIT_MAXHEALTH = 30;
    private final int INIT_GOLD = 10;
    private final int INIT_PIECES = 0;

    private boolean stairs = false;
    private boolean floorCleared;
    private int roomsExplored;
    private int hallwayCount;
    private int cavernCount;
    private int libraryCount;
    private int stairsCount;
    private int treasuryCount;
    private int shrineCount;
    private int floorSize;
    private Rooms roomType = null;
    private int floor;
    private final int TOP_FLOOR = 9;
    private Monster currentMonster;
    private boolean fighting = false;
    private Store[] shops;


    public GameController() throws IOException, ClassNotFoundException {
        saveData = new ArrayList<>();
        Item[] weapons = new Item[]{
                new Weapon("Dagger","Attack Power +1",10,1),
                new Weapon("Short Sword","Attack Power +2", 30,2),
                new Weapon("Long Sword","Attack Power +3", 60,3),
                new Weapon("Broadsword","Attack Power +4", 90,4),
                new Weapon("Mace","Attack Power +5", 120,5),
                new Weapon("Battle Axe","Attack Power +6", 150,6)};
        Item[] potions = new Item[]{
                new Potion("Minor Potion", "Recovers 5 Health", 10,5),
                new Potion("Minor Potion", "Recovers 10 Health", 20,10),
                new Potion("Minor Potion", "Recovers 155 Health", 30,15)};

        Item[] beds = new Item[]{
                new Bed("Quick Nap", "Recovers half Health", 15),
                new Bed("Rest Overnight", "Recover all Health", 30),
        };
        Blacksmith blacksmith = new Blacksmith("What can I make for you?",weapons);
        PotionShop potionShop = new PotionShop("I have many potions. See any you want?",potions);
        Inn inn = new Inn("Welcome. I have some nice beds available.", beds);
        load();
        shops = new Store[]{blacksmith,potionShop,inn};

    }


    void save() {
        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(saveData);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void load() throws IOException, ClassNotFoundException {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
            saveData = (ArrayList<Savable>) in.readObject();
        } catch (EOFException e) {
            save();
            load();
        }
    }

    public ArrayList<Savable> getSaveData() {
        return saveData;
    }

    public void loadHero(String name) {
        for (Savable hero : saveData){
            if (hero instanceof Hero){
                if (((Hero) hero).getName().equals(name)){
                    this.currentHero = (Hero) hero;
                    pack = currentHero.getPack();
                    return;
                }
            }
        }
        throw new GameLoadException("game load failed");
    }

    public void deleteHero() {
        Hero temp = null;
        for (Savable hero : saveData){
            if (hero instanceof Hero){
                if (((Hero) hero).getName().equals(deleteHero)){
                    temp = (Hero) hero;
                }
            }
        }
        saveData.remove(temp);
        save();
    }

    public void setDeleteHero(String deleteHero) {
        this.deleteHero = deleteHero;
    }

    public void newHero(String name) {
        this.currentHero = new Hero(name,INIT_STRENGTH,INIT_DEFENSE,INIT_MAXHEALTH,INIT_HEALTH,INIT_GOLD,INIT_PIECES);
        pack = currentHero.getPack();
        saveData.add(currentHero);
        save();
    }

    public Hero getHero() {
        return currentHero;
    }


    
    
    
    

    public void useItem(String s) {
        Item choice = null;
        for (Item item : pack){
            if (item.getName().equals(s)){
                choice = item;
                break;
            }
        }
        choice.useItem(currentHero);
        pack.remove(choice);
    }

    
    
    
    
    

    public void createDungeon(){
        createFloor();
    }

    private void createFloor() {
        floor++;
        stairs = false;
        floorCleared = false;
        roomsExplored = 0;
        hallwayCount = Utilities.getRandom(1,4,3);
        cavernCount = Utilities.getRandom(1,4,3);
        libraryCount = Utilities.getRandom(1,4,1);
        stairsCount = 1;
        treasuryCount = Utilities.getRandom(1,3,0);
        shrineCount = Utilities.getRandom(1,2,1);
        floorSize = hallwayCount + cavernCount + libraryCount + stairsCount + treasuryCount + shrineCount;
    }

    private Rooms roomType(int i){
        switch (i){
            case 1:
                return Rooms.HALLWAY;
            case 2:
                return Rooms.CAVERN;
            case 3:
                return Rooms.LIBRARY;
            case 4:
                stairs = true;
                return Rooms.STAIRS;
            case 5:
                return Rooms.TREASURY;
            case 6:
                return Rooms.SHRINE;
            default:
                return null;
        }
    }

    private boolean availableRooms(int i) {
        switch (i){
            case 1:
                if(hallwayCount > 0){
                    hallwayCount--;
                    return true;
                } else return false;
            case 2:
                if(cavernCount > 0){
                    cavernCount--;
                    return true;
                } else return false;
            case 3:
                if(libraryCount > 0){
                    libraryCount--;
                    return true;
                } else return false;
            case 4:
                if (stairsCount > 0){
                    stairsCount--;
                    return true;
                } else return false;
            case 5:
                if (treasuryCount > 0){
                    treasuryCount--;
                    return true;
                } else return false;
            case 6:
                if (shrineCount > 0){
                    shrineCount --;
                    return true;
                } else return false;
        }


        return true;
    }

    public Room getRoom() {
        if(floor == TOP_FLOOR){
            currentRoom = new BossRoom("Ballroom","/src/main/resources/Images/bossDoor.jpg");
            return currentRoom;
        } else {
            boolean valid = false;
            int i = 0;
            int j;
            do {
                j = Utilities.getRandom(1, 100, 1);
                if (j <= 22) {
                    i = 1; //Hallway 22% chance
                } else if (j <= 44) {
                    i = 2; //Cavern 22% chance
                } else if (j <= 66) {
                    i = 3; //Library 22% chance
                } else if (j <= 80) {
                    i = 4; //Stairs 14% chance
                } else if (j <= 90) {
                    i = 5; //Treasury 10% chance
                } else if (j <= 100) {
                    i = 6; //Shrine 10% chance
                }

                if (availableRooms(i)) {
                    valid = true;
                }

                if (roomsExplored == floorSize) {
                    System.out.println("Floor Cleared");
                    floorCleared = true;
                }

            } while (!valid && !floorCleared);


            if (!floorCleared) {
                roomType = roomType(i);
            }
            if (roomType != null) {
                switch (roomType) {
                    case CAVERN:
                        currentRoom = new Cavern("Cavern", roomType.imgPath);
                        currentMonster = createMonster();
                        fighting = true;
                        break;
                    case SHRINE:
                        currentRoom = new Shrine("Shrine", roomType.imgPath);
                        break;
                    case STAIRS:
                        currentRoom = new Stairs("Stairs",roomType.imgPath);
                        break;
                    case HALLWAY:
                        currentRoom = new Hallway("Hallway", roomType.imgPath);
                        currentMonster = createMonster();
                        fighting = true;
                        break;
                    case LIBRARY:
                        currentRoom = new Library("Library", roomType.imgPath);
                        break;
                    case TREASURY:
                        currentRoom = new Treasury("Treasury", roomType.imgPath);
                        break;
                }
            }
        }
        roomsExplored++;
        return currentRoom;
    }

    private Monster createMonster() {
        int rand = Utilities.getRandom(1,floor,0);
        Types type = Types.values()[rand];
        return new Monster(type);

    }

    public boolean isStairs() {
        return stairs;
    }

    public void useStairs() {
        createFloor();
    }

    public void encounter() {
        currentRoom.encounter(floor,currentHero,currentMonster);
    }

    public boolean isFloorCleared() {
        return floorCleared;
    }

    public Monster getMonster() {
        return currentMonster;
    }

    public boolean isFighting() {
        return fighting;
    }

    private static int getDamage(int strength, int defense, int atkPower) {
        int damage;
        int value = 0;

        value += Utilities.getRandom(2,3,1);


        value += (strength/2 + atkPower - defense/2);

        if (Utilities.getRandom(1,100,1) < 10){
            value += Utilities.getRandom(1,3,3);
        }

        if (value < 0){
            damage = 0;
        } else {
            damage = value;
        }
        return damage;
    }

    public boolean run(){

        if((Utilities.getRandom(2,10,1) + (currentHero.getStrength())) > (Utilities.getRandom(2,20,1) + currentMonster.getEscapeRoll())) {
            fighting = false;
            return true;
        } else {
            return false;
        }
    }

    public void fight() {

        int attack;
        int damage;
        attack = Utilities.getRandom(1,20,1);
        if(attack >= currentMonster.getDefense()) {
            damage = getDamage(currentHero.getStrength(), currentMonster.getDefense(), currentHero.getWeapon().getAttackPower());
            currentMonster.setCurrHealth(-damage);
        }
        if (currentMonster.getCurrHealth() > 0) {
            attack = Utilities.getRandom(1, 20, 1) + (currentMonster.getStrength() / 2);
            if (attack >= currentHero.getDefense()) {
                damage = getDamage(currentMonster.getStrength(), currentHero.getDefense(), 0);
                currentHero.setCurHealth(-damage);
            }
        } else {
            fighting = false;
        }
    }

    public void resetDungeon() {
        floor = 0;
    }

    public Store[] getShops() {
        return shops;
    }

    public boolean purchase(Store store, Object selectedItem) {
        String[] raw = selectedItem.toString().split(" -");
        String itemName = raw[0];
            if (store instanceof PotionShop) {
                for (Item item : store.getItems()) {
                    if (item.getName().equals(itemName) && currentHero.getGold() >= item.getCost()) {
                        currentHero.getPack().add(item);
                        currentHero.setGold(-item.getCost());
                        return true;
                    }
                }
            } else if (store instanceof Blacksmith) {
            for (Item item : store.getItems()) {
                if (item.getName().equals(itemName) && currentHero.getGold() >= item.getCost()) {
                    currentHero.setWeapon((Weapon) item);
                    currentHero.setGold(-item.getCost());
                    return true;
                }
            }
        } else if(store instanceof Inn) {
                for (Item item : store.getItems()) {
                    if (item.getName().equals(itemName) && currentHero.getGold() >= item.getCost()) {
                        item.useItem(currentHero);
                        currentHero.setGold(-item.getCost());
                        return true;
                    }
                }
            }
        return false;
    }

}
