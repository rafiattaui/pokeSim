import java.util.List;

public class Game {
    public static void main(String[] args) {
        System.out.println("Welcome to pokeSim!");

        Pokemon bulbasaur = new Pokemon(1, "Bulbasaur", List.of(ElementType.GRASS, ElementType.POISON), 45);
        Pokemon rattata = new Pokemon(19, "Rattata", List.of(ElementType.NORMAL), 43.9);

        Area bush = new Area("bush", List.of(bulbasaur, rattata));
        bush.rollEncounter();
    }
}
