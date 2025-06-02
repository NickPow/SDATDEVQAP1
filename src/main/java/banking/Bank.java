package banking;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Handles account creation, lookup, and transfers.
 */
public class Bank {
    private final Map<String, Account> accounts = new HashMap<>();
    private final Random random = new Random();

    public Account createAccount(String name, String password) {
        String accNum;
        do {
            accNum = String.valueOf(100 + random.nextInt(900));
        } while (accounts.containsKey(accNum));

        Account acc = new Account(accNum, name, password);
        accounts.put(accNum, acc);
        return acc;
    }

    public Account login(String accNum, String password) {
        Account acc = accounts.get(accNum);
        if (acc == null || !acc.checkPassword(password)) {
            throw new IllegalArgumentException("Invalid account number or password.");
        }
        return acc;
    }

    public void transfer(Account from, Account to, double amount) {
        if (from == to) throw new IllegalArgumentException("Cannot transfer to the same account.");
        from.withdraw(amount);
        to.deposit(amount);
    }

    public Account getAccountByNumber(String accNum) {
        Account acc = accounts.get(accNum);
        if (acc == null) throw new IllegalArgumentException("Account not found.");
        return acc;
    }
}
