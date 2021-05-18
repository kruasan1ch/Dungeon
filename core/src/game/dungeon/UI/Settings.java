package game.dungeon.UI;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import game.dungeon.Dclass;

public class Settings extends Window {
    private final Dclass game;
    private Slider fxSound;
    private Slider musicSound;
    public Settings(String title, Skin skin, float X, float Y, float Width, float Height, final Dclass game) {
        super(title, skin);
        this.game = game;
        this.setPosition(X,Y);
        this.setWidth(Width);
        this.setHeight(Height);
        this.setMovable(false);
        this.setResizable(false);
        this.setVisible(false);

        fxSound = new Slider(0f,1f,0.05f,false,skin);
        fxSound.setValue(game.fxVolume);
        fxSound.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.fxVolume = fxSound.getValue();
                System.out.println(fxSound.getValue());
            }
        });

        musicSound = new Slider(0f,1f,0.05f,false,skin);
        musicSound.setValue(game.musicVolume);
        musicSound.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.musicVolume = musicSound.getValue();
                game.ResetVolume();
                System.out.println(musicSound.getValue());
            }
        });

        Label fx = new Label("FX volume",skin);
        Label music = new Label("Music volume", skin);

        TextButton ok = new TextButton("ok",skin);
        ok.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Settings.this.setVisible(false);
            }
        });
        Table layout = new Table();
        layout.add(music).padLeft(5).left();
        layout.add(musicSound).padLeft(5).left();
        layout.row();
        layout.add(fx).padLeft(5).left();
        layout.add(fxSound).padLeft(5).left();
        layout.row();
        layout.add(ok).padLeft(5).left();

        Settings.this.add(layout);
    }
}
