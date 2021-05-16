package Entities;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import game.dungeon.Damage;
import game.dungeon.UI.BattleUI;

import java.util.Random;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class Chest extends Actor {
    TextureAtlas atlas ;
    TextureRegion region;
    Sprite sprite;
    private final float spriteScale = 2f;
    public Chest(int x, int y, String atlas){
        this.atlas = new TextureAtlas("TexturePacks/Chest.atlas");
        region = this.atlas.findRegion(atlas);
        sprite = new Sprite(region);
        sprite.scale(spriteScale);
        setBounds(sprite.getX(),sprite.getY(),sprite.getWidth(),sprite.getHeight());
        setTouchable(Touchable.enabled);
        setPosition(x,y);
    }
    public void SetStartPosition(float startX,float startY){
        DoMoveAction(startX,startY,0.00001f);
    }
    public void DoMoveAction(float X, float Y, float duration){
        MoveToAction move = new MoveToAction();
        move.setPosition(X,Y);
        move.setDuration(duration);
        Chest.this.addAction(move);
    }
    public void ClearActions(){
        Array<Action> actions = Chest.this.getActions();
        if(actions.size > 0){
            for (Action action : actions){
                Chest.this.removeAction(action);
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }
    public void SetSpriteByRegion(String name){
        region = atlas.findRegion(name);
        sprite = new Sprite(region);
        sprite.scale(spriteScale);
    }

    @Override
    protected void positionChanged() {
        sprite.setPosition(getX(),getY());
    }
    class ChangeSprite extends Action{
        Chest chest;
        private final String name;
        public ChangeSprite(String name){
            this.chest = Chest.this;
            this.name = name;
        }
        @Override
        public boolean act(float delta) {
            chest.SetSpriteByRegion(name);
            return true;
        }
    }
}
