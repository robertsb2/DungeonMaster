package Controller;

        import Model.*;
        import Model.rooms.*;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.Initializable;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.canvas.Canvas;
        import javafx.scene.canvas.GraphicsContext;
        import javafx.scene.control.*;
        import javafx.scene.image.Image;
        import javafx.scene.input.KeyCode;
        import javafx.scene.input.KeyEvent;
        import javafx.scene.layout.StackPane;
        import javafx.stage.Stage;

        import javax.rmi.CORBA.Util;
        import java.io.IOException;
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
    private Utilities utilities = new Utilities();


    @FXML
    private Label name;
    @FXML
    private ProgressBar xpBar;
    @FXML
    private Label strength;
    @FXML
    private Label defense;
    @FXML
    private ProgressBar healthBar;
    @FXML
    private Label health;
    @FXML
    private Label gold;
    @FXML
    private Label weapon;
    @FXML
    private Canvas mainCanvas;
    @FXML
    private ListView<String> itemList;
    @FXML
    private Button topLeft;
    @FXML
    private Button topRight;
    @FXML
    private Button bottomLeft;
    @FXML
    private Button bottomRight;
    @FXML
    private Label monsterName;
    @FXML
    private Label monsterStrength;
    @FXML
    private Label monsterDefense;
    @FXML
    private ProgressBar monsterHealthBar;
    @FXML
    private Label monsterHealth;
    @FXML
    private StackPane enemyStats;
    @FXML
    private TextArea combatOutput;
    @FXML
    private StackPane encounters;
    @FXML
    private Button affirmative;
    @FXML
    private Button negative;
    @FXML
    private StackPane shops;
    @FXML
    private ListView inventory;
    @FXML
    private TextArea dialogue;
    @FXML
    private TextArea encounterText;
    @FXML
    private StackPane menu;
    @FXML
    private StackPane gameOverPanel;
    @FXML
    private Label end;


    /**
     * Runs initial setup for the scene
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Utilities.stop();
        menu.setVisible(false);
        gameController = Main.getGameController();
        hero = gameController.getHero();
        pack = hero.getPack();
        gc = mainCanvas.getGraphicsContext2D();
        Image camp = new Image(String.valueOf(getClass().getResource("/Images/camp.jpg")));
        gc.drawImage(camp,0,0,PICTURE_DIM,PICTURE_DIM);
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
        gameOverPanel.setVisible(false);
        gameController.resetDungeon();
        update();

    }

    private void update() {
        Monster monster = gameController.getMonster();
        name.setText(hero.getName() + " Level: " + hero.getLevel());
        strength.setText("Strength: " + hero.getStrength());
        defense.setText("Defense: " + hero.getDefense());
        health.setText(hero.getCurHealth() + "/" + hero.getMaxHealth());
        healthBar.setProgress(hero.getCurHealth()/hero.getMaxHealth());
        if (hero.getXp() >= Utilities.getXpNeeded(hero.getLevel())){
            hero.levelUp();
        }
        weapon.setText(hero.getWeapon().getName() + "\n" + hero.getWeapon().getDescription());
        xpBar.setProgress(hero.getXp() / Utilities.getXpNeeded(hero.getLevel()));
        gold.setText("Gold: " + hero.getGold());
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
            monsterHealth.setText(monster.getCurrHealth() + "/" + monster.getMaxHealth());
            monsterHealthBar.setProgress(monster.getCurrHealth()/monster.getMaxHealth());
        }

        if (hero.getCurHealth() < 10){
            utilities.playSound("/Audio/heartbeat.mp3", false);
        }


        itemList.setItems(items);
        if (!gameController.isAlive()){
            gameOver();
        }
        if (gameController.isWin()){
            gameWin();
        }
        
    }

    private void gameWin() {
        end.setText("You Win!");
        gameOverPanel.setVisible(true);
        gameOverPanel.toFront();
    }

    private void gameOver() {
        gameOverPanel.setVisible(true);
        gameOverPanel.toFront();
        gameController.setDeleteHero(hero.getName());
        gameController.deleteHero();
    }

    private void resetOutput() {
        encounters.setVisible(false);
        enemyStats.setVisible(false);
        affirmative.setVisible(false);
        negative.setVisible(false);
    }






    private void toDungeon(){
        gameController.createDungeon();
        Image tower = new Image(String.valueOf(getClass().getResource("/Images/tower.jpg")));
        gc.drawImage(tower,0,0,PICTURE_DIM,PICTURE_DIM);
        topLeft.setText("Explore");
        topLeft.setOnAction(event -> explore());
        topRight.setText("Use Stairs");
        topRight.setOnAction(event -> stairs());
        topRight.setDisable(true);
        bottomRight.setVisible(true);
        bottomRight.setText("Leave");
        bottomRight.setOnAction(event -> initialize(null,null));



    }

    private void explore() {
        topLeft.setDisable(true);
        resetOutput();
        room = gameController.getRoom();
        currentRoom = room.getRoom();
        gc.drawImage(currentRoom, 0, 0, PICTURE_DIM, PICTURE_DIM);
        if(!gameController.isFloorCleared()) {
            if (gameController.isStairs()) {
                topRight.setDisable(false);
            }
            encounter();
            isStairsDisabled();
        } else  {
            encounters.setVisible(true);
            affirmative.setDisable(true);
            negative.setDisable(true);
            topLeft.setDisable(true);
        }
    }

    private void encounter() {
        if (room instanceof Hallway || room instanceof Cavern || room instanceof BossRoom){
            Monster monster = gameController.getMonster();
            Image image = monster.getImage();
            gc.drawImage(image,monster.getImgX(),monster.getImgY(),monster.getWidth(),monster.getHeight());
            enemyStats.setVisible(true);
            encounters.setVisible(false);
            topLeft.setDisable(true);
            topRight.setDisable(true);
            bottomRight.setDisable(true);
        } else {
            enemyStats.setVisible(false);
            encounters.setVisible(true);
        }
        String text = gameController.encounter();
        if (room instanceof Shrine){
            affirmative.setVisible(true);
            affirmative.setDisable(false);
            affirmative.setOnAction(event -> pray());
        }
        encounterText.setText(text);
        update();
    }

    private void pray() {
        String result = gameController.pray();
        encounterText.setText(result);
        affirmative.setVisible(false);
    }

    private void stairs() {
        gameController.useStairs();
        topRight.setDisable(true);
        topLeft.setDisable(false);
        explore();
    }

    private void isStairsDisabled(){
        if (!gameController.isFighting()){
            gc.drawImage(currentRoom, 0, 0, PICTURE_DIM, PICTURE_DIM);
            if (gameController.isStairs()) {
                topRight.setDisable(false);
            }
            combatOutput.setText("");
            enemyStats.setVisible(false);
            topLeft.setDisable(false);
            bottomRight.setDisable(false);
        }
    }





    private void toTown(){
        Image village = new Image(String.valueOf(getClass().getResource("/Images/village.jpg")));
        gc.drawImage(village,0,0,PICTURE_DIM,PICTURE_DIM);
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
        Image innImage = new Image(String.valueOf(getClass().getResource("/Images/inn.jpg")));
        gc.drawImage(innImage,0,0,PICTURE_DIM,PICTURE_DIM);
        inventory.setItems(inn.getInventory());
        dialogue.setText(inn.getDialogue());
        currentStore = inn;
        shops.setVisible(true);
    }

    private void potions() {
        Image potions = new Image(String.valueOf(getClass().getResource("/Images/potions.jpg")));
        gc.drawImage(potions,0,0,PICTURE_DIM,PICTURE_DIM);
        inventory.setItems(potionShop.getInventory());
        dialogue.setText(potionShop.getDialogue());
        currentStore = potionShop;
        shops.setVisible(true);
    }

    private void blacksmith() {
        Image blacksmithImage = new Image(String.valueOf(getClass().getResource("/Images/blacksmith.jpg")));
        gc.drawImage(blacksmithImage,0,0,PICTURE_DIM,PICTURE_DIM);
        inventory.setItems(blacksmith.getInventory());
        dialogue.setText(blacksmith.getDialogue());
        currentStore = blacksmith;
        shops.setVisible(true);
    }





    @FXML
    private void fight() {
        int damage = gameController.fight();
        update();
        isStairsDisabled();
        if (damage != 0) {
            combatOutput.setText("You dealt " + damage + " damage.");
        } else {
            combatOutput.setText("Your attack missed!");
        }
    }

    @FXML
    private void run() {
        if(gameController.run()){
            isStairsDisabled();
        } else {
            combatOutput.setText("Can't Escape");
        }
        update();
    }

    @FXML
    private void useItem() {
        if (!itemList.getSelectionModel().isEmpty()) {
            String item = itemList.getSelectionModel().getSelectedItem();
            String[] raw = item.split(" x");
            gameController.useItem(raw[0]);
            update();
        }
    }

    @FXML
    private void purchase() {
        if(gameController.purchase(currentStore,inventory.getSelectionModel().getSelectedItem())) {
            dialogue.setText("Thank You!");
            update();
        } else {
            dialogue.setText("You don't have enough money for that.");
        }
    }

    @FXML
    private void showMenu(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.Q)){
            toggleMenu();

        }
    }

    @FXML
    private void toggleMenu() {
        menu.setVisible(!menu.isVisible());
        menu.toFront();
    }

    @FXML
    private void save() {
        gameController.save();
        toggleMenu();
    }

    @FXML
    private void saveQuit() throws IOException {
        gameController.save();
        restart();
    }

    @FXML
    private void restart() throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/title.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
