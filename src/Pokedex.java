import java.util.*;


// Pokedex stores the different pokemon species, a new class will be made later to store individual Pokemon caught.
// TODO - Implement a player inventory class to store individual caught pokemon
public class Pokedex {
    private static final Map<Integer, Pokemon> PokedexById = new HashMap<>();
    private static final Map<String, Pokemon> PokedexByName = new HashMap<>();

    static {
        register(new Pokemon(1, "Bulbasaur", List.of(ElementType.GRASS,
                ElementType.POISON), 0.45));

        register(new Pokemon(19, "Ratatta", List.of(ElementType.NORMAL), 0.5));
    }

    private static void register(Pokemon pokemon){
        PokedexById.put(pokemon.getPokedexNum(), pokemon);
        PokedexByName.put(pokemon.getPokemonName().toLowerCase(), pokemon);
    }

    public static Pokemon getById(int id){
        return PokedexById.get(id);
    }

    public static Pokemon getByName(String name){
        return PokedexByName.get(name.toLowerCase());
    }

    public static Collection<Pokemon> getAll(){
        return PokedexById.values();
    }
}
