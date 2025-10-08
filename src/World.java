import java.util.*;

public class World {
    private static final List<Area> Areas = new ArrayList<>();

    static {
        Areas.add(new Area("Forest", List.of(Pokedex.getById(1), Pokedex.getById(19))));
    }

    public static List<Area> getAreas() {return Areas;}

    public static Area getAreaByName(String name){
        return Areas.stream()
                .filter(a -> a.getAreaName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
