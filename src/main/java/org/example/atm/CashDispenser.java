package org.example.atm;

public class CashDispenser {

    private double totalCash;

    public CashDispenser(double totalCash) {
        this.totalCash = totalCash;
    }

    public double getTotalCash() {
        return totalCash;
    }

    public void loadCash(double amount) {
        this.totalCash += amount;
    }

    public boolean dispenseCash(double amount) {
        if (amount > totalCash)  return false;
        this.totalCash -= amount;
        return true;
    }
}
