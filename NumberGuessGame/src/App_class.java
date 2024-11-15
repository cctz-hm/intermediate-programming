//Ciana Tzuo

import java.util.Random;
import java.util.Scanner;

class Game {
     private int computerRange;
     private int computerNumber;
     private int guessCount;
     private Scanner sc;
 
     public Game(int range, Scanner scanner) {
         this.computerRange = range;
         this.sc = scanner;
         Random r = new Random();
         this.computerNumber = r.nextInt(computerRange + 1);
         this.guessCount = 0;
     }
 
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
 
             guessCount++; 
 
             if (userGuess == computerNumber) {
                 System.out.println("Correct. You got it in " + guessCount + " guesses.");
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

class BestOfThree {
     private int range;
     private Scanner sc;
 
     public BestOfThree(int range, Scanner sc) {
         this.range = range;
         this.sc= sc;
     }
 
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
     public static void main(String[] args) {
          Scanner sc = new Scanner(System.in);
          int computerRange = 0;
  
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
  
          BestOfThree bestOfThree = new BestOfThree(computerRange, sc);
          int bestGameScore = bestOfThree.Play();
          
          System.out.println("The best game score was: " + bestGameScore + " guesses.");
          sc.close();
      }
     
}

