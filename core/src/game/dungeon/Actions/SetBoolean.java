package game.dungeon.Actions;

import com.badlogic.gdx.scenes.scene2d.Action;

public class SetBoolean extends Action {
    Boolean bool;
    Boolean set;
    public SetBoolean(Boolean bool, Boolean set){
        this.bool = bool;
        this.set = set;
    }
    @Override
    public boolean act(float delta) {
        bool = set;
        return false;
    }
}
