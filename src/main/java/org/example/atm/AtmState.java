package org.example.atm;

public interface AtmState {

    void insertCard(Card card);
    void enterPin(int pin);
    void selectOperation(String operation);
    void deposit(double amount);
    void withdraw(double amount);
    void checkBalance();
    void ejectCard();
}
