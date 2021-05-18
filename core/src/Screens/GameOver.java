package Screens;

import Entities.Chest;
import Entities.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import game.dungeon.Dclass;
import game.dungeon.UI.EscWindow;
import game.dungeon.UI.NextLevel;

public class GameOver implements Screen {
    private final Dclass game;
    private TiledMap map;
    private final Stage stage;
    private final Skin skin;
    private final float X = Dclass.Width / (float)8;
    private final float Y = Dclass.height / (float) 5;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    public GameOver(Dclass game){
        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("Skin/uiskin.json"));
    }
    @Override
    public void show() {
        map = new TmxMapLoader().load("Maps/GameOver.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        camera.position.set(X,Y,0);
        camera.zoom = (float) (320 * 256)  / (Dclass.height * Dclass.Width) * (float) 4.5;
        camera.update();

        Label gameOver = new Label("Game Over",skin, "borderless");
        gameOver.setPosition(455,480);
        gameOver.setHeight(80);
        gameOver.setWidth(250);
        gameOver.setWrap(true);

        Label lastWords = new Label("Any last words ?",skin, "borderless");
        lastWords.setPosition(425,400);
        lastWords.setHeight(80);
        lastWords.setWidth(250);
        lastWords.setWrap(true);

        final TextField epitaph = new TextField("",skin, "borderless");
        epitaph.setPosition(425,375);
        epitaph.setSize(160,50);

        TextButton ng = new TextButton("New Game", skin);
        ng.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.ResetMusicFile("Sound/Music/main menu.mp3");
                game.player = new Player();
                game.setScreen(new CharCreation(game));
            }
        });
        TextButton dl = new TextButton("Death chart", skin);
        dl.addListener( new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new DeathChart(game,epitaph.getText()));
            }
        });

        Table layout = new Table();
        layout.add(ng);
        layout.row();
        layout.add(dl);
        layout.setPosition(515,350);
        stage.addActor(gameOver);
        stage.addActor(lastWords);
        stage.addActor(epitaph);
        stage.addActor(layout);
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
        stage.act(delta);
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
        map.dispose();
        renderer.dispose();
    }
}
