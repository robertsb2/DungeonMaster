package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;

public class Blacksmith implements Store {
    private String dialogue;
    private ArrayList<Item> itemList = new ArrayList<>();
    private ObservableList<String> listItems = FXCollections.observableArrayList();

    /**
     * default constructor
     */
    public Blacksmith(){
    }

    /**
     * overloaded constructor
     * @param dialogue initial dialogue for the store
     * @param items the list of items available for purchase
     */
    public Blacksmith(String dialogue, Item... items) {
        this.setDialogue(dialogue);
        this.setItemList(items);
        this.setInventory(items);
    }

    /**
     * sets inventory as a list of items
     * @param items items to be added.
     */
    @Override
    public void setInventory(Item[] items) {
        for (Item item : items){
            listItems.add(item.getName() + " -" + item.getCost() + "\n" +
                    "     -" + item.getDescription());
        }
    }

    /**
     * sets initial dialogue
     * @param dialogue dialogue
     */
    @Override
    public void setDialogue(String dialogue) {
        this.dialogue = dialogue;
    }

    /**
     *
     * @return inventory
     */
    @Override
    public ObservableList getInventory() {
        return listItems;
    }

    /**
     *
     * @return dialogue
     */
    @Override
    public String getDialogue() {
        return dialogue;
    }

    /**
     * prepares inventory for display handling
     * @param items inventory items
     */
    @Override
    public void setItemList(Item[] items) {
        itemList.addAll(Arrays.asList(items));
    }

    /**
     *
     * @return prepared inventory
     */
    @Override
    public ArrayList<Item> getItems() {
        return itemList;
    }
}
