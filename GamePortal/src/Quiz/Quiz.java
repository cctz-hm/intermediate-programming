
/*
 * Irene Feng Nov 2022
 * This is the class where we create the Quiz and run it. It has the main method.  
 * 
 * © Suli and Ciana 
 * 
 * Copyright Division of work: 
 * - Question writing (both)
 * - coding the questions (suli) 
 * - coding the results (ciana)
 * - cases in Questions.java (both)
 * - cases in Quiz.java (both, Ciana did non integer part)
 * 
 */
package Quiz;

import java.io.File;
import java.util.Scanner;

import Game.ErrorCheck;
import Game.Game;
import Game.GameWriteable;
import processing.core.PApplet;


public class Quiz implements GameWriteable{
        static Scanner sc = new Scanner(System.in);

        public static void main(String[] args) throws Exception {
                // Create Categories
                Category elf = new Category("Elf/Hip Pop",
                                "You are mischievous and overall funny. You should listen to more hip pop.", "Elf", "Hip Pop");              
                
                Category frosty = new Category("Frosty the Snowman/Jazz",
                "You are laid back and relatively chill. You should listen to more jazz", "Frosty", "Jazz");

                Category santa = new Category("Santa/Classical",
                                "You can sometimes be dramatic but are overall classy and the face of events. You should listen to more classical", "Santa", "Classical");
                Category grinch = new Category("Grinch/Rock", 
                "You don't like going with the flow and are relatively rebellious. You should listen to more rock.", "Grinch", "Rock");

                // Create Questions
                Question q1 = new Question("It's 8 AM on Christmas morning, you just woke What is the first thing you do?");
                // Attach Answers to Questions
                q1.possibleAnswers[0] = new Answer("Rush to wake up my family.", elf);
                q1.possibleAnswers[1] = new Answer("Open everyone's presents by myself", frosty);
                q1.possibleAnswers[2] = new Answer("Slowly get up and make myself breakfast", santa);
                q1.possibleAnswers[3] = new Answer("Go back to sleep—it's too early for this.", grinch);

                Question q2 = new Question("It's been a few hours, and now you're very hungry. What do you do?");
                // Attach Answers to Questions
                q2.possibleAnswers[0] = new Answer("Drink a glass of ice cold eggnog.", grinch);
                q2.possibleAnswers[1] = new Answer("Drink a mug of hot cocoa with marshmallows", elf);
                q2.possibleAnswers[2] = new Answer("Snack on the gingerbread house I made yesterday", frosty);
                q2.possibleAnswers[3] = new Answer("Have a few christmas decorated sugar cookies.", santa);

                Question q3 = new Question("Its time for Christmas dinner! What song are you putting on?");
                // Attach Answers to Questions
                q3.possibleAnswers[0] = new Answer("Jingle Bell Rock.", elf);
                q3.possibleAnswers[1] = new Answer("You're a Mean One, Mr. Grinch", grinch);
                q3.possibleAnswers[2] = new Answer("Silent Night", santa);
                q3.possibleAnswers[3] = new Answer("Last Christmas", frosty);

                Question q4 = new Question("It's been a long day, and you just want to relax. What's your go-to Christmas movie to watch?");
                // Attach Answers to Questions
                q4.possibleAnswers[0] = new Answer("Home Alone", frosty);
                q4.possibleAnswers[1] = new Answer("Love Actually", santa);
                q4.possibleAnswers[2] = new Answer("The Nightmare Before Christmas", grinch);
                q4.possibleAnswers[3] = new Answer("Elf", elf);

                Question q5 = new Question("What time of day do you usually listen to music?");
                // Attach Answers to Questions
                q5.possibleAnswers[0] = new Answer("7-10AM", santa);
                q5.possibleAnswers[1] = new Answer("11AM-4PM", frosty);
                q5.possibleAnswers[2] = new Answer("4-8PM", elf);
                q5.possibleAnswers[3] = new Answer("9PM and later", grinch);

                Question q6 = new Question("What is your favorite color?");
                // Attach Answers to Questions
                q6.possibleAnswers[0] = new Answer("Shades of green, blue, or purple", frosty);
                q6.possibleAnswers[1] = new Answer("Black or white", grinch);
                q6.possibleAnswers[2] = new Answer("Shades of red, yellow, or orange", santa);
                q6.possibleAnswers[3] = new Answer("Shades of brown or grey", elf);

                Question q7 = new Question("What is your favorite season of the year?");
                // Attach Answers to Questions
                q7.possibleAnswers[0] = new Answer("Spring", santa);
                q7.possibleAnswers[1] = new Answer("Summer", grinch);
                q7.possibleAnswers[2] = new Answer("Autumn", elf);
                q7.possibleAnswers[3] = new Answer("Winter", frosty);

                Question q8 = new Question("What is your favorite dessert?");
                // Attach Answers to Questions
                q8.possibleAnswers[0] = new Answer("Cake", elf);
                q8.possibleAnswers[1] = new Answer("Ice cream", frosty);
                q8.possibleAnswers[2] = new Answer("Cookies", santa);
                q8.possibleAnswers[3] = new Answer("I don't eat dessert", grinch);
              
                
                // For each question, ask, read input, store answer.
                gameIntro();
                Question[] qList = { q1, q2, q3, q4, q5, q6, q7, q8};
                for (Question q : qList) {
                        Category c = q.ask(sc);
                        c.points++;
                }
                // Get most common ctegory from the questions asked
                // Return Category
                Category[] cList = {elf, frosty, santa, grinch };
                int index = getMostPopularCatIndex(cList);
                System.out.println("If you were Christmas character, you would be " + cList[index].output1 + ". Your music genre is " + cList[index].output2);
                System.out.println(cList[index].description);

        }

        public static void gameIntro() {
                // requires 1 to keep going
                System.out.println("Which Christmas character are you and what music you should listen to?");
                System.out.println("You get to choose numbers 1-4 for every question. Enter '1' to play!");

                int play = ErrorCheck.getInt(sc);
                if (play != 1) {
                        System.out.println("Unidentifiable input. Please enter '1' to play");
                        gameIntro();
                }                
        }

        // returns the index that is the max
        // the tie breaker is the first Category that has the count is the "max" :/ 
        public static int getMostPopularCatIndex(Category[] counts) {
                int maxCount = 0;
                int maxIndex = 0;
                for (int i = 0; i < counts.length; i++) {
                        if (counts[i].points > maxCount) {
                                maxCount = counts[i].points;
                                maxIndex = i;
                        }
                }
                return maxIndex;
        }

        @Override
        public String getGameName() {
                return "Christmas Music Quiz";
        }

        @Override
        public int play() {
                try {
                        main(new String[]{}); // fixed
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                return 0;   
        }

        @Override
        public String getScore() {
                return "N/A"; 
        }

        @Override
        public void writeHighScore(File f) {
                System.out.println("Thanks for playing the quiz! No high score recorded.");
        }

        @Override
        public boolean isHighScore(String score, String currentHighScore) {
                return false; 
        }


}
