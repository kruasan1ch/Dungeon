package Screens;

import Entities.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import game.dungeon.Damage;
import game.dungeon.Dclass;
import game.dungeon.Saver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainMenu implements Screen {
    private final Dclass game;
    private TiledMap map;
    private Stage stage;
    private final float X = Dclass.Width / (float)8;
    private final float Y = Dclass.height / (float) 5;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    public MainMenu(Dclass game){
        this.game = game;
    }
    @Override
    public void show() {
        int ButtonWidth = 168;
        int ButtonHeight = 48;
        map = new TmxMapLoader().load("Maps/MainMenu.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        camera.position.set(X,Y,0);
        camera.zoom = (float) (320 * 256)  / (Dclass.height * Dclass.Width) * (float) 4.5;
        camera.update();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("Skin/uiskin.json"));
        TextButton newGame = new TextButton("New Game", skin);
        newGame.setPosition(428,400);
        newGame.setHeight(ButtonHeight);
        newGame.setWidth(ButtonWidth);
        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new CharCreation(game));
            }
        });
        TextButton Load = new TextButton("Load",skin);
        Load.setPosition(428,330);
        Load.setHeight(ButtonHeight);
        Load.setWidth(ButtonWidth);
        Load.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                new Saver(game).load();
            }
        });

        TextButton Exit = new TextButton("Exit", skin);
        Exit.setPosition(428,194);
        Exit.setHeight(ButtonHeight);
        Exit.setWidth(ButtonWidth);
        Exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
                System.exit(0);
            }
        });
        stage.addActor(newGame);
        stage.addActor(Exit);
        stage.addActor(Load);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            System.out.printf(Gdx.input.getX() + " " + Gdx.input.getY() + "\n");
        }
        renderer.setView(camera);
        renderer.render();
        stage.act();
        stage.draw();
    }
    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
        map.dispose();
    }
}
