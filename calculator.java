import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Simple Calculator ---");
            System.out.println("1. Add\n2. Subtract\n3. Multiply\n4. Divide\n5. Modulus\n6. Exit");
            System.out.print("Choose an operation: ");
            choice = sc.nextInt();
            if (choice == 6) break;
            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice!");
                continue;
            }
            System.out.print("Enter first number: ");
            double a = sc.nextDouble();
            System.out.print("Enter second number: ");
            double b = sc.nextDouble();
            try {
                double result = switch (choice) {
                    case 1 -> a + b;
                    case 2 -> a - b;
                    case 3 -> a * b;
                    case 4 -> {
                        if (b == 0) throw new ArithmeticException("Cannot divide by zero!");
                        yield a / b;
                    }
                    case 5 -> a % b;
                    default -> 0;
                };
                System.out.println("Result: " + result);
            } catch (ArithmeticException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
        sc.close();
    }
}