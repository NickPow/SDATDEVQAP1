# Simple Banking Application

This is a Java command-line banking application developed using Maven. It allows users to create accounts, log in using an account number and password, and perform basic banking operations such as deposits, withdrawals, transfers, and viewing transaction history.

---

## Features

- Create a secure account with a unique 3-digit account number and password
- Log in using account number and password
- Deposit and withdraw funds
- Transfer funds between accounts
- View current balance
- View transaction history
- Unit tests written with JUnit 5
- GitHub Actions integration to run tests automatically on pull requests

---

## Technologies Used

- Java
- Maven
- JUnit 5
- GitHub Actions

---

## How It Works

1. User is prompted to create an account or log in
2. After logging in, user can:
   - Deposit or withdraw money
   - Transfer funds to another account
   - Check balance
   - View full transaction history
   - Log out

All accounts are stored in memory during runtime.


---

## How to Run

1. Clone the repository
2. Compile and run the application using Maven:

mvn compile

mvn exec:java -Dexec.mainClass=banking.Main