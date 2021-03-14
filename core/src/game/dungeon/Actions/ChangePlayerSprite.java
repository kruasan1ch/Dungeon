package game.dungeon.Actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import game.dungeon.Dclass;

public class ChangePlayerSprite extends Action {
    private Dclass game;
    private String region;
    public ChangePlayerSprite(Dclass game, String region){
        this.game = game;
        this.region = region;
    }
    @Override
    public boolean act(float delta) {
        game.player.SetSpriteByRegion(region);
        return true;
    }
}
