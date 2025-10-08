import java.util.*;

enum GameState {
    MENU,
    EXPLORING,
    ENCOUNTER,
    CATCHING,
    INVENTORY,
    EXIT
}

enum Pokeball {
    POKEBALL("Pokeball", 1.0),
    GREATBALL("Greatball", 1.5),
    ULTRABALL("Ultraball", 2.0);

    private final double modifier;
    private final String name;
    Pokeball(String name, double modifier) {this.name = name; this.modifier = modifier;}
    public double getModifier() {return this.modifier;}
    public String getName() {return this.name;}
}

public class Game {

    private static GameState currentState =  GameState.MENU;
    private static boolean Running = true;

    // Shared state data
    private Area currentArea = new Area("Home", List.of());
    private Pokemon currentPokemon = null;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Game game = new Game();
        game.init();
    }

    public void init() {

        while (Running) {
            System.out.println();
            switch (currentState) {
                case MENU -> showMainMenu();
                case EXPLORING -> handleExploration();
                case ENCOUNTER -> handleEncounter();
                case CATCHING ->  handleCatching();
                case EXIT -> handleExit();
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("Welcome to pokeSim!");
        System.out.println("1 -> Explore an area");
        System.out.println("2 -> Open your inventory");
        System.out.println("3 -> Exit");
        System.out.println("Select an option:");

        int Choice = Integer.parseInt(scanner.nextLine());
        switch (Choice) {
            case 1 -> currentState = GameState.EXPLORING;
            case 2 -> currentState = GameState.INVENTORY;
            case 3 -> currentState = GameState.EXIT;
            default -> System.out.println("Invalid option!");
        }
    }

    private void handleExploration() {
        System.out.println("You are currently at: " + currentArea.getAreaName() + ".");
        System.out.println("Where would you like to explore: ");

        ArrayList<Area> availableAreas = new ArrayList<>(World.getAreas());
        // Display all areas with an index
        for (int i = 0; i < availableAreas.size(); i++) {
            System.out.printf("%d -> %s\n", i+1, availableAreas.get(i).toString());
        }

        System.out.println("Type the number of the area you'd like to explore:");
        int input = scanner.nextInt();
        Area selectedArea = availableAreas.get(input - 1);

        System.out.println("\nExploring the " + selectedArea.toString() + " area.");
        currentArea = selectedArea;
        Pokemon encounter = selectedArea.rollEncounter();
        currentPokemon = encounter;
        System.out.println("You encountered a wild " + encounter.getPokemonName() + "!");

        currentState = GameState.ENCOUNTER;

        // TODO - Graph-based area exploration?
    }

    private void handleEncounter(){
        System.out.println("\nWhat would you like to do?");
        System.out.println("1 -> Catch");
        System.out.println("2 -> Run Away");
        int input = scanner.nextInt();
        switch (input) {
            case 1 -> currentState = GameState.CATCHING;
            case 2 -> currentState = GameState.EXPLORING;
        }
    }

    private void handleCatching() {
        Pokeball selectedPokeball = null;
        while (selectedPokeball == null) {
            try {
                System.out.println("\nWhich Pokéball would you like to use?");
                Pokeball[] balls = Pokeball.values();

                for (int i = 0; i < balls.length; i++) {
                    System.out.printf("%d -> %s (x%.1f catch rate)\n",
                            i + 1, balls[i].getName(), balls[i].getModifier());
                }

                System.out.print("Enter number: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // clear newline

                if (choice >= 1 && choice <= balls.length) {
                    selectedPokeball = balls[choice - 1];
                } else {
                    System.out.println("Invalid number! Try again.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number!");
                scanner.nextLine(); // clear invalid input
            }
        }

        System.out.println("\nYou threw a " + selectedPokeball.getName() + "!");
        double baseChance = currentPokemon.getBaseCatchRate();
        double finalchance = Math.min(1.0, baseChance * selectedPokeball.getModifier());
        double roll = Math.random();

        try {
            for (int i = 0; i < 3; i++)
                Thread.sleep(500);
            System.out.println("...Wiggle...");
            Thread.sleep(500);
            System.out.println("...Wiggle...");
        } catch (InterruptedException e) {}

        if (roll < finalchance) {
            System.out.println("Click! You caught " + currentPokemon.getPokemonName() + "!");
            // TODO - pokedex.markCaught(currentEncounter);
            currentPokemon = null;
            currentState = GameState.EXPLORING;
        } else {
            System.out.println(currentPokemon.getPokemonName() + " broke free!");
            System.out.println("Try again? (y/n)");
            String retry = scanner.nextLine().trim();
            if (retry.equalsIgnoreCase("y")) {
                handleCatching();
            } else {
                currentState = GameState.EXPLORING;
            }
        }
    }


    private void handlePokedex(){
        // TODO - Create a separate class to track player's personal progress of the pokedex
    }

    private void handleExit() {
        System.out.println("Thank you for playing!");
        Running = false;
    }
}
