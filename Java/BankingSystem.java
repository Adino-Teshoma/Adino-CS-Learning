
import java.util.Scanner;

public class BankingSystem {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String name = "";
        int accountNumber = 0;
        double balance = 0;

        boolean accountCreated = false;

        int choice;

        System.out.println("WELCOME TO BANKING SYSTEM");

        do {

            System.out.println("\n===== BANKING SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Display Information");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            switch (choice) {

                case 1:

                    input.nextLine();

                    System.out.print("Enter your name: ");
                    name = input.nextLine();

                    System.out.print("Enter account number: ");
                    accountNumber = input.nextInt();

                    System.out.print("Enter initial deposit: ");
                    balance = input.nextDouble();

                    if (balance >= 0) {
                        accountCreated = true;
                        System.out.println("Account created successfully!");
                    } else {
                        balance = 0;
                        System.out.println("Invalid initial deposit!");
                    }

                    break;

                case 2:

                    if (accountCreated) {

                        System.out.print("Enter deposit amount: ");
                        double deposit = input.nextDouble();

                        if (deposit > 0) {
                            balance = balance + deposit;
                            System.out.println("Deposit successful!");
                        } else {
                            System.out.println("Invalid amount!");
                        }

                    } else {
                        System.out.println("Create an account first!");
                    }

                    break;

                case 3:

                    if (accountCreated) {

                        System.out.print("Enter withdrawal amount: ");
                        double withdraw = input.nextDouble();

                        if (withdraw > 0 && withdraw <= balance) {
                            balance = balance - withdraw;
                            System.out.println("Withdrawal successful!");
                        } else {
                            System.out.println("Invalid amount or insufficient balance!");
                        }

                    } else {
                        System.out.println("Create an account first!");
                    }

                    break;

                case 4:

                    if (accountCreated) {
                        System.out.println("Account Balance: " + balance);
                    } else {
                        System.out.println("Create an account first!");
                    }

                    break;

                case 5:

                    if (accountCreated) {
                        System.out.println("Account Name: " + name);
                        System.out.println("Account Number: " + accountNumber);
                        System.out.println("Account Balance: " + balance);
                    } else {
                        System.out.println("Create an account first!");
                    }

                    break;

                case 6:

                    System.out.println("Thank you for using our bank!");
                    break;

                default:

                    System.out.println("Invalid choice! Try again.");

            }

        } while (choice != 6);

        input.close();
    }
}