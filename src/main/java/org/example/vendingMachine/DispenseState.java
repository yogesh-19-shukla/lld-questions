package org.example.vendingMachine;

public class DispenseState implements State {
    @Override
    public void insertCoin(VendingMachine vm, Coin coin) {
        System.out.println("Please wait, dispensing in progress.");

    }

    @Override
    public void selectItem(VendingMachine vm, ItemType itemType) {
        System.out.println("Please wait, dispensing in progress.");

    }

    @Override
    public void dispense(VendingMachine vm) {
        Item item = vm.getSelectedItem();
        vm.getInventory().reduceItem(item.getType());
        vm.deductBalance(item.getPrice());
        System.out.println("Dispensed: " + item.getType());
        if (vm.getBalance() > 0) {
            System.out.println("Returning change: ₹" + vm.getBalance());
        }
        vm.reset();
        vm.setState(new IdleState());
    }

    @Override
    public void cancel(VendingMachine vm) {
        System.out.println("Cannot cancel now, dispensing.");

    }
}
