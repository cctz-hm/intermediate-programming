package Game;
import java.io.File;

public interface Game {
    String getGameName();

    int play(); // must be able to play a game

    String getScore(); // get a score - if there is no "score" you can return return "N/A" or something.

    String getUsername();
    void writeHighScore(File f); // writes the high score of this game to a file.
}


//edit Quiz.java and ShoppingGameUI.java so that there is a user. then edit both files so that each time the game is played, the results are uploaded to Highscore.csv

