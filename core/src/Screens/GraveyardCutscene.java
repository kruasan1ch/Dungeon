package Screens;

import Entities.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import game.dungeon.Actions.ChangePlayerSprite;
import game.dungeon.Dclass;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class GraveyardCutscene implements Screen {
    private final Dclass game;
    private TiledMap map;
    private final Stage stage;
    private final Skin skin;
    private final Player player;
    public Label text;
    private TextButton goDown;

    private final float X = Dclass.Width / (float)8;
    private final float Y = Dclass.height / (float) 5;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    public GraveyardCutscene(Dclass game){
        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("Skin/uiskin.json"));
        player = game.player;
    }
    @Override
    public void show() {
        game.ResetMusicFile("Sound/Music/cutscene.mp3");
        map = new TmxMapLoader().load("Maps/Graveyard.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        camera.position.set(X,Y,0);
        camera.zoom = (float) (320 * 256)  / (Dclass.height * Dclass.Width) * (float) 4.5;
        camera.update();
        text = new Label("\nYou are entered ancient tomb",skin, "borderless");
        ScrollPane scroll = new ScrollPane(text,skin);
        scroll.setFadeScrollBars(false);
        scroll.setFlickScroll(false);
        scroll.setPosition(248,98);
        scroll.setHeight(84);
        scroll.setWidth(524);
        text.setPosition(248,98);
        text.setHeight(84);
        text.setWidth(524);
        text.setWrap(true);
        scroll.setPosition(248,66);
        goDown = new TextButton("Go down",skin);
        goDown.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                player.SetSpriteByRegion("Back");
                player.ClearActions();
                game.setScreen(new TombScreen(game));
            }
        });
        goDown.setDisabled(true);
        goDown.setVisible(false);
        goDown.setPosition(324,152);
        goDown.setWidth(128);
        goDown.setHeight(30);
        TextButton skip = new TextButton("Skip", skin);
        skip.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                player.SetSpriteByRegion("Back");
                player.ClearActions();
                game.setScreen(new TombScreen(game));
            }
        });
        skip.setPosition(250,152);
        skip.setWidth(64);
        skip.setHeight(30);
        stage.addActor(skip);
        stage.addActor(goDown);
        stage.addActor(player);
        stage.addActor(scroll);
        System.out.println(player.first.Type +  " "+player.second.Type );
        player.addAction(sequence(moveTo(485f,273f),delay(0.3f),
                moveTo(485,496f,3f),delay(0.5f),moveTo(485f,599,2f),
                new ChangePlayerSprite(game,"Left"),moveTo(406f, 599f, 1.5f)));
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(player.getX() == 406f && player.getY() == 599f){
            goDown.setVisible(true);
            goDown.setDisabled(false);
        }
        game.batch.begin();
        renderer.setView(camera);
        renderer.render();
        stage.act(delta);
        stage.draw();
        game.batch.end();
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
