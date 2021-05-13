package game.dungeon;

import com.badlogic.gdx.math.Vector2;

public class EnemyPosition {
    private Vector2[] Positions = {
            new Vector2(510,600),
            new Vector2(350, 600),
            new Vector2(570, 600)
    };
    private int currentPosition = 0;
    public Vector2 next(){
        Vector2 result = new Vector2();
        try{
            result = Positions[currentPosition];
        } catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        currentPosition++;
        return result;
    }
}
