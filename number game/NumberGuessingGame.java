import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int totalRounds = 0;
        int totalAttempts = 0;
        int bestScore = Integer.MAX_VALUE;
        
        System.out.println("Welcome to the Number Guessing Game!");
        
        boolean playAgain = true;
        while (playAgain) {
            totalRounds++;
            int attempts = 0;
            int numberToGuess = random.nextInt(100) + 1; // generates a random number between 1 and 100 inclusive
            System.out.println("\nRound " + totalRounds + ". Guess a number between 1 and 100.");
            
            while (true) {
                attempts++;
                System.out.print("Enter your guess (or 'q' to quit): ");
                String input = scanner.nextLine().trim();
                
                if (input.equalsIgnoreCase("q")) {
                    System.out.println("Quitting the game.");
                    playAgain = false;
                    break;
                }
                
                try {
                    int guess = Integer.parseInt(input);
                    if (guess < 1 || guess > 100) {
                        System.out.println("Please enter a number within the range of 1 to 100.");
                        continue;
                    }
                    
                    if (guess < numberToGuess) {
                        System.out.println("Too low. Try again!");
                    } else if (guess > numberToGuess) {
                        System.out.println("Too high. Try again!");
                    } else {
                        System.out.println("Congratulations! You've guessed the correct number " + numberToGuess + " in " + attempts + " attempts.");
                        totalAttempts += attempts;
                        if (attempts < bestScore) {
                            bestScore = attempts;
                        }
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }
            
            if (playAgain) {
                System.out.print("Do you want to play again? (yes/no): ");
                String playAgainInput = scanner.nextLine().trim();
                if (!playAgainInput.equalsIgnoreCase("yes")) {
                    playAgain = false;
                }
            }
        }
        
        System.out.println("\nGame Over! You played " + totalRounds + " round(s) with a total of " + totalAttempts + " attempts.");
        System.out.println("Your best score was " + bestScore + " attempt(s) in a single round.");
        
        scanner.close();
    }
}
