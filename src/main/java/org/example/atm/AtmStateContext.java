package org.example.atm;

public class AtmStateContext {

    private AtmState currentState;
    private Card currentCard;
    private final CashDispenser dispenser;

    public AtmStateContext(CashDispenser dispenser) {
        this.dispenser = dispenser;
        this.currentState = new IdleState(this);
    }

    public void setState(AtmState state) {
        this.currentState = state;
    }

    public void setCard(Card card) {
        this.currentCard = card;
    }

    public Card getCard() {
        return currentCard;
    }

    public CashDispenser getDispenser() {
        return dispenser;
    }

    // Delegated Methods
    public void insertCard(Card card) { currentState.insertCard(card); }
    public void enterPin(int pin) { currentState.enterPin(pin); }
    public void selectOperation(String operation) { currentState.selectOperation(operation); }
    public void deposit(double amount) { currentState.deposit(amount); }
    public void withdraw(double amount) { currentState.withdraw(amount); }
    public void checkBalance() { currentState.checkBalance(); }
    public void ejectCard() { currentState.ejectCard(); }
}
