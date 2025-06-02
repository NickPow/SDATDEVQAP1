package banking;

import java.util.List;

/**
 * Mediates banking operations and encapsulates business logic.
 */
public class BankService {
    private final Bank bank;

    public BankService() {
        this.bank = new Bank();
    }

    public Account createAccount(String name, String password) {
        return bank.createAccount(name, password);
    }

    public Account login(String accNum, String password) {
        return bank.login(accNum, password);
    }

    public void deposit(Account acc, double amount) {
        acc.deposit(amount);
    }

    public void withdraw(Account acc, double amount) {
        acc.withdraw(amount);
    }

    public double getBalance(Account acc) {
        return acc.getBalance();
    }

    public void transfer(Account from, Account to, double amount) {
        bank.transfer(from, to, amount);
        from.log("Transferred $" + amount + " to account " + to.getAccountNumber());
        to.log("Received $" + amount + " from account " + from.getAccountNumber());
    }

    public Account getByNumber(String accNum) {
        return bank.getAccountByNumber(accNum);
    }

    public List<String> getTransactionHistory(Account acc) {
        return acc.getTransactionHistory();
    }
}
