import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int number = rand.nextInt(100) + 1;
        int attempts = 0;
        int maxAttempts = 10;
        System.out.println("Guess the number (1-100). You have " + maxAttempts + " attempts.");

        while (attempts < maxAttempts) {
            System.out.print("Enter your guess: ");
            int guess = sc.nextInt();
            attempts++;
            if (guess == number) {
                System.out.println("Correct! You guessed it in " + attempts + " attempts.");
                break;
            } else if (guess < number) {
                System.out.println("Too low!");
            } else {
                System.out.println("Too high!");
            }
        }
        if (attempts == maxAttempts) {
            System.out.println("Out of attempts! The number was " + number);
        }
        sc.close();
    }
}