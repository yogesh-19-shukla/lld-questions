package org.example.vendingMachine;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {

    private final Map<ItemType, Item> itemCatalog = new HashMap<>();
    private final Inventory inventory = new Inventory();
    private State state;
    private int balance;
    private Item selectedItem;

    public VendingMachine() {
        this.state = new IdleState();
        itemCatalog.put(ItemType.COKE, new Item(ItemType.COKE, 25));
        itemCatalog.put(ItemType.PEPSI, new Item(ItemType.PEPSI, 35));
        itemCatalog.put(ItemType.JUICE, new Item(ItemType.JUICE, 30));
    }

    public void insertCoin(Coin coin) {
        state.insertCoin(this, coin);
    }

    public void selectItem(ItemType type) {
        state.selectItem(this, type);
    }

    public void dispense() {
        state.dispense(this);
    }

    public void cancel() {
        state.cancel(this);
    }

    public void setState(State state) {
        this.state = state;
    }

    public void addBalance(int amount) {
        balance += amount;
    }

    public void deductBalance(int amount) {
        if (amount > balance) System.out.println("Not enough balance");
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }

    public void setSelectedItem(Item item) {
        selectedItem = item;
    }

    public Item getSelectedItem() {
        return selectedItem;
    }

    public Map<ItemType, Item> getItemCatalog() {
        return itemCatalog;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void refill(ItemType type, int count) {
        inventory.addItem(type, count);
    }

    public void showInventory() {
        inventory.displayInventory();
    }

    public void reset() {
        balance = 0;
        selectedItem = null;
    }
}
