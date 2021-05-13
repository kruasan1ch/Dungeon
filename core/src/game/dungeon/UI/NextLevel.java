package game.dungeon.UI;

import Screens.RandomScreen;
import Screens.TombScreen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import game.dungeon.Dclass;

import java.util.Random;

public class NextLevel extends Window {
    private final Dclass game;
    public NextLevel(String title, Skin skin, float X, float Y, float Width, float Height, final Dclass game) {
        super(title, skin);
        this.setPosition(X,Y);
        this.setWidth(Width);
        this.setHeight(Height);
        this.setMovable(false);
        this.setResizable(false);
        this.setVisible(false);
        this.game = game;
        TextButton left = new TextButton("Left door",skin);
        left.setPosition(left.getWidth()/2,Height *0.9f);
        left.setWidth(left.getWidth() + 10);
        left.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Random rnd = new Random();
                int i = rnd.nextInt(100);
                if(i >= 50){
                    game.player.SetSpriteByRegion("Back");
                    game.player.ClearActions();
                    game.setScreen(new RandomScreen(game));
                }
                else{
                    //prizeLevel
                }
            }
        });
        TextButton right = new TextButton("Right door",skin);
        right.setPosition(right.getWidth()/2,Height *0.9f);
        right.setWidth(right.getWidth() + 10);
        right.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.player.SetSpriteByRegion("Back");
                game.player.ClearActions();
                game.setScreen(new RandomScreen(game));
            }
        });
        Table layout = new Table();
        layout.add(left).padRight(5).right();
        layout.add(right).padLeft(5).left();
        layout.row();
        this.add(layout);
    }
    public void SetVisible(boolean state){
        this.setVisible(state);
    }
}
