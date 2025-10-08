import java.util.List;
import java.util.Random;

public class Area {
    private final String AreaName;
    private final List<Pokemon> PossiblePokemon;
    private final Random Random = new Random();

    public Area(String AreaName, List<Pokemon> possiblePokemon){
        this.AreaName = AreaName;
        this.PossiblePokemon = possiblePokemon;
    }

    public String getAreaName() {return AreaName;}
    public Pokemon rollEncounter(){
        Pokemon caught = PossiblePokemon.get(Random.nextInt(PossiblePokemon.size()));
        System.out.println("You caught: " + caught.getPokemonName() + "!");
        return caught;
    }
}
