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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import game.dungeon.Dclass;
import game.dungeon.UI.BattleUI;
import game.dungeon.UI.EscWindow;
import game.dungeon.UI.NextLevel;

import java.util.Random;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class ChestScreen implements Screen {
    private final Dclass game;
    private TiledMap map;
    private final Stage stage;
    private final Skin skin;
    private final Player player;
    private final float X = Dclass.Width / (float)8;
    private final float Y = Dclass.height / (float) 5;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private Window escWindow;
    private NextLevel nextLevel;
    private Label text;
    private BattleUI bUI;
    private Chest chest;
    public ChestScreen(Dclass game){
        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("Skin/uiskin.json"));
        player = game.player;
        bUI = new BattleUI(game,skin);
    }
    @Override
    public void show() {
        map = new TmxMapLoader().load("Maps/Chest.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        camera.position.set(X,Y,0);
        escWindow = new EscWindow("",skin,X*4f - 250,Y*3f - 250,500,500,game);
        camera.zoom = (float) (320 * 256)  / (Dclass.height * Dclass.Width) * (float) 4.5;
        camera.update();
        player.SetStartPosition(506,300);
        text = new Label("You found chest!\nClick on it to get some loot!\n", skin, "borderless");
        text.setPosition(248,98);
        text.setHeight(84);
        text.setWidth(524);
        text.setWrap(true);
        nextLevel = new NextLevel("",skin,X*4f-200,Y*3f-200,400,200,game);
        chest = new Chest(504,516, "1");
        chest.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                chest.remove();
                int previos = player.potions;
                Random rnd = new Random();
                int i = rnd.nextInt(100);
                if(i <= 15){
                    player.potions += 3;
                }else if(i <= 30){
                    player.potions += 2;
                }else{
                    player.potions += 1;
                }
                text.setText(text.getText() + "You found " + (player.potions - previos) + " hp potions!");
                bUI.UpdatePotionLabel();
                nextLevel.SetVisible(true);
            }
        });
        stage.addActor(chest);
        stage.addActor(player.SwingAnimation);
        stage.addActor(player);
        stage.addActor(nextLevel);
        stage.addActor(escWindow);
        stage.addActor(text);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            escWindow.setVisible(!game.Pause);
            game.Pause = !game.Pause;
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
