package org.example.vendingMachine;

public class IdleState implements State {
    @Override
    public void insertCoin(VendingMachine vm, Coin coin) {
        vm.addBalance(coin.getValue());
        vm.setState(new HasMoneyState());
        System.out.println("Coin inserted: ₹" + coin.getValue());
    }

    @Override
    public void selectItem(VendingMachine vm, ItemType itemType) {
        System.out.println("Insert coin first");
    }

    @Override
    public void dispense(VendingMachine vm) {
        System.out.println("Insert coin and select item first");
    }

    @Override
    public void cancel(VendingMachine vm) {
        System.out.println("Nothing to cancel");
    }
}
