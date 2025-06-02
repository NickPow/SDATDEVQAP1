package banking;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Represents a bank account with secure access and transaction tracking.
 */
public class Account {
    private static final Logger logger = Logger.getLogger(Account.class.getName());

    private final String accountNumber;
    private final String password;
    private final String holder;
    private double balance;
    private final List<String> history = new ArrayList<>();

    public Account(String accountNumber, String holder, String password) {
        this.accountNumber = accountNumber;
        this.password = password;
        this.holder = formatName(holder);
        this.balance = 0.0;
        log("Account created");
        logger.info("Account created for: " + this.holder + " (Acc #" + accountNumber + ")");
    }

    public boolean checkPassword(String input) {
        return this.password.equals(input);
    }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive.");
        balance += amount;
        log("Deposited $" + amount);
        logger.info("Deposited $" + amount + " to account " + accountNumber);
    }

    public void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive.");
        if (amount > balance) throw new IllegalArgumentException("Insufficient funds.");
        balance -= amount;
        log("Withdrew $" + amount);
        logger.info("Withdrew $" + amount + " from account " + accountNumber);
    }

    public void log(String message) {
        history.add(message);
    }

    public List<String> getTransactionHistory() {
        return new ArrayList<>(history);
    }

    public double getBalance() {
        return balance;
    }

    public String getHolder() {
        return holder;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    private String formatName(String name) {
        String[] words = name.trim().toLowerCase().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                sb.append(Character.toUpperCase(word.charAt(0)))
                  .append(word.substring(1)).append(" ");
            }
        }
        return sb.toString().trim();
    }
}
