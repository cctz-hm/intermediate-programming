package Store;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import Game.Game;
import Game.GameWriteable;
import processing.core.PApplet;
import processing.core.PSketch;
import java.util.concurrent.CountDownLatch;


public class ShoppingGameUI extends PApplet implements GameWriteable{
    Store store;
    Map<String, Integer> cart;
    double total;
    double budget;
    Map<Item, Integer> requiredItems;
    int selectedItemIndex = -1;
    int check = 0;
    private String username = "";
    private boolean fulfilledShoppingList = false;
    CountDownLatch latch;



    public void settings() {
        size(1024, 784); 
    }

    public void setup() {
        store = new Store();
        cart = new HashMap<>();
        total = 0;
        budget = 200 + new Random().nextInt(300);
        requiredItems = getRandomShoppingList(store);
        frameRate(30);
    }

    public void draw() {
        background(255); 
        drawItems();
        displayShoppingList();
        displayBudget();
        displayCartContents();
        displayExitButton();
        displayCheckoutButton();
        

        if (check == 1){
            displayBooleanRight();
        } else if (check == 2) {
            displayBooleanWrong();
        } else {

        }

    }

    void drawItems() {
        List<Item> items = store.getItems();
        float boxWidth = 300; 
        int yPos = 20;
        fill(0);
        textSize(14);
        text("Instructions: \n Buy items, following the shopping list, from the list on the left side. \n As you buy, the brands and prices will change. \n Try to fulfill the list and checkout when you are ready. \n Remember, you have a budget.", 375, 50);
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (item.isUpdated()) {
                fill(100, 250, 150); 
            } else {
                fill(240); 
            }

            rect(20, yPos, boxWidth, 40); 
            fill(0);
            text(i + 1 + ". " + item.getName() + " - $" + item.getPrice() + ", " + item.getBrand(), 30, yPos + 25);
            yPos += 60; 

            item.setUpdated(false);


        }
    }
    
    

    void displayShoppingList() {
        fill(0);
        int yPos = 20;
        text("Shopping List:", 800, yPos);
        yPos += 20;
        for (Map.Entry<Item, Integer> entry : requiredItems.entrySet()) {
            text("- " + entry.getKey().getName() + ": " + entry.getValue() + " units needed", 800, yPos);
            yPos += 20;
        }


    }



    void displayBudget() {
        fill(0);
        int yPos = 300;
        text("Budget: $" + budget, 800, yPos);
        text("Total spent: $" + total, 800, yPos + 20);
        text("Remaining budget: $" + budget, 800, yPos + 40);
    }

    void displayCartContents() {
        fill(0);
        int yPos = 400;
        text("Items Purchased:", 800, yPos);
        yPos += 20;
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            text(entry.getKey() + ": " + entry.getValue() + " units", 800, yPos);
            yPos += 20;
        }
    }

    void displayExitButton() {
        fill(200, 100, 100); 
        rect(800, height - 50, 100, 30); 
        fill(255); 
        text("Exit", 845, height - 30);
    }

    void displayCheckoutButton() {
        fill(100, 200, 100);
        rect(width - 110, height - 50, 100, 30);
        fill(255);
        text("Checkout", width - 95, height - 30);
    }

    void displayBooleanRight(){
        fill(88, 198, 193); 
        rect(550, height - 50, 190, 30); 
        fill(0); 
        text("All items purchased correctly!", 555, height - 30);
    }
    void displayBooleanWrong(){
        fill(88, 198, 193); 
        rect(550, height - 50, 190, 30); 
        fill(0); 
        text("Some items are missing or incorrect.", 555, height - 30);

    }

    public void mousePressed() {
        // exit button
        if (mouseX >= 800 && mouseX <= 900 && mouseY >= height - 50 && mouseY <= height) {
            System.out.println("exiting");
            latch.countDown();
            surface.setVisible(false); //ChatGPT help suggestion
            //exit();
        }

        // checkout button
        if (mouseX >= width - 110 && mouseX <= width - 10 && mouseY >= height - 50 && mouseY <= height) {
            fulfilledShoppingList = checkListFulfillment();
            if (checkListFulfillment()) {
                check = 1;
                println("All items purchased correctly!");
            } else {
                check = 2;
                println("Some items are missing or incorrect.");
            }
        }
        // Item selection
        for (int i = 0; i < store.getItems().size(); i++) {
            if (mouseY >= 20 + i * 60 && mouseY < 60 + i * 60 && mouseX >= 20 && mouseX <= 320) {
                selectedItemIndex = i;
                processItemSelection(store.getItems().get(i));
                store.updateRandomItem(); 
                return;
                
            }
            
        }
    }

    boolean checkListFulfillment() {
        for (Map.Entry<Item, Integer> entry : requiredItems.entrySet()) {
            if (cart.getOrDefault(entry.getKey().getName(), 0) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    void processItemSelection(Item item) {
        if (budget >= item.getPrice()) {
            total += item.getPrice();
            budget -= item.getPrice();
            cart.put(item.getName(), cart.getOrDefault(item.getName(), 0) + 1);
            println(item.getName() + " added to cart.");
        } else {
            println("Not enough budget to buy " + item.getName());
        }
    }

    Map<Item, Integer> getRandomShoppingList(Store store) {
        Map<Item, Integer> list = new LinkedHashMap<>();
        Random random = new Random();
        List<Item> allItems = store.getItems();
        int numItems = random.nextInt(5) + 1;
        while (list.size() < numItems) {
            Item item = allItems.get(random.nextInt(allItems.size()));
            int quantity = random.nextInt(3) + 1;
            if (!list.containsKey(item)) {
                list.put(item, quantity);
            }
        }
        return list;
    }

    @Override
    public String getGameName() {
        return "Shopping Game";
    }

    @Override
    public int play() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your username: ");
        username = input.nextLine();
        latch = new CountDownLatch(1); 

        PApplet.runSketch(new String[]{"ShoppingGameUI"}, this);
        
        try {
            latch.await();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return 0;

    }

    @Override
    public String getScore() {
        return String.valueOf(fulfilledShoppingList);
    }

    @Override
    public String getUsername(){
        return username;
        
    }

    @Override
    public boolean isHighScore(String score, String currentHighScore) {

        if (score != currentHighScore){
            return true;
        }

        return false;
        
    }

}

