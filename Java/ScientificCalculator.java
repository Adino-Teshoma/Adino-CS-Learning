import javax.swing.*;
import java.awt.*;
import java.math.BigInteger;
import java.util.Locale;

public class ScientificCalculator extends JFrame {

    private final JComboBox<String> operationBox;
    private final JComboBox<String> matrixMode;
    private final JTextArea instructions;
    private final JTextArea inputArea;
    private final JTextArea resultArea;

    private final String[] operations = {
        "1. Addition",
        "2. Subtraction",
        "3. Multiplication",
        "4. Division",
        "5. Square Root",
        "6. Power",
        "7. Sine (degrees)",
        "8. Cosine (degrees)",
        "9. Tangent (degrees)",
        "10. Logarithm (base 10)",
        "11. Differentiate a polynomial",
        "12. Integrate a polynomial",
        "13. Matrix operations",
        "14. Linear equations",
        "15. Quadratic equation",
        "16. Evaluate a polynomial",
        "17. Exponential functions",
        "18. Inverse sine (degrees)",
        "19. Inverse cosine (degrees)",
        "20. Inverse tangent (degrees)",
        "21. Factorial",
        "22. Prime factorization",
        "23. Prime or composite number"
    };

    public ScientificCalculator() {
        super("Scientific Calculator");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(920, 780);
        setMinimumSize(new Dimension(680, 600));
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout(12, 12));
        root.setBorder(
            BorderFactory.createEmptyBorder(16, 16, 16, 16)
        );
        setContentPane(root);

        JLabel title = new JLabel(
            "SCIENTIFIC CALCULATOR",
            SwingConstants.CENTER
        );
        title.setFont(new Font("SansSerif", Font.BOLD, 25));

        operationBox = new JComboBox<>(operations);
        operationBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
        operationBox.setMaximumRowCount(15);

        matrixMode = new JComboBox<>(new String[] {
            "Matrix addition",
            "Matrix subtraction",
            "Matrix multiplication",
            "Matrix transpose"
        });

        JPanel selectors = new JPanel(new GridLayout(0, 1, 6, 6));
        selectors.add(new JLabel("Select operation:"));
        selectors.add(operationBox);
        selectors.add(matrixMode);

        JPanel top = new JPanel(new BorderLayout(10, 15));
        top.add(title, BorderLayout.NORTH);
        top.add(selectors, BorderLayout.CENTER);
        root.add(top, BorderLayout.NORTH);

        instructions = new JTextArea();
        instructions.setEditable(false);
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);
        instructions.setFont(new Font("SansSerif", Font.PLAIN, 14));
        instructions.setBackground(new Color(238, 244, 250));
        instructions.setMargin(new Insets(10, 10, 10, 10));

        inputArea = new JTextArea(6, 30);
        inputArea.setFont(new Font("Monospaced", Font.PLAIN, 17));
        inputArea.setMargin(new Insets(10, 10, 10, 10));

        resultArea = new JTextArea(9, 30);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setMargin(new Insets(10, 10, 10, 10));

        JPanel middle = new JPanel();
        middle.setLayout(new BoxLayout(middle, BoxLayout.Y_AXIS));

        JScrollPane helpScroll = new JScrollPane(instructions);
        helpScroll.setBorder(
            BorderFactory.createTitledBorder("Instructions")
        );
        helpScroll.setPreferredSize(new Dimension(700, 140));

        JScrollPane inputScroll = new JScrollPane(inputArea);
        inputScroll.setBorder(
            BorderFactory.createTitledBorder("Enter your values")
        );

        JScrollPane resultScroll = new JScrollPane(resultArea);
        resultScroll.setBorder(
            BorderFactory.createTitledBorder("Result")
        );

        middle.add(helpScroll);
        middle.add(Box.createVerticalStrut(10));
        middle.add(inputScroll);
        middle.add(Box.createVerticalStrut(10));
        middle.add(resultScroll);
        root.add(middle, BorderLayout.CENTER);

        JButton calculateButton = new JButton("Calculate");
        JButton exampleButton = new JButton("Load Example");
        JButton clearButton = new JButton("Clear");
        JButton exitButton = new JButton("Exit");

        calculateButton.setFont(
            new Font("SansSerif", Font.BOLD, 15)
        );

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.add(exampleButton);
        buttons.add(clearButton);
        buttons.add(calculateButton);
        buttons.add(exitButton);
        root.add(buttons, BorderLayout.SOUTH);

        operationBox.addActionListener(event -> updateHelp());
        matrixMode.addActionListener(event -> updateHelp());

        calculateButton.addActionListener(event -> calculate());

        exampleButton.addActionListener(event -> {
            inputArea.setText(exampleInput());
            resultArea.setText("");
        });

        clearButton.addActionListener(event -> {
            inputArea.setText("");
            resultArea.setText("");
            inputArea.requestFocusInWindow();
        });

        exitButton.addActionListener(event -> dispose());

        updateHelp();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
                );
            } catch (Exception ignored) {
                // Keep the default appearance.
            }

            new ScientificCalculator().setVisible(true);
        });
    }

    // ---------- Instructions and examples ----------

    private void updateHelp() {
        int operation = operationBox.getSelectedIndex() + 1;
        matrixMode.setVisible(operation == 13);

        String text;

        switch (operation) {
            case 1:
            case 2:
            case 3:
            case 4:
                text = "Enter two numbers separated by a space.\n"
                     + "Example: 8 5";
                break;

            case 5:
                text = "Enter one nonnegative number.\nExample: 25";
                break;

            case 6:
                text = "Enter base and exponent.\n"
                     + "Example: 2 3 means 2 raised to power 3.";
                break;

            case 7:
            case 8:
            case 9:
                text = "Enter one angle in DEGREES.\nExample: 30";
                break;

            case 10:
                text = "Enter one positive number.\n"
                     + "Calculates the base-10 logarithm.";
                break;

            case 11:
                text = "Line 1: polynomial coefficients, highest power first.\n"
                     + "Example: 3 2 1 means 3*x^2 + 2*x + 1.\n"
                     + "Optional line 2: x at which to evaluate the derivative.";
                break;

            case 12:
                text = "Line 1: polynomial coefficients, highest power first.\n"
                     + "Optional line 2: lower and upper limits.\n"
                     + "Example: line 1 = 1 0 0; line 2 = 0 2.";
                break;

            case 13:
                if (matrixMode.getSelectedIndex() == 3) {
                    text = "Enter one matrix, one row per line.\n"
                         + "Separate entries with spaces.\n"
                         + "All rows must have the same number of entries.";
                } else {
                    text = "Enter matrix A, a BLANK LINE, then matrix B.\n"
                         + "Use one row per line and spaces between entries.\n"
                         + "Add/subtract: same dimensions. Multiply: "
                         + "columns of A must equal rows of B.";
                }
                break;

            case 14:
                text = "One equation a*x + b = 0: enter a b on ONE line.\n"
                     + "For two equations, enter TWO lines:\n"
                     + "a b c for a*x + b*y = c\n"
                     + "d e f for d*x + e*y = f";
                break;

            case 15:
                text = "Enter a b c for a*x^2 + b*x + c = 0.\n"
                     + "Example: 1 -5 6";
                break;

            case 16:
                text = "Line 1: polynomial coefficients, highest power first.\n"
                     + "Line 2: value of x.\n"
                     + "Example: line 1 = 2 3 1; line 2 = 2.";
                break;

            case 17:
                text = "Enter x to calculate e^x.\n"
                     + "Or enter a x to calculate a^x, with a > 0.";
                break;

            case 18:
                text = "Enter a number between -1 and 1.\n"
                     + "Calculates inverse sine, asin(x), in degrees.\n"
                     + "This is not the same as 1/sin(x).";
                break;

            case 19:
                text = "Enter a number between -1 and 1.\n"
                     + "Calculates inverse cosine, acos(x), in degrees.";
                break;

            case 20:
                text = "Enter one number.\n"
                     + "Calculates inverse tangent, atan(x), in degrees.";
                break;

            case 21:
                text = "Enter a whole number from 0 to 1000.\n"
                     + "Example: 5 gives 5! = 120.\n"
                     + "0! = 1.";
                break;

            case 22:
                text = "Enter a whole number from 2 to 1000000000000.\n"
                     + "Example: 60 = 2 * 2 * 3 * 5.\n"
                     + "This factors integers, not polynomial expressions.";
                break;

            case 23:
                text = "Enter a whole number from 0 to 1000000000000.\n"
                     + "Checks whether it is prime or composite.\n"
                     + "0 and 1 are neither prime nor composite.";
                break;

            default:
                text = "";
        }

        instructions.setText(text);
        instructions.setCaretPosition(0);
        inputArea.setText(exampleInput());
        resultArea.setText("");
        revalidate();
        repaint();
    }

    private String exampleInput() {
        switch (operationBox.getSelectedIndex() + 1) {
            case 1: return "8 5";
            case 2: return "8 5";
            case 3: return "8 5";
            case 4: return "8 2";
            case 5: return "25";
            case 6: return "2 3";
            case 7: return "30";
            case 8: return "60";
            case 9: return "45";
            case 10: return "100";
            case 11: return "3 2 1\n2";
            case 12: return "1 0 0\n0 2";

            case 13:
                return matrixMode.getSelectedIndex() == 3
                    ? "1 2 3\n4 5 6"
                    : "1 2\n3 4\n\n5 6\n7 8";

            case 14: return "2 1 5\n1 -1 1";
            case 15: return "1 -5 6";
            case 16: return "2 3 1\n2";
            case 17: return "2";
            case 18: return "0.5";
            case 19: return "0.5";
            case 20: return "1";
            case 21: return "5";
            case 22: return "60";
            case 23: return "97";
            default: return "";
        }
    }

    // ---------- Main calculation ----------

    private void calculate() {
        try {
            int operation = operationBox.getSelectedIndex() + 1;
            String text = inputArea.getText().trim();

            require(!text.isEmpty(), "Please enter some values.");

            String answer;

            switch (operation) {
                case 11:
                    answer = differentiate(text);
                    break;
                case 12:
                    answer = integrate(text);
                    break;
                case 13:
                    answer = matrixOperation(text);
                    break;
                case 14:
                    answer = linearEquations(text);
                    break;
                case 15:
                    answer = quadratic(text);
                    break;
                case 16:
                    answer = evaluatePolynomial(text);
                    break;
                case 21:
                    answer = factorial(text);
                    break;
                case 22:
                    answer = primeFactorization(text);
                    break;
                case 23:
                    answer = primeOrComposite(text);
                    break;
                default:
                    answer = basicOperation(operation, text);
            }

            resultArea.setText(answer);
            resultArea.setCaretPosition(0);

        } catch (IllegalArgumentException exception) {
            resultArea.setText("Input error:\n" + exception.getMessage());

        } catch (ArithmeticException exception) {
            resultArea.setText(
                "Calculation error:\n" + exception.getMessage()
            );
        }
    }

    // ---------- Basic and scientific operations ----------

    private String basicOperation(int operation, String text) {
        double[] n = parseNumbers(text);
        double answer;

        switch (operation) {
            case 1:
                count(n, 2);
                answer = n[0] + n[1];
                break;

            case 2:
                count(n, 2);
                answer = n[0] - n[1];
                break;

            case 3:
                count(n, 2);
                answer = n[0] * n[1];
                break;

            case 4:
                count(n, 2);
                require(n[1] != 0, "Cannot divide by zero.");
                answer = n[0] / n[1];
                break;

            case 5:
                count(n, 1);
                require(n[0] >= 0,
                        "Enter a nonnegative number for a real square root.");
                answer = Math.sqrt(n[0]);
                break;

            case 6:
                count(n, 2);
                answer = Math.pow(n[0], n[1]);
                break;

            case 7:
                count(n, 1);
                answer = Math.sin(Math.toRadians(n[0]));
                break;

            case 8:
                count(n, 1);
                answer = Math.cos(Math.toRadians(n[0]));
                break;

            case 9:
                count(n, 1);
                require(Math.abs(n[0] % 180.0) != 90.0,
                        "Tangent is undefined at this angle.");
                answer = Math.tan(Math.toRadians(n[0]));
                break;

            case 10:
                count(n, 1);
                require(n[0] > 0,
                        "Logarithm requires a positive number.");
                answer = Math.log10(n[0]);
                break;

            case 17:
                require(n.length == 1 || n.length == 2,
                        "Enter x for e^x, or a x for a^x.");

                if (n.length == 1) {
                    answer = Math.exp(n[0]);
                } else {
                    require(n[0] > 0,
                            "The exponential base must be positive.");
                    answer = Math.pow(n[0], n[1]);
                }
                break;

            case 18:
                count(n, 1);
                require(n[0] >= -1 && n[0] <= 1,
                        "Inverse sine requires a value from -1 to 1.");
                answer = Math.toDegrees(Math.asin(n[0]));
                break;

            case 19:
                count(n, 1);
                require(n[0] >= -1 && n[0] <= 1,
                        "Inverse cosine requires a value from -1 to 1.");
                answer = Math.toDegrees(Math.acos(n[0]));
                break;

            case 20:
                count(n, 1);
                answer = Math.toDegrees(Math.atan(n[0]));
                break;

            default:
                throw new IllegalArgumentException("Unknown operation.");
        }

        String unit = operation >= 18 && operation <= 20
                    ? " degrees" : "";

        return "Result = " + format(answer) + unit;
    }

    // ---------- Polynomial calculations ----------
    // Coefficients are stored highest power first.

    private String differentiate(String text) {
        String[] lines = nonemptyLines(text);
        require(lines.length <= 2, "Use one or two lines.");

        double[] coefficients = parseNumbers(lines[0]);
        int degree = coefficients.length - 1;
        double[] derivative = new double[Math.max(1, degree)];

        for (int i = 0; i < degree; i++) {
            derivative[i] = coefficients[i] * (degree - i);
        }

        String answer = "f(x) = " + polynomialText(coefficients)
                      + "\n\nf'(x) = " + polynomialText(derivative);

        if (lines.length == 2) {
            double[] point = parseNumbers(lines[1]);
            count(point, 1);

            answer += "\n\nf'(" + format(point[0]) + ") = "
                    + format(polynomialValue(derivative, point[0]));
        }

        return answer;
    }

    private String integrate(String text) {
        String[] lines = nonemptyLines(text);
        require(lines.length <= 2, "Use one or two lines.");

        double[] coefficients = parseNumbers(lines[0]);
        double[] integral = new double[coefficients.length + 1];

        for (int i = 0; i < coefficients.length; i++) {
            integral[i] =
                coefficients[i] / (coefficients.length - i);
        }

        String answer = "f(x) = " + polynomialText(coefficients)
                      + "\n\nIntegral = "
                      + polynomialText(integral) + " + C";

        if (lines.length == 2) {
            double[] limits = parseNumbers(lines[1]);
            count(limits, 2);

            double value = polynomialValue(integral, limits[1])
                         - polynomialValue(integral, limits[0]);

            answer += "\n\nDefinite integral from "
                    + format(limits[0]) + " to " + format(limits[1])
                    + " = " + format(value);
        }

        return answer;
    }

    private String evaluatePolynomial(String text) {
        String[] lines = nonemptyLines(text);
        require(lines.length == 2,
                "Use two lines: coefficients, then x.");

        double[] coefficients = parseNumbers(lines[0]);
        double[] point = parseNumbers(lines[1]);
        count(point, 1);

        return "f(x) = " + polynomialText(coefficients)
             + "\n\nf(" + format(point[0]) + ") = "
             + format(polynomialValue(coefficients, point[0]));
    }

    private double polynomialValue(double[] coefficients, double x) {
        double value = 0;

        for (double coefficient : coefficients) {
            value = value * x + coefficient;
        }

        return value;
    }

    private String polynomialText(double[] coefficients) {
        StringBuilder text = new StringBuilder();
        int degree = coefficients.length - 1;

        for (int i = 0; i < coefficients.length; i++) {
            double coefficient = coefficients[i];
            int power = degree - i;

            if (coefficient == 0) {
                continue;
            }

            if (text.length() > 0) {
                text.append(coefficient < 0 ? " - " : " + ");
            } else if (coefficient < 0) {
                text.append("-");
            }

            double magnitude = Math.abs(coefficient);

            if (power == 0 || magnitude != 1) {
                text.append(format(magnitude));

                if (power > 0) {
                    text.append("*");
                }
            }

            if (power > 0) {
                text.append("x");

                if (power > 1) {
                    text.append("^").append(power);
                }
            }
        }

        return text.length() == 0 ? "0" : text.toString();
    }

    // ---------- Matrices ----------

    private String matrixOperation(String text) {
        int mode = matrixMode.getSelectedIndex();

        String normalized = text.replace("\r", "").trim();
        String[] blocks = normalized.split("\\n\\s*\\n");

        if (mode == 3) {
            require(blocks.length == 1,
                    "Transpose requires one matrix only.");

            double[][] a = parseMatrix(blocks[0]);
            double[][] result = new double[a[0].length][a.length];

            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[0].length; j++) {
                    result[j][i] = a[i][j];
                }
            }

            return matrixText(result);
        }

        require(blocks.length == 2,
                "Separate matrix A and matrix B with a blank line.");

        double[][] a = parseMatrix(blocks[0]);
        double[][] b = parseMatrix(blocks[1]);

        if (mode == 0 || mode == 1) {
            require(a.length == b.length
                    && a[0].length == b[0].length,
                    "The matrices must have the same dimensions.");

            double[][] result = new double[a.length][a[0].length];

            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[0].length; j++) {
                    result[i][j] = mode == 0
                        ? a[i][j] + b[i][j]
                        : a[i][j] - b[i][j];
                }
            }

            return matrixText(result);
        }

        require(a[0].length == b.length,
                "Columns of A must equal rows of B.");

        double[][] result = new double[a.length][b[0].length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return matrixText(result);
    }

    private double[][] parseMatrix(String text) {
        String[] rows = nonemptyLines(text);
        require(rows.length <= 20, "Maximum matrix size is 20 by 20.");

        double[][] matrix = new double[rows.length][];
        int columns = -1;

        for (int i = 0; i < rows.length; i++) {
            matrix[i] = parseNumbers(rows[i]);

            if (columns == -1) {
                columns = matrix[i].length;
            }

            require(matrix[i].length == columns,
                    "Every row must have the same number of entries.");
            require(columns <= 20, "Maximum matrix size is 20 by 20.");
        }

        return matrix;
    }

    private String matrixText(double[][] matrix) {
        StringBuilder text = new StringBuilder("Result:\n\n");

        for (double[] row : matrix) {
            text.append("[ ");

            for (double value : row) {
                text.append(format(value)).append("  ");
            }

            text.append("]\n");
        }

        return text.toString();
    }

    // ---------- Linear equations ----------

    private String linearEquations(String text) {
        String[] lines = nonemptyLines(text);

        if (lines.length == 1) {
            double[] values = parseNumbers(lines[0]);
            count(values, 2);
            return solveLinear(values[0], values[1]);
        }

        require(lines.length == 2,
                "Use one line for one equation, or two lines for a system.");

        double[] first = parseNumbers(lines[0]);
        double[] second = parseNumbers(lines[1]);
        count(first, 3);
        count(second, 3);

        normalizeEquation(first);
        normalizeEquation(second);

        double a = first[0];
        double b = first[1];
        double c = first[2];
        double d = second[0];
        double e = second[1];
        double f = second[2];

        if ((a == 0 && b == 0 && c != 0)
                || (d == 0 && e == 0 && f != 0)) {
            return "No solution.";
        }

        double determinant = a * e - b * d;

        if (determinant != 0) {
            double x = (c * e - b * f) / determinant;
            double y = (a * f - c * d) / determinant;

            return "x = " + format(x) + "\ny = " + format(y);
        }

        if (a * f - c * d != 0 || b * f - c * e != 0) {
            return "No solution.";
        }

        return "Infinitely many solutions.";
    }

    private void normalizeEquation(double[] equation) {
        double scale = Math.max(
            Math.abs(equation[0]),
            Math.max(Math.abs(equation[1]), Math.abs(equation[2]))
        );

        if (scale != 0) {
            for (int i = 0; i < equation.length; i++) {
                equation[i] /= scale;
            }
        }
    }

    private String solveLinear(double a, double b) {
        if (a != 0) {
            return "x = " + format(-b / a);
        }

        return b == 0 ? "Infinitely many solutions." : "No solution.";
    }

    // ---------- Quadratic equations ----------

    private String quadratic(String text) {
        double[] values = parseNumbers(text);
        count(values, 3);

        double a = values[0];
        double b = values[1];
        double c = values[2];

        if (a == 0) {
            return "This is a linear equation.\n" + solveLinear(b, c);
        }

        double scale = Math.max(
            Math.abs(a), Math.max(Math.abs(b), Math.abs(c))
        );

        a /= scale;
        b /= scale;
        c /= scale;

        require(a != 0,
                "Coefficient sizes exceed this solver's numeric range.");

        double discriminant = b * b - 4 * a * c;

        if (discriminant > 0) {
            double root = Math.sqrt(discriminant);
            double q = -0.5 * (b + Math.copySign(root, b));

            return "Two real roots:\n\n"
                 + "x1 = " + format(q / a)
                 + "\nx2 = " + format(c / q);
        }

        if (discriminant == 0) {
            return "Repeated real root:\n\nx = "
                 + format(-b / (2 * a));
        }

        double real = -b / (2 * a);
        double imaginary =
            Math.sqrt(-discriminant) / (2 * Math.abs(a));

        return "Two complex roots:\n\n"
             + "x1 = " + format(real) + " + "
             + format(imaginary) + "i\n"
             + "x2 = " + format(real) + " - "
             + format(imaginary) + "i";
    }

    // ---------- Factorial ----------

    private String factorial(String text) {
        long number = readWholeNumber(text, 0, 1000);
        BigInteger result = BigInteger.ONE;

        for (long i = 2; i <= number; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }

        return number + "! =\n\n" + result;
    }

    // ---------- Prime factorization ----------

    private String primeFactorization(String text) {
        long number = readWholeNumber(
            text, 2, 1_000_000_000_000L
        );

        long remaining = number;
        StringBuilder factors = new StringBuilder();

        while (remaining % 2 == 0) {
            appendFactor(factors, 2);
            remaining /= 2;
        }

        for (long divisor = 3;
             divisor <= remaining / divisor;
             divisor += 2) {

            while (remaining % divisor == 0) {
                appendFactor(factors, divisor);
                remaining /= divisor;
            }
        }

        if (remaining > 1) {
            appendFactor(factors, remaining);
        }

        return number + " = " + factors;
    }

    private void appendFactor(StringBuilder factors, long factor) {
        if (factors.length() > 0) {
            factors.append(" * ");
        }

        factors.append(factor);
    }

    // ---------- Prime and composite checker ----------

    private String primeOrComposite(String text) {
        long number = readWholeNumber(
            text, 0, 1_000_000_000_000L
        );

        if (number < 2) {
            return number + " is NEITHER prime NOR composite.";
        }

        long divisor = smallestDivisor(number);

        if (divisor == number) {
            return number + " is a PRIME number.\n\n"
                 + "Its only positive divisors are 1 and "
                 + number + ".";
        }

        return number + " is a COMPOSITE number.\n\n"
             + "For example:\n"
             + number + " = " + divisor + " * "
             + (number / divisor);
    }

    private long smallestDivisor(long number) {
        if (number % 2 == 0) {
            return 2;
        }

        for (long divisor = 3;
             divisor <= number / divisor;
             divisor += 2) {

            if (number % divisor == 0) {
                return divisor;
            }
        }

        return number;
    }

    // ---------- Input validation ----------

    private long readWholeNumber(
            String text, long minimum, long maximum) {

        String trimmed = text.trim();

        require(trimmed.matches("\\+?\\d+"),
                "Enter one whole number without spaces or decimal points.");

        long number;

        try {
            number = Long.parseLong(trimmed);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(
                "The number is too large."
            );
        }

        require(number >= minimum && number <= maximum,
                "Enter a whole number from "
                + minimum + " to " + maximum + ".");

        return number;
    }

    private double[] parseNumbers(String text) {
        String trimmed = text.trim();
        require(!trimmed.isEmpty(), "A required input line is empty.");

        String[] tokens = trimmed.split("[\\s,]+");
        require(tokens.length <= 201,
                "Use no more than 201 numbers per input group.");

        double[] values = new double[tokens.length];

        for (int i = 0; i < tokens.length; i++) {
            try {
                values[i] = Double.parseDouble(tokens[i]);
            } catch (NumberFormatException exception) {
                throw new IllegalArgumentException(
                    "'" + tokens[i] + "' is not a valid number."
                );
            }

            require(Double.isFinite(values[i]),
                    "Use finite numbers only.");
        }

        return values;
    }

    private String[] nonemptyLines(String text) {
        String normalized = text.replace("\r", "").trim();
        require(!normalized.isEmpty(), "Please enter some values.");

        return normalized.split("\\n\\s*");
    } 

    private void count(double[] values, int expected) {
        require(values.length == expected,
                "Expected " + expected
                + " number(s), but received " + values.length + ".");
    }

    private void require(boolean condition, String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }

    private String format(double value) {
        if (!Double.isFinite(value)) {
            throw new ArithmeticException(
                "The result is outside the finite real-number range "
                + "or is undefined for these inputs."
            );
        }

        if (value == 0) {
            return "0";
        }

        return String.format(Locale.US, "%.12g", value);
    }
}
