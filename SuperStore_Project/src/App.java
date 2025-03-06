import processing.core.PApplet;

public class App {
    public static void main(String[] args) throws Exception {
        String[] processingArgs = { "ShoppingGame" };
        ShoppingGameUI game = new ShoppingGameUI();
        PApplet.runSketch(processingArgs, game);
    }
}
