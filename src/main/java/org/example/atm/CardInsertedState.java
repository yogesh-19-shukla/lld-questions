package org.example.atm;

public class CardInsertedState implements AtmState {

    private final AtmStateContext context;

    public CardInsertedState(AtmStateContext context) {
        this.context = context;
    }

    public void insertCard(Card card) {
        System.out.println("Card already inserted.");
    }

    public void enterPin(int pin) {
        if (context.getCard().verifyPin(pin)) {
            System.out.println("PIN verified.");
            context.setState(new AuthenticatedState(context));
        } else {
            System.out.println("Incorrect PIN.");
        }
    }

    public void selectOperation(String operation) {
        System.out.println("Enter PIN first.");
    }

    public void deposit(double amount) { }

    public void withdraw(double amount) { }

    public void checkBalance() { }

    public void ejectCard() {
        context.setCard(null);
        context.setState(new IdleState(context));
        System.out.println("Card ejected.");
    }
}
