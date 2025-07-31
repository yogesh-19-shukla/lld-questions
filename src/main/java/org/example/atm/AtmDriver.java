package org.example.atm;

public class AtmDriver {

        public static void main(String[] args) {
            BankAccount account = new BankAccount("12345", 10000);
            Card card = new Card("1111-2222-3333-4444", 1234, account);
            CashDispenser dispenser = new CashDispenser(50000);

            AtmStateContext atm = new AtmStateContext(dispenser);

            atm.insertCard(card);
            atm.enterPin(1234);
            atm.checkBalance();
            atm.withdraw(2000);
            atm.checkBalance();
            atm.deposit(1000);
            atm.checkBalance();
            atm.ejectCard();
        }
}
