package game.dungeon;

import java.util.Random;

public class MapRandomizer {
    private String[] Maps ={"Maps/Tomb.tmx","Maps/Cave.tmx","Maps/Swamp.tmx"};
    public String next(){
        return Maps[new Random().nextInt(Maps.length -1)];
    }
}
