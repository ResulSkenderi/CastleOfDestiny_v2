import java.util.HashMap;

public class Room {
    private String description;
    private HashMap<String, Room> exits;
    private Item item;
    private Character character;
    private boolean isWinningRoom;

    public Room(String description) {
        this(description, false);
    }

    public Room(String description, boolean isWinningRoom) {
        this.description = description;
        this.isWinningRoom = isWinningRoom;
        exits = new HashMap<>();
    }

    public void setExits(Room north, Room east, Room south, Room west) {
        if (north != null) exits.put("north", north);
        if (east != null) exits.put("east", east);
        if (south != null) exits.put("south", south);
        if (west != null) exits.put("west", west);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public void addItem(Item item) {
        this.item = item;
    }

    public Item getItem(String itemName) {
        if (item != null && item.getName().equalsIgnoreCase(itemName)) {
            Item temp = item;
            item = null; // Gegenstand wird aus dem Raum entfernt
            return temp;
        }
        System.out.println("Item not found in this room.");
        return null;
    }

    public void addCharacter(Character character) {
        this.character = character;
    }

    public void talkToCharacter() {
        if (character != null) {
            System.out.println(character.getDialogue());
        } else {
            System.out.println("No one to talk to here.");
        }
    }

    public String getLongDescription() {
        return "You are " + description + ".\n" + getExitString() + getItemDescription() + getCharacterDescription();
    }

    private String getExitString() {
        StringBuilder exitString = new StringBuilder("Exits:");
        for (String exit : exits.keySet()) {
            exitString.append(" ").append(exit);
        }
        return exitString.toString();
    }

    private String getItemDescription() {
        return item != null ? "\nYou see a " + item.getName() + " here." : "";
    }

    private String getCharacterDescription() {
        return character != null ? "\nYou see " + character.getName() + " here." : "";
    }

    public boolean isWinningRoom() {
        return isWinningRoom;
    }
}
