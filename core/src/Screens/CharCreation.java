package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import game.dungeon.Dclass;

public class CharCreation implements Screen {
    private final Dclass game;
    private TiledMap map;
    private final Stage stage;
    private final Skin skin;
    private TextField name;
    private int playerClass=0;
    private Label text;
    private final float X = Dclass.Width / (float)8;
    private final float Y = Dclass.height / (float) 5;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    public CharCreation(Dclass game){
        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("Skin/uiskin.json"));
    }
    @Override
    public void show() {
        map = new TmxMapLoader().load("Maps/CharCreation.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        camera.position.set(X,Y,0);

        camera.zoom = (float) (320 * 256)  / (Dclass.height * Dclass.Width) * (float) 4.5;
        camera.update();
        ImageButton Barb = new ImageButton(new SpriteDrawable(new Sprite(new Texture("ImageButtons/Barb.PNG"))));
        Barb.setPosition(309,502);
        Barb.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                text.setText("The barbarian\n\"Once he was chief of northern tribe. Now he is a vessel of powerful ghost.\n" +
                        "More he kill - more power he gets\"\n" +
                        "\nYour kill count increases damage and health, but since barbarian wasn't college professor you gain less experience");
                playerClass = 0;
            }
        });
        ImageButton Knight = new ImageButton(new SpriteDrawable(new Sprite(new Texture("ImageButtons/Knight.PNG"))));
        Knight.setPosition(463,502);
        Knight.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                text.setText("The Knight\n\"The son of an impoverished nobleman. He was sent to the royal army and now investigating" +
                        "necromancers cult. Though he is an officer - he just an average fighter\"\n" +
                        "Normal experience rate, no special traits.");
                playerClass = 1;
            }
        });
        ImageButton Loser = new ImageButton(new SpriteDrawable(new Sprite(new Texture("ImageButtons/Loser.PNG"))));
        Loser.setPosition(616,502);
        Loser.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                text.setText("The loser\n\"Merely commoner, son of the blacksmith, he was cursed cause of his attitude." +
                        " His aunt told him that he can find a magical scroll in ancient ruins. That scroll can purge his curse\"\n" +
                        "Your special feature - luck or its lack. Not only you can damage yourself while wielding sword." +
                        " Every enemy on your way can destroy themselves");
                playerClass = 2;
            }
        });
        Label tip = new Label("Click on class icon\nto choose class",skin, "borderless");
        tip.setPosition(420,656);
        stage.addActor(tip);
        name = new TextField("",skin, "borderless");
        name.setPosition(317,342);
        name.setSize(200,46);
        name.setMaxLength(7);
        final TextButton back = new TextButton("Back", skin);
        back.setHeight(48);
        back.setWidth(98);
        back.setPosition(309,656);
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenu(game));
            }
        });
        TextButton start = new TextButton("Start", skin);
        start.setHeight(48);
        start.setWidth(116);
        start.setPosition(548,332);
        start.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(name.getText().length() ==0){
                    game.player.name = "Am Shegar";
                }else{
                    game.player.name = name.getText();
                }
                switch (playerClass){
                    case 0: game.player.first.Type = 3; game.player.second.Type = 1; break;
                    case 1: game.player.first.Type = 0; game.player.second.Type = 2; break;
                    case 2: game.player.first.Type = 0; game.player.second.Type = 3; break;
                }
                game.player.setPlayerClass(playerClass);
                game.setScreen(new GraveyardCutscene(game));
            }
        });
        text = new Label("",skin, "borderless");
        ScrollPane scroll = new ScrollPane(text,skin);
        scroll.setPosition(248,98);
        scroll.setHeight(150);
        scroll.setWidth(422);
        scroll.setFadeScrollBars(false);
        scroll.setFlickScroll(false);
        text.setPosition(318,101);
        text.setHeight(150);
        text.setWidth(422);
        text.setWrap(true);
        scroll.setPosition(318,101);
        stage.addActor(scroll);
        stage.addActor(Barb);
        stage.addActor(Knight);
        stage.addActor(Loser);
        stage.addActor(name);
        stage.addActor(back);
        stage.addActor(start);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
