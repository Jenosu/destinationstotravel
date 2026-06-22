import java.util.Random;
import java.util.Scanner;

public class GuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        
        while (playAgain) {
            int numberToGuess = random.nextInt(100) + 1;
            int numberOfGuesses = 0;
            int guess;
            
            System.out.println("I'm thinking of a number between 1 and 100. Can you guess what it is?");
            
            do {
                System.out.print("Enter your guess: ");
                guess = scanner.nextInt();
                numberOfGuesses++;
                
                if (guess < numberToGuess) {
                    System.out.println("Your guess is too low.");
                } else if (guess > numberToGuess) {
                    System.out.println("Your guess is too high.");
                }
            } while (guess != numberToGuess);
            
            System.out.println("Congratulations! You guessed the number in " + numberOfGuesses + " guesses.");
            System.out.print("Do you want to play again? (y/n): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("y");
        }
        
        scanner.close();
    }

}
