package NumberGuess;

//Ciana Tzuo

import java.util.Random;
import java.util.Scanner;


public class App {
    public static void main(String[] args) throws Exception {
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        int computerRange = 0;
        int counter = 0;

        

        System.out.println("Enter range:");

        while (true) {
            if (sc.hasNextInt()) {
                computerRange = sc.nextInt();
                if (computerRange > 0) {
                    break;
                } else {
                    System.out.println("Please enter a positive integer for the range:");
                }
            } else {
                System.out.println("That's not an integer. Try again:");
                sc.next();
            }
        }

        int computerNumber = r.nextInt(computerRange + 1);

        System.out.println("The computer has a number, plz guess:");

        while (true) {
            int userGuess;
            counter = counter++;

            if (sc.hasNextInt()) {
                userGuess = sc.nextInt();

                if (userGuess < 0 || userGuess > computerRange) {
                    System.out.println("That's not in range. Try again.");
                    continue;

                }
            } else {
                System.out.println("That's not a number. Please enter an integer:");
                sc.next();
                continue;
            }
            counter++;

            if (userGuess == computerNumber) {
                System.out.println("You got it in " + counter + " guesses!");
                break;
            } else if (userGuess > computerNumber) {
                System.out.println("The number is smaller than " + userGuess);
            } else {
                System.out.println("The number is larger than " + userGuess);
            }

        }
        sc.close();
    }

    

}
