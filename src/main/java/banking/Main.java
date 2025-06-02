/**
 * Program Description: Simple Banking App 
 * Author: Nicholas Power SD12
 * Date: June 2, 2025
 */

package banking;

import java.util.Scanner;

/**
 * CLI for Banking App with login and transaction history.
 */
public class Main {
    public static void main(String[] args) {
        BankService service = new BankService();
        Scanner scanner = new Scanner(System.in);
        Account currentUser = null;

        while (true) {
            if (currentUser == null) {
                System.out.println("\nSimple Banking App");
                System.out.println("1. Create Account");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Choice: ");

                String input = scanner.nextLine();
                if (input.equals("1")) {
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Choose a password: ");
                    String password = scanner.nextLine();
                    Account acc = service.createAccount(name, password);
                    System.out.println("Account created! Your account number is: " + acc.getAccountNumber());
                } else if (input.equals("2")) {
                    System.out.print("Enter account number: ");
                    String accNum = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    try {
                        currentUser = service.login(accNum, password);
                        System.out.println("Welcome, " + currentUser.getHolder() + "!");
                    } catch (Exception e) {
                        System.out.println("Login failed: " + e.getMessage());
                    }
                } else if (input.equals("3")) {
                    System.out.println("Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid choice.");
                }
            } else {
                System.out.println("\nAccount Menu (" + currentUser.getAccountNumber() + ")");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Transfer");
                System.out.println("4. Check Balance");
                System.out.println("5. View Transaction History");
                System.out.println("6. Logout");
                System.out.print("Choice: ");

                String choice = scanner.nextLine();

                try {
                    switch (choice) {
                        case "1":
                            System.out.print("Amount: ");
                            double deposit = Double.parseDouble(scanner.nextLine());
                            service.deposit(currentUser, deposit);
                            System.out.println("Deposited $" + deposit);
                            break;

                        case "2":
                            System.out.print("Amount: ");
                            double withdraw = Double.parseDouble(scanner.nextLine());
                            service.withdraw(currentUser, withdraw);
                            System.out.println("Withdrew $" + withdraw);
                            break;

                        case "3":
                            System.out.print("Recipient account number: ");
                            String toAccNum = scanner.nextLine();
                            System.out.print("Amount to transfer: ");
                            double amount = Double.parseDouble(scanner.nextLine());
                            Account toAcc = service.getByNumber(toAccNum);
                            service.transfer(currentUser, toAcc, amount);
                            System.out.println("Transferred $" + amount + " to " + toAcc.getHolder());
                            break;

                        case "4":
                            System.out.println("Balance: $" + service.getBalance(currentUser));
                            break;

                        case "5":
                            System.out.println("Transaction History:");
                            for (String entry : service.getTransactionHistory(currentUser)) {
                                System.out.println("- " + entry);
                            }
                            break;

                        case "6":
                            currentUser = null;
                            System.out.println("Logged out.");
                            break;

                        default:
                            System.out.println("Invalid choice.");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }

        scanner.close();
    }
}
