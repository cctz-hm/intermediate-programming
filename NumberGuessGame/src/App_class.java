//Ciana Tzuo

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;


// Game class that controls the variables needed in the Game 
class Game {
     int computerRange;
     int computerNumber;
     int guessCount;
     Scanner sc;
     ArrayList<Integer> pastGuesses = new ArrayList<Integer>();

 
     public Game(int range, Scanner scanner) {
         this.computerRange = range;
         this.sc = scanner;
         Random r = new Random();
         this.computerNumber = r.nextInt(computerRange + 1);
         this.guessCount = 0;
     }
 
     // Play function that controls the cases when entering a range
     public int play() {
         System.out.println("The computer has chosen a number. Start Guessing!");
         while (true) {
             System.out.print("Enter your guess: ");
             int userGuess;
             
             if (sc.hasNextInt()) {
                 userGuess = sc.nextInt();
                 if (userGuess < 0 || userGuess > computerRange) {
                     System.out.println("Guess out of range. Try a number between 0 and " + computerRange);
                     continue;
                 }
             } else {
                 System.out.println("That's not a number. Please enter an integer:");
                 sc.next(); 
                 continue;
             }

              if (pastGuesses.contains(userGuess)) {
                System.out.println("You already guessed " + userGuess + ". Try something else.");
                continue;
            }

             pastGuesses.add(userGuess);
             guessCount++; 

 
             if (userGuess == computerNumber) {
                System.out.println("Correct. You got it in " + guessCount + " guesses.\n Your guesses: " + pastGuesses);
                 break;
             } else if (userGuess > computerNumber) {
                 System.out.println("The number is smaller than " + userGuess);
             } else {
                 System.out.println("The number is larger than " + userGuess);
             }
         }
          
         return guessCount; 
     }
 }

// BestOfThree class that controls the larger game with the three rounds
class BestOfThree {
    int range;
    Scanner sc;
 
     public BestOfThree(int range, Scanner sc) {
         this.range = range;
         this.sc= sc;
     }
 
     // Play function that controls the scoring of the game and starts new game
     public int Play() {
         int bestScore = 10000;
 
         for (int i = 1; i <= 3; i++) {
             System.out.println("\nStarting Game " + i + " of 3:");
             Game game = new Game(range, sc);
             int currentScore = game.play(); 
             
             if (currentScore < bestScore) {
                 bestScore = currentScore;
             }
         }
         return bestScore; 
     }
 }
 

class App_class {
     public static void main(String[] args) throws Exception {
          Scanner sc = new Scanner(System.in);
          int computerRange = 0;
          FileSave fileSave;

          fileSave = new FileSave("NumberGuessGameResults.csv");

          System.out.print("Enter your username: ");
          String username = sc.next();

          HashMap<String, String> results = fileSave.getResults();
          String pastScores;

        // see if user exists
          if (results.containsKey(username)) {
            System.out.println("Welcome back, " + username);
            pastScores = results.get(username); 
        } else {
            System.out.println("Welcome, " + username);
            pastScores = ""; 
        }
  
          System.out.println("Enter a positive integer range for the game:");
          while (true) {
              if (sc.hasNextInt()) {
                  computerRange = sc.nextInt();
                  if (computerRange > 0) {
                      break;
                  } else {
                      System.out.println("Enter a positive integer for the range:");
                  }
              } else {
                  System.out.println("That's not an integer. Try again:");
                  sc.next();
              }
          }
          
          // initializing the game
          BestOfThree bestOfThree = new BestOfThree(computerRange, sc);
          int bestGameScore = bestOfThree.Play();

          
          System.out.println("The best game score for '" + username + "' was: " + bestGameScore + " guesses.");
          
        if (!pastScores.isEmpty()) {
            int oldBestScore = Integer.parseInt(pastScores.trim()); //removes extra spaces, string to int
            if (bestGameScore < oldBestScore) {
                System.out.println("New best score.");
                pastScores = String.valueOf(bestGameScore); 
            } else {
                System.out.println("Your new score is not better than your previous best.");
            }
        } else {
            pastScores = String.valueOf(bestGameScore);
        }
        
        fileSave.updateResult(username, pastScores);
        
          
          sc.close();
      }
     
}


