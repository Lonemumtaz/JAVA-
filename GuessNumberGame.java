import java.util.Scanner;

public class GuessNumberGame {
    public static void main(String[] args) {
        int numberToGuess = 7;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Guess a number between 1 and 10:");
        int guess = scanner.nextInt();
        if (guess == numberToGuess) {
            System.out.println("Correct! You win.");
        } else {
            System.out.println("Wrong! The number was " + numberToGuess);
        }
        scanner.close();
    }
}
