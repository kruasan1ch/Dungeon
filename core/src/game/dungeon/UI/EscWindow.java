package game.dungeon.UI;

import Screens.MainMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import game.dungeon.Dclass;

public class EscWindow extends Window {
    private final Dclass game;
    public EscWindow(String title, Skin skin, float X, float Y, float Width, float Height, final Dclass game) {
        super(title, skin);
        this.setPosition(X,Y);
        this.setWidth(Width);
        this.setHeight(Height);
        this.setMovable(false);
        this.setResizable(false);
        this.setVisible(false);
        this.game = game;
        TextButton backToMain = new TextButton("Back to main menu",skin);
        backToMain.setPosition(backToMain.getWidth()/2 - 10,Height *0.9f);
        backToMain.setWidth(backToMain.getWidth() + 10);
        backToMain.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenu(game));
            }
        });
        TextButton Continue = new TextButton("Continue",skin);
        Continue.setWidth(backToMain.getWidth());
        Continue.setPosition(Continue.getWidth()/2 - 15,Height *0.85f);
        Continue.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SetVisible();

            }
        });
        TextButton Exit = new TextButton("Exit",skin);
        Exit.setWidth(backToMain.getWidth());
        Exit.setPosition(Exit.getWidth()/2 - 15,Height *0.80f);
        Exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
                System.exit(0);
            }
        });
        this.addActor(backToMain);
        this.addActor(Continue);
        this.addActor(Exit);
    }
    private void SetVisible(){
        this.setVisible(!game.Pause);
        game.Pause = !game.Pause;
    }
}
