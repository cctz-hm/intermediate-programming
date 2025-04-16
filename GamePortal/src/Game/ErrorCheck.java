package Game;

import java.util.Scanner;

public class ErrorCheck {

    /**
     * Prompts the user for an integer input using the given Scanner.
     * Keeps prompting until a valid integer is entered.
     */
    public static int getInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer:");
            sc.next(); // discard invalid input
        }
        return sc.nextInt();
    }

    /**
     * Prompts the user for a positive integer input.
     */
    public static int getPositiveInt(Scanner sc) {
        int val = getInt(sc);
        while (val <= 0) {
            System.out.println("Please enter a positive integer:");
            val = getInt(sc);
        }
        return val;
    }

    /**
     * Prompts the user for an integer within a given range [min, max].
     */
    public static int getIntInRange(Scanner sc, int min, int max) {
        int val = getInt(sc);
        while (val < min || val > max) {
            System.out.println("Please enter a number between " + min + " and " + max + ":");
            val = getInt(sc);
        }
        return val;
    }
}
