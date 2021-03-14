package game.dungeon.UI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Icon extends Actor {
    private Sprite icon;
    public Icon(){

    }
    public void init(String path){
        icon = new Sprite(new Texture(path));
        setBounds(icon.getX(),icon.getY(),icon.getWidth(),icon.getHeight());
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        icon.draw(batch);
    }
    @Override
    protected void positionChanged() {
        icon.setPosition(getX(),getY());
    }
}