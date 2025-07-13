package org.example.vendingMachine;

public class Main {

    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine();

        vm.refill(ItemType.COKE, 2);
        vm.refill(ItemType.PEPSI, 1);
        vm.showInventory();

        vm.insertCoin(Coin.TEN);
        vm.insertCoin(Coin.TEN);
        vm.insertCoin(Coin.FIVE);
        vm.selectItem(ItemType.COKE); // Should dispense

        vm.insertCoin(Coin.TEN);
        vm.cancel(); // Cancel and get ₹10 back

        vm.insertCoin(Coin.TEN);
        vm.insertCoin(Coin.TEN);
        vm.selectItem(ItemType.PEPSI); // Should not dispense
    }
}
