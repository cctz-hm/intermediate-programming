package NumberGuess;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

import Game.ErrorCheck;
import Game.GameWriteable;

public class NumberGuessGame implements GameWriteable {
    private int bestGameScore;
    private String lastUsername;
    private Scanner sc = new Scanner(System.in);

    @Override
    public String getGameName() {
        return "Number Guess";
    }

    @Override
    public int play() {
        System.out.print("Enter your username: ");
        lastUsername = sc.next();

        System.out.println("Enter a positive integer range for the game:");
        int computerRange = ErrorCheck.getPositiveInt(sc);


        BestOfThree bestOfThree = new BestOfThree(computerRange, sc);
        bestGameScore = bestOfThree.Play();
        System.out.println("Best score: " + bestGameScore);
        return bestGameScore;
    }

    @Override
    public String getScore() {
        return String.valueOf(bestGameScore);
    }

    @Override
    public boolean isHighScore(String newScore, String oldScore) {
        if (oldScore == null || oldScore.equals("N/A")) return true;
        try {
            return Integer.parseInt(newScore) < Integer.parseInt(oldScore);
        } catch (NumberFormatException e) {
            return true;
        }
    }

    @Override
    public void writeHighScore(File f) {
        GameWriteable.super.writeHighScore(f); // Uses default method
    }
}
