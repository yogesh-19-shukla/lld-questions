package org.example.vendingMachine;

public class HasMoneyState implements State {
    @Override
    public void insertCoin(VendingMachine vm, Coin coin) {
        vm.addBalance(coin.getValue());
        System.out.println("Coin inserted: ₹" + coin.getValue());
    }

    @Override
    public void selectItem(VendingMachine vm, ItemType type) {
        if (!vm.getInventory().hasItem(type)) {
            System.out.println("Out of stock.");
            return;
        }

        Item item = vm.getItemCatalog().get(type);
        if (vm.getBalance() < item.getPrice()) {
            System.out.println("Insufficient balance, Price is ₹" + item.getPrice() + " and available is ₹" + vm.getBalance());
        } else {
            vm.setSelectedItem(item);
            vm.setState(new DispenseState());
            vm.dispense();
        }

    }

    @Override
    public void dispense(VendingMachine vm) {
        System.out.println("Select an item first.");
    }

    @Override
    public void cancel(VendingMachine vm) {
        System.out.println("Transaction cancelled. Returning ₹" + vm.getBalance());
        vm.reset();
        vm.setState(new IdleState());

    }
}
