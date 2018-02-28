package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class BaseController implements Initializable {

    private GameController gameController;
    private Hero hero;
    private Pack pack;
    private GraphicsContext gc;
    private final int PICTURE_DIM =  450;
    private final ObservableList<String> items = FXCollections.observableArrayList();
    private Room room;
    private Image currentRoom;
    private Store currentStore;
    private Blacksmith blacksmith;
    private Inn inn;
    private PotionShop potionShop;



    @FXML public Label name;
    @FXML public Label strength;
    @FXML public Label defense;
    @FXML public ProgressBar healthBar;
    @FXML public Label health;
    @FXML public Label gold;
    @FXML public Label pieces;
    @FXML public Canvas mainCanvas;
    @FXML public ListView<String> itemList;
    @FXML public Button topLeft;
    @FXML public Button topRight;
    @FXML public Button bottomLeft;
    @FXML public Button bottomRight;
    @FXML public Label monsterName;
    @FXML public Label monsterStrength;
    @FXML public Label monsterDefense;
    @FXML public ProgressBar monsterHealthBar;
    @FXML public Label monsterHealth;
    @FXML public StackPane enemyStats;
    @FXML public StackPane encounters;
    @FXML public Button affirmative;
    @FXML public Button negative;
    @FXML public StackPane shops;
    @FXML public ListView inventory;
    @FXML public TextArea dialogue;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameController = Main.getGameController();
        hero = gameController.getHero();
        pack = hero.getPack();
        gc = mainCanvas.getGraphicsContext2D();
        try {
            final Image camp = new Image(new FileInputStream("src/main/resources/Images/camp.jpg"));
            gc.drawImage(camp,0,0,PICTURE_DIM,PICTURE_DIM);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        topLeft.setText("To Town");
        topLeft.setDisable(false);
        topLeft.setOnAction(event -> toTown());
        topRight.setText("To Dungeon");
        topRight.setDisable(false);
        topRight.setOnAction(event -> toDungeon());
        bottomLeft.setVisible(false);
        bottomRight.setVisible(false);
        enemyStats.setVisible(false);
        encounters.setVisible(false);
        shops.setVisible(false);
        affirmative.setVisible(false);
        negative.setVisible(false);

        gameController.resetDungeon();
        update();
    }

    private void update() {
        Monster monster = gameController.getMonster();
        name.setText(hero.getName());
        strength.setText("Strength: " + hero.getStrength());
        defense.setText("Defense: " + hero.getDefense());
        health.setText(hero.getCurHealth() + "/" + hero.getMaxHealth());
        healthBar.setProgress(hero.getCurHealth()/hero.getMaxHealth());
        gold.setText("Gold: " + hero.getGold());
        pieces.setText("Talisman Pieces: " + hero.getPieces());
        ArrayList<String> packNames = new ArrayList<>();
        for (Item item : pack) {
            packNames.add(item.getName());
        }
        items.clear();
        for (Item item : pack){
            if (!items.contains(item.getName() + " x" + Collections.frequency(packNames,item.getName()))){
                items.add(item.getName() + " x" + Collections.frequency(packNames,item.getName()));
            }
        }

        if (monster != null){
            monsterName.setText(monster.getType());
            monsterStrength.setText("Strength: " + monster.getStrength());
            monsterDefense.setText("Defense: " + monster.getDefense());
            monsterHealth.setText(monster.getCurrHealth() + "/" + monster.getDefense());
            monsterHealthBar.setProgress(monster.getCurrHealth()/monster.getMaxHealth());
        }


        itemList.setItems(items);
}

    public void useItem() {
        if (!itemList.getSelectionModel().isEmpty()) {
            String item = itemList.getSelectionModel().getSelectedItem();
            String[] raw = item.split(" x");
            gameController.useItem(raw[0]);
            update();
        }
    }

    private void toDungeon(){
        gameController.createDungeon();
        try {
            final Image camp = new Image(new FileInputStream("src/main/resources/Images/tower.jpg"));
            gc.drawImage(camp,0,0,PICTURE_DIM,PICTURE_DIM);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        topLeft.setText("Explore");
        topLeft.setOnAction(event -> explore());
        topRight.setText("Use Stairs");
        topRight.setOnAction(event -> stairs());
        topRight.setDisable(true);
        bottomRight.setVisible(true);
        bottomRight.setText("Leave");
        bottomRight.setOnAction(event -> initialize(null,null));


    }

    private void toTown(){
        try {
            final Image camp = new Image(new FileInputStream("src/main/resources/Images/village.jpg"));
            gc.drawImage(camp,0,0,PICTURE_DIM,PICTURE_DIM);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Store[] shops = gameController.getShops();
        blacksmith = (Blacksmith) shops[0];
        potionShop = (PotionShop) shops[1];
        inn = (Inn) shops[2];
        topLeft.setText("Blacksmith");
        topLeft.setOnAction(event -> blacksmith());
        topRight.setText("Potion Shop");
        topRight.setOnAction(event -> potions());
        bottomLeft.setVisible(true);
        bottomLeft.setText("Inn");
        bottomLeft.setOnAction(event -> inn());
        bottomRight.setVisible(true);
        bottomRight.setText("Leave");
        bottomRight.setOnAction(event -> initialize(null,null));

    }

    private void inn() {
        try {
            final Image camp = new Image(new FileInputStream("src/main/resources/Images/inn.jpg"));
            gc.drawImage(camp,0,0,PICTURE_DIM,PICTURE_DIM);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        inventory.setItems(inn.getInventory());
        dialogue.setText(inn.getDialogue());
        currentStore = inn;
        shops.setVisible(true);
    }

    private void potions() {
        try {
            final Image camp = new Image(new FileInputStream("src/main/resources/Images/potions.jpg"));
            gc.drawImage(camp,0,0,PICTURE_DIM,PICTURE_DIM);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        inventory.setItems(potionShop.getInventory());
        dialogue.setText(potionShop.getDialogue());
        currentStore = potionShop;
        shops.setVisible(true);
    }

    private void blacksmith() {
        try {
            final Image camp = new Image(new FileInputStream("src/main/resources/Images/blacksmith.jpg"));
            gc.drawImage(camp,0,0,PICTURE_DIM,PICTURE_DIM);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        inventory.setItems(blacksmith.getInventory());
        dialogue.setText(blacksmith.getDialogue());
        currentStore = blacksmith;
        shops.setVisible(true);
    }

    private void explore() {
        hero.setCurHealth(30);
        topLeft.setDisable(true);
        if(!gameController.isFloorCleared()) {

            room = gameController.getRoom();
            currentRoom = room.getRoom();
            gc.drawImage(currentRoom, 0, 0, PICTURE_DIM, PICTURE_DIM);
            if (gameController.isStairs()) {
                topRight.setDisable(false);
            }
            encounter();
            isFighting();
        } else  {
            encounters.setVisible(true);
            affirmative.setDisable(true);
            negative.setDisable(true);
            topLeft.setDisable(true);
        }
    }

    private void encounter() {
        if (room instanceof Hallway || room instanceof Cavern){
            Monster monster = gameController.getMonster();
            Image image = monster.getImage();
            gc.drawImage(image,monster.getImgX(),monster.getImgY(),monster.getWidth(),monster.getHeight());
            update();
            System.out.println(image.getWidth());
            System.out.println(image.getHeight());
            enemyStats.setVisible(true);
            encounters.setVisible(false);
            topLeft.setDisable(true);
            bottomRight.setDisable(true);
        } else {
            enemyStats.setVisible(false);
            encounters.setVisible(true);
        }
        System.out.println(room.getClass().getSimpleName());
        gameController.encounter();
    }

    private void stairs() {
        gameController.useStairs();
        topRight.setDisable(true);
    }

    private void isFighting(){
        if (!gameController.isFighting()){
            gc.drawImage(currentRoom, 0, 0, PICTURE_DIM, PICTURE_DIM);
            if (gameController.isStairs()) {
                topRight.setDisable(false);
            }
            enemyStats.setVisible(false);
            topLeft.setDisable(false);
            bottomRight.setDisable(false);
        }
    }

    public void fight() {
        gameController.fight();
        update();
        isFighting();
    }

    public void run() {
        if(gameController.run()){
            isFighting();
        } else {
            System.out.println("failed");
        }
    }

    public void purchase() {
        if(gameController.purchase(currentStore,inventory.getSelectionModel().getSelectedItem())) {
            update();
        } else {
            dialogue.setText("You don't have enough money for that.");
        }
    }
}
