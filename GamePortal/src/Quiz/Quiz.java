
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
        private String username = "";
        private String finalCharacterResult = "";



        public static void main(String[] args) throws Exception {

                Quiz quiz = new Quiz();
                quiz.run();

        }
        public void run() {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter your username: ");
            username = input.nextLine();
            
                // Create Categories
                Category elf = new Category("Elf/Hip Pop", "You are mischievous and overall funny. You should listen to more hip pop.", "Elf", "Hip Pop");
                Category frosty = new Category("Frosty the Snowman/Jazz", "You are laid back and relatively chill. You should listen to more jazz", "Frosty", "Jazz");
                Category santa = new Category("Santa/Classical", "You can sometimes be dramatic but are overall classy and the face of events. You should listen to more classical", "Santa", "Classical");
                Category grinch = new Category("Grinch/Rock", "You don't like going with the flow and are relatively rebellious. You should listen to more rock.", "Grinch", "Rock");
            
                // Create and ask questions
                Question[] qList = {
                    new Question("It's 8 AM on Christmas morning, you just woke What is the first thing you do?"),
                    new Question("It's been a few hours, and now you're very hungry. What do you do?"),
                    new Question("Its time for Christmas dinner! What song are you putting on?"),
                    new Question("It's been a long day, and you just want to relax. What's your go-to Christmas movie to watch?"),
                    new Question("What time of day do you usually listen to music?"),
                    new Question("What is your favorite color?"),
                    new Question("What is your favorite season of the year?"),
                    new Question("What is your favorite dessert?")
                };
            
                // Link answers
                qList[0].possibleAnswers = new Answer[]{
                    new Answer("Rush to wake up my family.", elf),
                    new Answer("Open everyone's presents by myself", frosty),
                    new Answer("Slowly get up and make myself breakfast", santa),
                    new Answer("Go back to sleep—it's too early for this.", grinch)
                };
                qList[1].possibleAnswers = new Answer[]{
                    new Answer("Drink a glass of ice cold eggnog.", grinch),
                    new Answer("Drink a mug of hot cocoa with marshmallows", elf),
                    new Answer("Snack on the gingerbread house I made yesterday", frosty),
                    new Answer("Have a few christmas decorated sugar cookies.", santa)
                };
                qList[2].possibleAnswers = new Answer[]{
                    new Answer("Jingle Bell Rock.", elf),
                    new Answer("You're a Mean One, Mr. Grinch", grinch),
                    new Answer("Silent Night", santa),
                    new Answer("Last Christmas", frosty)
                };
                qList[3].possibleAnswers = new Answer[]{
                    new Answer("Home Alone", frosty),
                    new Answer("Love Actually", santa),
                    new Answer("The Nightmare Before Christmas", grinch),
                    new Answer("Elf", elf)
                };
                qList[4].possibleAnswers = new Answer[]{
                    new Answer("7-10AM", santa),
                    new Answer("11AM-4PM", frosty),
                    new Answer("4-8PM", elf),
                    new Answer("9PM and later", grinch)
                };
                qList[5].possibleAnswers = new Answer[]{
                    new Answer("Shades of green, blue, or purple", frosty),
                    new Answer("Black or white", grinch),
                    new Answer("Shades of red, yellow, or orange", santa),
                    new Answer("Shades of brown or grey", elf)
                };
                qList[6].possibleAnswers = new Answer[]{
                    new Answer("Spring", santa),
                    new Answer("Summer", grinch),
                    new Answer("Autumn", elf),
                    new Answer("Winter", frosty)
                };
                qList[7].possibleAnswers = new Answer[]{
                    new Answer("Cake", elf),
                    new Answer("Ice cream", frosty),
                    new Answer("Cookies", santa),
                    new Answer("I don't eat dessert", grinch)
                };
            
                gameIntro();
                for (Question q : qList) {
                    Category c = q.ask(sc);
                    c.points++;
                }
            
                Category[] cList = {elf, frosty, santa, grinch};
                int index = getMostPopularCatIndex(cList);
                finalCharacterResult = cList[index].output1;
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
                        run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                return 0;   
        }

        @Override
        public String getScore() {
                return finalCharacterResult;
        }

        @Override
        public String getUsername(){
            return username;
            
        }

        @Override
        public boolean isHighScore(String score, String currentHighScore) {
            if (currentHighScore == null) {
                return true;
            } 
            if (score != currentHighScore){
                return true;

            }
                return false; 
        }

}
