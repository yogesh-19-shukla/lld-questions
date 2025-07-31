package org.example.atm;

public class IdleState implements AtmState {

    private final AtmStateContext context;

    public IdleState(AtmStateContext context) {
        this.context = context;
    }

    public void insertCard(Card card) {
        context.setCard(card);
        System.out.println("Card inserted.");
        context.setState(new CardInsertedState(context));
    }

    public void enterPin(int pin) {
        System.out.println("Insert card first.");
    }

    public void selectOperation(String operation) { }

    public void deposit(double amount) { }

    public void withdraw(double amount) { }

    public void checkBalance() { }

    public void ejectCard() {
        System.out.println("No card to eject.");
    }
}
