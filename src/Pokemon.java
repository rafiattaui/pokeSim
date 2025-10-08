import java.util.List;

public class Pokemon {
    private final int PokedexNum;
    private final String PokemonName;
    private final List<ElementType> TypeList;
    private final double BaseCatchRate;

    public Pokemon(int PokedexNum, String PokemonName, List<ElementType> TypeList, double BaseCatchRate) {
        this.PokedexNum = PokedexNum;
        this.PokemonName = PokemonName;
        this.TypeList = TypeList;
        this.BaseCatchRate = BaseCatchRate; // float from 0 - 100
    }

    public int getPokedexNum() {return PokedexNum;}
    public String getPokemonName() {return PokemonName;}
    public List<ElementType> getTypeList() {return TypeList;}
    public double getBaseCatchRate() {return BaseCatchRate;}
}


