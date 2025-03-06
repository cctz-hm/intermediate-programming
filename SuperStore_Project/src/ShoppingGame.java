import java.util.*;

class Item {
    protected String name;
    protected double price;
    protected String brand;
    protected double[] possiblePrices;
    protected String[] possibleBrands;
    protected boolean updated;

    public Item(String name, double initialPrice, String initialBrand, double[] possiblePrices, String[] possibleBrands) {
        this.name = name;
        this.price = initialPrice;
        this.brand = initialBrand;
        this.possiblePrices = possiblePrices;
        this.possibleBrands = possibleBrands;
        this.updated = false;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    public boolean isUpdated() { 
        return updated; 
    }

    public void setUpdated(boolean state) { 
        updated = state; 
    }


    public void updatePriceAndBrand() {
    }
}

class FoodItem extends Item {
    public FoodItem(String name, double initialPrice, String initialBrand, double[] possiblePrices, String[] possibleBrands) {
        super(name, initialPrice, initialBrand, possiblePrices, possibleBrands);
    }
}

class SupplyItem extends Item {
    public SupplyItem(String name, double initialPrice, String initialBrand, double[] possiblePrices, String[] possibleBrands) {
        super(name, initialPrice, initialBrand, possiblePrices, possibleBrands);
    }

    @Override
    public void updatePriceAndBrand() {
        Random random = new Random();
        int index = random.nextInt(possibleBrands.length);
        this.brand = possibleBrands[index];
        this.price = possiblePrices[index];
        this.updated = true;
    }
}

class NonEssentialItem extends Item {
    public NonEssentialItem(String name, double initialPrice, String initialBrand, double[] possiblePrices, String[] possibleBrands) {
        super(name, initialPrice, initialBrand, possiblePrices, possibleBrands);
    }

    @Override
    public void updatePriceAndBrand() {
        Random random = new Random();
        int index = random.nextInt(possibleBrands.length);
        this.brand = possibleBrands[index];
        this.price = possiblePrices[index];
    }
}

class EssentialItem extends Item {
    public EssentialItem(String name, double initialPrice, String initialBrand, double[] possiblePrices, String[] possibleBrands) {
        super(name, initialPrice, initialBrand, possiblePrices, possibleBrands);
    }
}


class Store {
    private List<Item> items;

    public Store() {
        items = new ArrayList<>();
        items.add(new FoodItem("Berries", 7.99, "Walmart", new double[]{7.99, 8.99}, new String[]{"Walmart", "Whole foods"}));
        items.add(new FoodItem("Apple", 0.99, "Walmart", new double[]{0.99, 1.99}, new String[]{"Walmart", "Whole foods"}));
        items.add(new FoodItem("Broccoli", 1.87, "Walmart", new double[]{1.87, 3.66}, new String[]{"Walmart", "Whole foods"}));
        items.add(new FoodItem("Salmon", 9.86, "Walmart", new double[]{9.86, 16.99}, new String[]{"Walmart", "Whole foods"}));
        items.add(new FoodItem("Ice Cream", 3.99, "Walmart", new double[]{3.99, 5.99}, new String[]{"Walmart", "Whole foods"}));
        items.add(new FoodItem("Water", 3.64, "Walmart", new double[]{3.64, 5.79}, new String[]{"Walmart", "Whole foods"}));
        items.add(new SupplyItem("Shirt", 20.00, "Target", new double[]{20.00, 45.00}, new String[]{"Target", "Bloomingdale"}));
        items.add(new SupplyItem("Pants", 25.99, "Target", new double[]{25.99, 60.00}, new String[]{"Target", "Bloomingdale"}));
        //items.add(new SupplyItem("Computer", 184.99, "Target", new double[]{194.99, 299.00}, new String[]{"Target", "Bloomingdale"}));
        items.add(new SupplyItem("Books", 3.00, "Target", new double[]{3.00, 15.00}, new String[]{"Target", "Bloomingdale"}));
        //items.add(new NonEssentialItem("TV", 119.99, "Target", new double[]{119.99, 1999.00}, new String[]{"Target", "Best Buy"}));
        items.add(new NonEssentialItem("Soccer Ball", 20.00, "Target", new double[]{20.00, 45.00}, new String[]{"Target", "Bloomingdale"}));
        items.add(new EssentialItem("Toothpaste", 1.97, "Target", new double[]{1.97, 2.59}, new String[]{"Target", "Bloomingdale"}));
        items.add(new EssentialItem("Toiet Paper", 5.68, "Target", new double[]{5.68, 6.99}, new String[]{"Target", "Bloomingdale"}));
        items.add(new EssentialItem("Charger", 4.99, "Target", new double[]{4.99, 10.99}, new String[]{"Target", "Bloomingdale"}));

    }

    public List<Item> getItems() {
        return items;
    }

    public void updateRandomItem() {
        Random random = new Random();
        // Filter for only Non-Essential and Supply items
        List<Item> updatableItems = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof NonEssentialItem || item instanceof SupplyItem) {
                updatableItems.add(item);
            }
        }
        
        if (!updatableItems.isEmpty()) {
            int index = random.nextInt(updatableItems.size());
            updatableItems.get(index).updatePriceAndBrand();
        }
    }
}

public class ShoppingGame {
    public static void main(String[] args) {
        Store store = new Store();
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> cart = new HashMap<>();
        double total = 0;
        double budget = 200 + new Random().nextInt(300);
        Map<Item, Integer> requiredItems = getRandomShoppingList(store);

        System.out.println("You need to purchase the following items and quantities:");
        displayShoppingList(requiredItems);

        while (true) {
            System.out.println("\nYour budget: $" + budget);
            System.out.println("Available items to buy:");
            List<Item> items = store.getItems();
            int index = 1;
            for (Item item : items) {
                System.out.println(index++ + ". " + item.getName() + " ($" + item.getPrice() + ", " + item.getBrand() + ")");
            }
            System.out.println("0. Checkout");

            int choice = getInputChoice(scanner);

            if (choice == 0) {
                break;
            }

            Item selectedItem = items.get(choice - 1);
            if (budget >= selectedItem.getPrice()) {
                total += selectedItem.getPrice();
                budget -= selectedItem.getPrice(); // Update budget after purchase
                cart.put(selectedItem.getName(), cart.getOrDefault(selectedItem.getName(), 0) + 1);
            } else {
                System.out.println("Not enough budget to buy " + selectedItem.getName());
            }

            store.updateRandomItem(); // Randomly update items after each selection
            displayShoppingList(requiredItems); // Show the shopping list after every item selection
        }

        displayCheckoutSummary(cart, total, budget);
        scanner.close();
    }

    private static int getInputChoice(Scanner scanner) {
        System.out.print("Choose an item number to buy or 0 to checkout: ");
        while (true) {
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                if (choice >= 0 && choice <= 15) {
                    return choice;
                } else {
                    System.out.print("Invalid choice, please enter a number between 0 and " + 15 + ": ");
                }
            } else {
                scanner.next(); 
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    private static void displayCheckoutSummary(Map<String, Integer> cart, double total, double budget) {
        System.out.println("\nYou have checked out with the following items:");
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " units");
        }
        System.out.println("Total spent: $" + total);
        System.out.println("Remaining budget: $" + budget);
    }

    private static Map<Item, Integer> getRandomShoppingList(Store store) {
        Map<Item, Integer> list = new LinkedHashMap<>();
        Random random = new Random();
        List<Item> allItems = store.getItems();
        int numItems = random.nextInt(5) + 1; // randomly 1 to 5
        while (list.size() < numItems) {
            Item item = allItems.get(random.nextInt(allItems.size()));
            int quantity = random.nextInt(3) + 1; // randomly 1 to 3
            if (!list.containsKey(item)) {
                list.put(item, quantity);
            }
        }
        return list;
    }

    private static void displayShoppingList(Map<Item, Integer> requiredItems) {
        System.out.println("Shopping List:");
        for (Map.Entry<Item, Integer> entry : requiredItems.entrySet()) {
            System.out.println("- " + entry.getKey().getName() + ": " + entry.getValue() + " units needed");
        }
    }

    private static boolean checkListFulfillment(Map<Item, Integer> requiredItems, Map<String, Integer> cart) {
        for (Map.Entry<Item, Integer> entry : requiredItems.entrySet()) {
            if (!cart.containsKey(entry.getKey().getName()) || cart.get(entry.getKey().getName()) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }
}