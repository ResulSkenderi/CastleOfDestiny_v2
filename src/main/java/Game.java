import java.util.Scanner;

public class Game {
    private Room currentRoom;
    private Player player;

    public Game() {
        player = new Player("Hero", 10);
        createRooms();
    }

    private void createRooms() {
        Room entranceHall = new Room("in the Entrance Hall of the castle");
        Room library = new Room("in a grand Library filled with ancient books");
        Room throneRoom = new Room("in the majestic Throne Room", true);
        Room dungeon = new Room("in a dark, damp Dungeon");
        Room armory = new Room("in the Armory, full of ancient weapons");

        // Raumübergänge festlegen
        entranceHall.setExits(null, library, dungeon, armory);
        library.setExits(null, null, throneRoom, entranceHall);
        throneRoom.setExits(null, null, null, library);
        dungeon.setExits(entranceHall, null, null, null);
        armory.setExits(null, entranceHall, null, null);

        // Gegenstände und Charaktere hinzufügen
        library.addItem(new Item("Magic Book", 2));
        armory.addItem(new Item("Sword", 3));
        dungeon.addCharacter(new Character("Mysterious Stranger", "Be careful in the throne room..."));

        currentRoom = entranceHall;
    }

    public void play() {
        System.out.println("Welcome to CastleOfDestiny!");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());

        Scanner scanner = new Scanner(System.in);
        boolean finished = false;

        while (!finished) {
            System.out.print("> "); // Eingabeaufforderung
            String command = scanner.nextLine();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing. Goodbye!");
        scanner.close();
    }

    private boolean processCommand(String command) {
        if (command.equals("help")) {
            printHelp();
        } else if (command.startsWith("go")) {
            goRoom(command.substring(3));
        } else if (command.startsWith("take")) {
            player.takeItem(currentRoom.getItem(command.substring(5)));
        } else if (command.startsWith("talk")) {
            currentRoom.talkToCharacter();
        } else if (command.equals("inventory")) {
            player.showInventory();
        } else if (command.equals("quit")) {
            return true;
        } else {
            System.out.println("I don't understand that command.");
        }
        return false;
    }

    private void printHelp() {
        System.out.println("Your command words are: go [direction], take [item], talk, inventory, quit");
    }

    private void goRoom(String direction) {
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            if (currentRoom.isWinningRoom()) {
                System.out.println("Congratulations! You've reached the Throne Room and won the game!");
                System.exit(0);
            }
        }
    }
}
