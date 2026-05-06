import java.util.Scanner;

public class EvenOddCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int even = 0, odd = 0;
        System.out.println("Enter 5 numbers:");
        for (int i = 0; i < 5; i++) {
            int num = scanner.nextInt();
            if (num % 2 == 0) even++;
            else odd++;
        }
        System.out.println("Even numbers: " + even);
        System.out.println("Odd numbers: " + odd);
        scanner.close();
    }
}
