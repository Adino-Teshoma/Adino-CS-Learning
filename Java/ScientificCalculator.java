import java.util.Scanner;
import java.util.Locale;

public class ScientificCalculator {

    private static final Scanner input =
            new Scanner(System.in).useLocale(Locale.US);

    public static void main(String[] args) {
        int choice;

        do {
            showMenu();
            choice = readInt("Enter your choice: ", 0, 17);

            switch (choice) {
                case 1:
                case 2:
                case 3:
                case 4:
                    arithmetic(choice);
                    break;

                case 5:
                    squareRoot();
                    break;

                case 6:
                    power();
                    break;

                case 7:
                case 8:
                case 9:
                    trigonometry(choice);
                    break;

                case 10:
                    logarithm();
                    break;

                case 11:
                    differentiate();
                    break;

                case 12:
                    integrate();
                    break;

                case 13:
                    matrixMenu();
                    break;

                case 14:
                    linearEquations();
                    break;

                case 15:
                    quadraticEquation();
                    break;

                case 16:
                    evaluatePolynomial();
                    break;

                case 17:
                    exponential();
                    break;

                case 0:
                    System.out.println("Calculator closed.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        input.close();
    }

    private static void showMenu() {
        System.out.println("\n===== SCIENTIFIC CALCULATOR =====");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        System.out.println("5. Square Root");
        System.out.println("6. Power");
        System.out.println("7. Sine (degrees)");
        System.out.println("8. Cosine (degrees)");
        System.out.println("9. Tangent (degrees)");
        System.out.println("10. Logarithm (base 10)");
        System.out.println("11. Differentiate a polynomial");
        System.out.println("12. Integrate a polynomial");
        System.out.println("13. Matrix operations");
        System.out.println("14. Linear equations");
        System.out.println("15. Quadratic equation");
        System.out.println("16. Evaluate a polynomial");
        System.out.println("17. Exponential functions");
        System.out.println("0. Exit");
    }

    // ---------- Input helpers ----------

    private static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);

            if (input.hasNextInt()) {
                int value = input.nextInt();

                if (value >= min && value <= max) {
                    return value;
                }
            } else {
                input.next();
            }

            System.out.println(
                    "Enter a whole number from " + min + " to " + max + "."
            );
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);

            if (input.hasNextDouble()) {
                double value = input.nextDouble();

                if (Double.isFinite(value)) {
                    return value;
                }
            } else {
                input.next();
            }

            System.out.println("Enter a finite number, such as 2 or 3.5.");
        }
    }

    private static void printResult(double value) {
        if (Double.isFinite(value)) {
            System.out.println("Result = " + value);
        } else {
            System.out.println(
                    "No finite real result: check the input or numeric range."
            );
        }
    }

    // ---------- Original operations ----------

    private static void arithmetic(int operation) {
        double a = readDouble("Enter first number: ");
        double b = readDouble("Enter second number: ");

        switch (operation) {
            case 1:
                printResult(a + b);
                break;

            case 2:
                printResult(a - b);
                break;

            case 3:
                printResult(a * b);
                break;

            case 4:
                if (b == 0.0) {
                    System.out.println("Cannot divide by zero.");
                } else {
                    printResult(a / b);
                }
                break;

            default:
                break;
        }
    }

    private static void squareRoot() {
        double number = readDouble("Enter a number: ");

        if (number < 0) {
            System.out.println("This operation requires a nonnegative number.");
        } else {
            printResult(Math.sqrt(number));
        }
    }

    private static void power() {
        double base = readDouble("Enter base: ");
        double exponent = readDouble("Enter exponent: ");
        printResult(Math.pow(base, exponent));
    }

    private static void trigonometry(int operation) {
        double degrees = readDouble("Enter angle in degrees: ");
        double radians = Math.toRadians(degrees);

        switch (operation) {
            case 7:
                printResult(Math.sin(radians));
                break;

            case 8:
                printResult(Math.cos(radians));
                break;

            case 9:
                if (Math.abs(degrees % 180.0) == 90.0) {
                    System.out.println("Tangent is undefined at this angle.");
                } else {
                    printResult(Math.tan(radians));
                }
                break;

            default:
                break;
        }
    }

    private static void logarithm() {
        double number = readDouble("Enter a positive number: ");

        if (number <= 0) {
            System.out.println("Logarithm requires a positive number.");
        } else {
            printResult(Math.log10(number));
        }
    }

    private static void exponential() {
        System.out.println("1. e^x");
        System.out.println("2. a^x");

        int option = readInt("Choose: ", 1, 2);

        if (option == 1) {
            double x = readDouble("Enter x: ");
            printResult(Math.exp(x));
        } else {
            double base = readDouble("Enter positive base a: ");

            if (base <= 0) {
                System.out.println("The exponential base must be positive.");
                return;
            }

            double x = readDouble("Enter x: ");
            printResult(Math.pow(base, x));
        }
    }

    // ---------- Polynomial helpers ----------
    // Index i stores the coefficient of x^i.

    private static double[] readPolynomial() {
        int degree = readInt("Enter polynomial degree (0-100): ", 0, 100);
        double[] coefficients = new double[degree + 1];

        System.out.println(
                "Enter coefficients from highest power to constant."
        );
        System.out.println("Enter 0 for any missing power.");

        for (int i = degree; i >= 0; i--) {
            coefficients[i] = readDouble(
                    "Coefficient of x^" + i + ": "
            );
        }

        return coefficients;
    }

    private static double polynomialValue(double[] coefficients, double x) {
        double value = 0;

        for (int i = coefficients.length - 1; i >= 0; i--) {
            value = value * x + coefficients[i];
        }

        return value;
    }

    private static String polynomialText(double[] coefficients) {
        StringBuilder text = new StringBuilder();

        for (int i = coefficients.length - 1; i >= 0; i--) {
            double coefficient = coefficients[i];

            if (coefficient == 0.0) {
                continue;
            }

            if (text.length() > 0) {
                text.append(coefficient < 0 ? " - " : " + ");
            } else if (coefficient < 0) {
                text.append("-");
            }

            double magnitude = Math.abs(coefficient);

            if (i == 0 || magnitude != 1.0) {
                text.append(Double.toString(magnitude));

                if (i > 0) {
                    text.append("*");
                }
            }

            if (i > 0) {
                text.append("x");

                if (i > 1) {
                    text.append("^").append(i);
                }
            }
        }

        return text.length() == 0 ? "0" : text.toString();
    }

    // ---------- Differentiation ----------

    private static void differentiate() {
        System.out.println("\n--- POLYNOMIAL DIFFERENTIATION ---");

        double[] coefficients = readPolynomial();
        double[] derivative =
                new double[Math.max(1, coefficients.length - 1)];

        for (int i = 1; i < coefficients.length; i++) {
            derivative[i - 1] = i * coefficients[i];
        }

        System.out.println("f(x)  = " + polynomialText(coefficients));
        System.out.println("f'(x) = " + polynomialText(derivative));

        int evaluate = readInt(
                "Evaluate the derivative at a point? (1=Yes, 0=No): ",
                0, 1
        );

        if (evaluate == 1) {
            double x = readDouble("Enter x: ");
            printResult(polynomialValue(derivative, x));
        }
    }

    // ---------- Integration ----------

    private static void integrate() {
        System.out.println("\n--- POLYNOMIAL INTEGRATION ---");

        double[] coefficients = readPolynomial();
        double[] integral = new double[coefficients.length + 1];

        for (int i = 0; i < coefficients.length; i++) {
            integral[i + 1] = coefficients[i] / (i + 1);
        }

        System.out.println("f(x) = " + polynomialText(coefficients));
        System.out.println(
                "Indefinite integral = " + polynomialText(integral) + " + C"
        );

        int definite = readInt(
                "Calculate a definite integral? (1=Yes, 0=No): ",
                0, 1
        );

        if (definite == 1) {
            double lower = readDouble("Enter lower limit: ");
            double upper = readDouble("Enter upper limit: ");

            double answer = polynomialValue(integral, upper)
                    - polynomialValue(integral, lower);

            printResult(answer);
        }
    }

    private static void evaluatePolynomial() {
        System.out.println("\n--- POLYNOMIAL EVALUATION ---");

        double[] coefficients = readPolynomial();
        double x = readDouble("Enter x: ");

        System.out.println("f(x) = " + polynomialText(coefficients));
        printResult(polynomialValue(coefficients, x));
    }

    // ---------- Matrix operations ----------

    private static double[][] readMatrix(String name, int rows, int columns) {
        double[][] matrix = new double[rows][columns];

        System.out.println("Enter elements of matrix " + name + ":");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = readDouble(
                        name + "[" + (i + 1) + "][" + (j + 1) + "]: "
                );
            }
        }

        return matrix;
    }

    private static void printMatrix(double[][] matrix) {
        System.out.println("Result:");

        for (double[] row : matrix) {
            for (double value : row) {
                System.out.printf(Locale.US, "%14.6g ", value);
            }
            System.out.println();
        }
    }

    private static void matrixMenu() {
        System.out.println("\n--- MATRIX OPERATIONS ---");
        System.out.println("1. Addition: A + B");
        System.out.println("2. Subtraction: A - B");
        System.out.println("3. Multiplication: A * B");
        System.out.println("4. Transpose of A");

        int operation = readInt("Choose: ", 1, 4);
        int rows = readInt("Rows in A (1-10): ", 1, 10);
        int columns = readInt("Columns in A (1-10): ", 1, 10);

        double[][] a = readMatrix("A", rows, columns);

        if (operation == 4) {
            double[][] result = new double[columns][rows];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    result[j][i] = a[i][j];
                }
            }

            printMatrix(result);
            return;
        }

        if (operation == 1 || operation == 2) {
            System.out.println("B must have the same dimensions as A.");
            double[][] b = readMatrix("B", rows, columns);
            double[][] result = new double[rows][columns];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    result[i][j] = operation == 1
                            ? a[i][j] + b[i][j]
                            : a[i][j] - b[i][j];
                }
            }

            printMatrix(result);
            return;
        }

        System.out.println("Rows in B must equal columns in A: " + columns);
        int bColumns = readInt("Columns in B (1-10): ", 1, 10);

        double[][] b = readMatrix("B", columns, bColumns);
        double[][] result = new double[rows][bColumns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < bColumns; j++) {
                for (int k = 0; k < columns; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        printMatrix(result);
    }

    // ---------- Linear equations ----------

    private static void linearEquations() {
        System.out.println("\n--- LINEAR EQUATIONS ---");
        System.out.println("1. One equation: a*x + b = 0");
        System.out.println("2. Two simultaneous equations");

        int option = readInt("Choose: ", 1, 2);

        if (option == 1) {
            double a = readDouble("Enter a: ");
            double b = readDouble("Enter b: ");
            solveLinear(a, b);
            return;
        }

        System.out.println("Equation 1: a*x + b*y = c");
        System.out.println("Equation 2: d*x + e*y = f");

        double a = readDouble("Enter a: ");
        double b = readDouble("Enter b: ");
        double c = readDouble("Enter c: ");
        double d = readDouble("Enter d: ");
        double e = readDouble("Enter e: ");
        double f = readDouble("Enter f: ");

        double determinant = a * e - b * d;

        if (determinant != 0.0) {
            double x = (c * e - b * f) / determinant;
            double y = (a * f - c * d) / determinant;

            System.out.println("x = " + x);
            System.out.println("y = " + y);
            return;
        }

        boolean impossibleZeroRow =
                (a == 0.0 && b == 0.0 && c != 0.0)
                || (d == 0.0 && e == 0.0 && f != 0.0);

        if (impossibleZeroRow
                || a * f - c * d != 0.0
                || b * f - c * e != 0.0) {
            System.out.println("No solution.");
        } else {
            System.out.println("Infinitely many solutions.");
        }
    }

    private static void solveLinear(double a, double b) {
        if (a != 0.0) {
            System.out.println("x = " + (-b / a));
        } else if (b == 0.0) {
            System.out.println("Infinitely many solutions.");
        } else {
            System.out.println("No solution.");
        }
    }

    // ---------- Quadratic equations ----------

    private static void quadraticEquation() {
        System.out.println("\n--- QUADRATIC EQUATION ---");
        System.out.println("Form: a*x^2 + b*x + c = 0");

        double a = readDouble("Enter a: ");
        double b = readDouble("Enter b: ");
        double c = readDouble("Enter c: ");

        if (a == 0.0) {
            System.out.println("a is zero, so this is a linear equation.");
            solveLinear(b, c);
            return;
        }

        // Scale coefficients to reduce overflow in the discriminant.
        double scale = Math.max(
                Math.abs(a),
                Math.max(Math.abs(b), Math.abs(c))
        );

        a /= scale;
        b /= scale;
        c /= scale;

        if (a == 0.0) {
            System.out.println(
                    "Coefficients differ too much in size for this solver."
            );
            return;
        }

        double discriminant = b * b - 4.0 * a * c;

        if (discriminant > 0.0) {
            double root = Math.sqrt(discriminant);

            // This form reduces cancellation for unequal real roots.
            double q = -0.5 * (b + Math.copySign(root, b));

            System.out.println("x1 = " + (q / a));
            System.out.println("x2 = " + (c / q));

        } else if (discriminant == 0.0) {
            System.out.println("Repeated root: x = " + (-b / (2.0 * a)));

        } else {
            double real = -b / (2.0 * a);
            double imaginary =
                    Math.sqrt(-discriminant) / (2.0 * Math.abs(a));

            System.out.println("x1 = " + real + " + " + imaginary + "i");
            System.out.println("x2 = " + real + " - " + imaginary + "i");
        }
    }
}
