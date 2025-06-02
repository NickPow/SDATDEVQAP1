package banking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class BankTest {

    @Test
    void testCreateAccountGeneratesAccountNumber() {
        BankService service = new BankService();
        Account acc = service.createAccount("Alice", "pass123");
        assertNotNull(acc.getAccountNumber());
        assertEquals(3, acc.getAccountNumber().length());
    }

    @Test
    void testLoginSuccess() {
        BankService service = new BankService();
        Account acc = service.createAccount("Bob", "mypassword");
        Account loggedIn = service.login(acc.getAccountNumber(), "mypassword");
        assertEquals(acc.getAccountNumber(), loggedIn.getAccountNumber());
    }

    @Test
    void testLoginFailsWithWrongPassword() {
        BankService service = new BankService();
        Account acc = service.createAccount("Charlie", "secret");
        assertThrows(IllegalArgumentException.class, () -> {
            service.login(acc.getAccountNumber(), "wrongpass");
        });
    }

    @Test
    void testLoginFailsWithInvalidAccount() {
        BankService service = new BankService();
        assertThrows(IllegalArgumentException.class, () -> {
            service.login("999", "nopass");
        });
    }

    @Test
    void testDepositWithdrawFlow() {
        BankService service = new BankService();
        Account acc = service.createAccount("Eve", "eve123");
        service.deposit(acc, 100);
        assertEquals(100.0, acc.getBalance());
        service.withdraw(acc, 40);
        assertEquals(60.0, acc.getBalance());
    }

    @Test
    void testTransferToSelfFails() {
        BankService service = new BankService();
        Account acc = service.createAccount("David", "dpass");
        assertThrows(IllegalArgumentException.class, () -> {
            service.transfer(acc, acc, 50);
        });
    }

    @Test
    void testTransferBetweenAccountsAndHistory() {
        BankService service = new BankService();
        Account from = service.createAccount("Frank", "fpass");
        Account to = service.createAccount("Grace", "gpass");

        service.deposit(from, 200);
        service.transfer(from, to, 50);

        assertEquals(150.0, from.getBalance());
        assertEquals(50.0, to.getBalance());

        List<String> fromHistory = service.getTransactionHistory(from);
        List<String> toHistory = service.getTransactionHistory(to);

        assertTrue(fromHistory.contains("Deposited $200.0"));
        assertTrue(fromHistory.stream().anyMatch(s -> s.contains("Transferred $50.0")));
        assertTrue(toHistory.stream().anyMatch(s -> s.contains("Received $50.0")));
    }
}
