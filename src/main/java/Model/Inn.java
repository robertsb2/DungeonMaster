package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;

public class Inn implements Store {
    private String dialogue;
    private ArrayList<Item> itemList = new ArrayList<>();
    private ObservableList<String> listItems= FXCollections.observableArrayList();

    public Inn(){
    }

    public Inn(String dialogue, Item... items){
        this.setDialogue(dialogue);
        this.setItemList(items);
        this.setInventory(items);
    }

    @Override
    public void setInventory(Item[] items) {
        for (Item item : items){
            listItems.add(item.getName());
        }
    }

    @Override
    public void setDialogue(String dialogue) {
        this.dialogue = dialogue;
    }

    @Override
    public String getDialogue() {
        return dialogue;
    }

    @Override
    public void setItemList(Item[] items) {
        itemList.addAll(Arrays.asList(items));
    }

    @Override
    public ArrayList<Item> getItems() {
        return itemList;
    }

    @Override
    public ObservableList getInventory() {
        return listItems;
    }
}
