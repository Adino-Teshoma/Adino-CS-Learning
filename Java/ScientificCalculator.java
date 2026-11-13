
import java.util.Scanner;

public class ScientificCalculator {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\n===== SCIENTIFIC CALCULATOR =====");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Square Root");
            System.out.println("6. Power");
            System.out.println("7. Sine");
            System.out.println("8. Cosine");
            System.out.println("9. Tangent");
            System.out.println("10. Logarithm");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            double num1, num2, result;

            switch (choice) {

                case 1:
                    System.out.print("Enter two numbers: ");
                    num1 = input.nextDouble();
                    num2 = input.nextDouble();
                    result = num1 + num2;
                    System.out.println("Result = " + result);
                    break;

                case 2:
                    System.out.print("Enter two numbers: ");
                    num1 = input.nextDouble();
                    num2 = input.nextDouble();
                    result = num1 - num2;
                    System.out.println("Result = " + result);
                    break;

                case 3:
                    System.out.print("Enter two numbers: ");
                    num1 = input.nextDouble();
                    num2 = input.nextDouble();
                    result = num1 * num2;
                    System.out.println("Result = " + result);
                    break;

                case 4:
                    System.out.print("Enter two numbers: ");
                    num1 = input.nextDouble();
                    num2 = input.nextDouble();

                    if (num2 != 0) {
                        result = num1 / num2;
                        System.out.println("Result = " + result);
                    } else {
                        System.out.println("Cannot divide by zero!");
                    }
                    break;

                case 5:
                    System.out.print("Enter a number: ");
                    num1 = input.nextDouble();

                    if (num1 >= 0) {
                        result = Math.sqrt(num1);
                        System.out.println("Result = " + result);
                    } else {
                        System.out.println("Invalid input!");
                    }
                    break;

                case 6:
                    System.out.print("Enter base and power: ");
                    num1 = input.nextDouble();
                    num2 = input.nextDouble();
                    result = Math.pow(num1, num2);
                    System.out.println("Result = " + result);
                    break;

                case 7:
                    System.out.print("Enter angle in degrees: ");
                    num1 = input.nextDouble();
                    result = Math.sin(Math.toRadians(num1));
                    System.out.println("Result = " + result);
                    break;

                case 8:
                    System.out.print("Enter angle in degrees: ");
                    num1 = input.nextDouble();
                    result = Math.cos(Math.toRadians(num1));
                    System.out.println("Result = " + result);
                    break;

                case 9:
                    System.out.print("Enter angle in degrees: ");
                    num1 = input.nextDouble();
                    result = Math.tan(Math.toRadians(num1));
                    System.out.println("Result = " + result);
                    break;

                case 10:
                    System.out.print("Enter a positive number: ");
                    num1 = input.nextDouble();

                    if (num1 > 0) {
                        result = Math.log10(num1);
                        System.out.println("Result = " + result);
                    } else {
                        System.out.println("Invalid input!");
                    }
                    break;

                case 0:
                    System.out.println("Calculator closed.");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);

        input.close();
    }
}
