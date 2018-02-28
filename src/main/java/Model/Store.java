package Model;


import javafx.collections.ObservableList;

import java.util.ArrayList;

public interface Store {
    void setInventory(Item[] items);

    void setDialogue(String dialogue);

    ObservableList getInventory();

    String getDialogue();

    void setItemList(Item[] items);

    ArrayList<Item> getItems();
}
