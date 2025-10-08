import java.util.Scanner;

enum GameState {
    MENU,
    EXPLORING,
    ENCOUNTER,
    CATCHING,
    INVENTORY,
    EXIT
}

public class Game {

    private static GameState CurrentState =  GameState.MENU;
    private static boolean Running = true;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (Running) {
            System.out.println();
            switch (CurrentState) {
                case MENU -> showMainMenu();
                case EXPLORING -> exploreArea();
                case EXIT -> exitGame();
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("Welcome to pokeSim!");
        System.out.println("1. Explore an area");
        System.out.println("2. Exit");
        System.out.println("Select an option:");

        int Choice = Integer.parseInt(scanner.nextLine());
        switch (Choice) {
            case 1 -> CurrentState = GameState.EXPLORING;
            case 2 -> CurrentState = GameState.EXIT;
            default -> System.out.println("Invalid option!");
        }
    }

    private static void exploreArea() {
        System.out.println("Where would you like to explore: ");
        for (Area area : World.getAreas()) {
            System.out.println(area.toString());
        }
        System.out.println("Type the name of the area you would like to explore!");

        // TODO - Implement selecting area to explore via name or index
        // TODO - Graph-based area exploration?
        Area forest = World.getAreaByName("Forest");
        Pokemon encounter = forest.rollEncounter();
        System.out.println("Encountered a wild " + encounter.getPokemonName() + "!");
        CurrentState = GameState.EXIT;
    }

    private static void exitGame() {
        System.out.println("Thank you for playing!");
        Running = false;
    }
}
