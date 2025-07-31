# 🏧 ATM System - Low Level Design in Java

This project implements a **Low-Level Design (LLD)** of an **ATM (Automated Teller Machine)** system using Java, focusing on OOP principles, design patterns (State pattern), and modularity.

---

## 📋 Features

- ✅ Card Insertion
- ✅ PIN Verification
- ✅ Deposit Money
- ✅ Withdraw Money
- ✅ Check Balance
- ✅ Eject Card
- ✅ ATM Cash Limit Simulation
- ✅ State Pattern for Clean Workflow

---

## 🧱 Design Overview

### 👇 Classes

| Class             | Responsibility |
|------------------|----------------|
| `ATMState`        | Interface defining all ATM operations |
| `IdleState`       | ATM idle (no card) |
| `CardInsertedState` | After card insertion, before PIN |
| `AuthenticatedState` | After PIN verification |
| `ATMStateContext` | Holds current state and delegates actions |
| `Card`            | Contains card number, PIN, and associated account |
| `BankAccount`     | Performs balance checks, deposits, withdrawals |
| `CashDispenser`   | Simulates ATM's physical cash availability |
| `ATMDriver`       | Demo and test of ATM flow |

### 🌀 State Pattern

The **State Design Pattern** is used to manage the transitions between states:
- `IdleState` → `CardInsertedState` → `AuthenticatedState` → `IdleState`

---



