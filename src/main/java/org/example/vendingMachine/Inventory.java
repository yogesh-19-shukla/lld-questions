package org.example.vendingMachine;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private final Map<ItemType, Integer> items = new HashMap<>();

    public void addItem(ItemType type, int count) {
        items.put(type, items.getOrDefault(type, 0) + count);
    }

    public boolean hasItem(ItemType type) {
        return items.getOrDefault(type, 0) > 0;
    }

    public void reduceItem(ItemType type) {
        items.put(type, items.get(type) - 1);
    }

    public void displayInventory() {
        for (var entry : items.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " left");
        }
    }
}
