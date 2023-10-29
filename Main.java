import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User {
    private String userId;
    private String userPin;
    private double balance;
    private List<Double> transactionHistory;

    public User(String userId, String userPin, double balance) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = balance;
        transactionHistory = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPin() {
        return userPin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Double> getTransactionHistory() {
        return transactionHistory;
    }

    public void addToTransactionHistory(double amount) {
        transactionHistory.add(amount);
    }
}

class ATM {
    private List<User> users;
    private User currentUser;
    private Scanner scanner;

    public ATM() {
        users = new ArrayList<>();
        users.add(new User("user", "123456789", 1000.0));
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("                 Welcome to Aman ATM");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter User PIN: ");
        String userPin = scanner.nextLine();

        currentUser = authenticateUser(userId, userPin);

        if (currentUser != null) {
            showMainMenu();
        } else {
            System.out.println("Invalid credentials. Exiting...");
        }
    }

    private User authenticateUser(String userId, String userPin) {
        for (User user : users) {
            if (user.getUserId().equals(userId) && user.getUserPin().equals(userPin)) {
                return user;
            }
        }
        return null;
    }

    private void showMainMenu() {
        int choice;
        do {
            System.out.println("\n Main Menu:");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    showTransactionHistory();
                    break;
                case 2:
                    performWithdrawal();
                    break;
                case 3:
                    performDeposit();
                    break;
                case 4:
                    performTransfer();
                    break;
                case 5:
                    System.out.println("Thank you!!");
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);
    }

    private void showTransactionHistory() {
        List<Double> transactionHistory = currentUser.getTransactionHistory();
        System.out.println("Transaction History:");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Double amount : transactionHistory) {
                System.out.println("Transaction: " + amount);
            }
        }
    }

    private void performWithdrawal() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount > 0 && amount <= currentUser.getBalance()) {
            currentUser.setBalance(currentUser.getBalance() - amount);
            currentUser.addToTransactionHistory(-amount);
            System.out.println("Withdrawal successful. New balance: " + currentUser.getBalance());
        } else {
            System.out.println("Invalid amount or insufficient balance.");
        }
    }

    private void performDeposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            currentUser.setBalance(currentUser.getBalance() + amount);
            currentUser.addToTransactionHistory(amount);
            System.out.println("Deposit successful. New balance: " + currentUser.getBalance());
        } else {
            System.out.println("Invalid amount.");
        }
    }

    private void performTransfer() {
        System.out.print("Enter recipient's User ID: ");
        String recipientId = scanner.next();
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        User recipient = null;

        for (User user : users) {
            if (user.getUserId().equals(recipientId)) {
                recipient = user;
                break;
            }
        }

        if (recipient != null && amount > 0 && amount <= currentUser.getBalance())
        {
          //  System.out.println("Before Transfer - Current User Balance: " + currentUser.getBalance());
           // System.out.println("Before Transfer - Recipient Balance: " + recipient.getBalance());

            currentUser.setBalance(currentUser.getBalance() - amount);

           // System.out.println("After Transfer - Current User Balance: " + currentUser.getBalance());
          //  System.out.println("After Transfer - Recipient Balance: " + recipient.getBalance());

            currentUser.addToTransactionHistory(-amount);
            recipient.addToTransactionHistory(amount);

            System.out.println("Transfer successful.");
            System.out.println("Your New Balance: " + currentUser.getBalance());

        }
        else
        {
            System.out.println("Invalid recipient or amount, or insufficient balance.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}
