package Entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import game.dungeon.Actions.SetVisibleAction;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class SwingAnimation extends Actor {
    private final TextureAtlas atlas ;
    private TextureRegion region;
    private Sprite sprite;
    private final float TargetX;
    private final float TargetY;

    public SwingAnimation(String AtlasPath,float X,float Y){
        atlas = new TextureAtlas(AtlasPath);
        region = atlas.findRegion("1");
        sprite = new Sprite(region);
        float spriteScale = 2f;
        sprite.scale(spriteScale);
        setBounds(sprite.getX(),sprite.getY(),sprite.getWidth(),sprite.getHeight());
        setTouchable(Touchable.enabled);
        TargetX = X;
        TargetY = Y;
        this.setPosition(TargetX,TargetY);
        this.setVisible(false);
    }
    private void SetSpriteByRegion(String name){
        region = atlas.findRegion(name);
        sprite.setRegion(region);
    }
    public void start(float delay){
        this.addAction(sequence(new SetVisibleAction(this,true),Actions.delay(delay)
                ,new ChangeSprite("2"),Actions.delay(delay)
                ,new ChangeSprite("3"),Actions.delay(delay)
                ,new ChangeSprite("4"),Actions.delay(delay)
                ,new ChangeSprite("5"),Actions.delay(delay)
                ,new SetVisibleAction(this,false)
        ));
    }

    @Override
    protected void positionChanged() {
        sprite.setPosition(TargetX,TargetY);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    private class ChangeSprite extends Action{
        SwingAnimation swingAnimation;
        private final String name;
        public ChangeSprite(String name){
            this.swingAnimation = SwingAnimation.this;
            this.name = name;
        }
        @Override
        public boolean act(float delta) {
            swingAnimation.SetSpriteByRegion(name);
            return true;
        }
    }
}
