package game.dungeon.Actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class SetVisibleAction extends Action {
    Actor actor;
    boolean setVisible;
    public SetVisibleAction(Actor actor,boolean setVisible){
        this.actor = actor;
        this.setVisible = setVisible;
    }
    @Override
    public boolean act(float delta) {
        actor.setVisible(setVisible);
        return true;
    }
}
