/*
 * Irene Feng 10/12/2022
 */
public class Category {

    String label;
    String description; // after user is done and gets this category, will output description.
    int points = 0;
    String output1;
    String output2;

    Category(String c, String description, String output1, String output2) {
        this.label = c;
        this.output1 = output1;
        this.output2 = output2;
        this.description = description;
    }

}
