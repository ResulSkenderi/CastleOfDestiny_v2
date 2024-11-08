import java.util.ArrayList;

public class Player {
    private String name;
    private int maxWeight;
    private ArrayList<Item> inventory;
    private int currentWeight;

    public Player(String name, int maxWeight) {
        this.name = name;
        this.maxWeight = maxWeight;
        this.inventory = new ArrayList<>();
        this.currentWeight = 0;
    }

    public void takeItem(Item item) {
        if (item == null) return;

        if (currentWeight + item.getWeight() <= maxWeight) {
            inventory.add(item);
            currentWeight += item.getWeight();
            System.out.println("You picked up: " + item.getName());
        } else {
            System.out.println("Item is too heavy to carry.");
        }
    }

    public void showInventory() {
        System.out.println("Inventory:");
        for (Item item : inventory) {
            System.out.println("- " + item.getName() + " (Weight: " + item.getWeight() + ")");
        }
    }
}
