package org.example.atm;

public class AuthenticatedState implements AtmState {

    private final AtmStateContext context;

    public AuthenticatedState(AtmStateContext context) {
        this.context = context;
    }

    public void insertCard(Card card) {
        System.out.println("Card already inserted.");
    }

    public void enterPin(int pin) {
        System.out.println("Already authenticated.");
    }

    public void selectOperation(String operation) {
        System.out.println("Selected: " + operation);
    }

    public void deposit(double amount) {
        context.getCard().getAccount().deposit(amount);
        System.out.println("Deposited: ₹" + amount);
    }

    public void withdraw(double amount) {
        if (!context.getDispenser().dispenseCash(amount)) {
            System.out.println("ATM has insufficient cash.");
            return;
        }
        if (context.getCard().getAccount().withdraw(amount)) {
            System.out.println("Withdrawn: ₹" + amount);
        } else {
            System.out.println("Insufficient account balance.");
            context.getDispenser().loadCash(amount); // revert
        }
    }

    public void checkBalance() {
        double balance = context.getCard().getAccount().getBalance();
        System.out.println("Balance: ₹" + balance);
    }

    public void ejectCard() {
        context.setCard(null);
        context.setState(new IdleState(context));
        System.out.println("Card ejected.");
    }
}
