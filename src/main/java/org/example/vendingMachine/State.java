package org.example.vendingMachine;

public interface State {
    void insertCoin(VendingMachine vm, Coin coin);
    void selectItem(VendingMachine vm, ItemType itemType);
    void dispense(VendingMachine vm);
    void cancel(VendingMachine vm);
}