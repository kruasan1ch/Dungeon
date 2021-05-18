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
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import game.dungeon.Damage;
import game.dungeon.Dclass;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeathChart implements Screen {
    private final Dclass game;
    private TiledMap map;
    private final Stage stage;
    private final Skin skin;
    private final float X = Dclass.Width / (float)8;
    private final float Y = Dclass.height / (float) 5;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private Table layout;
    private Label items;
    public DeathChart(Dclass game, String epitaph){
        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("Skin/uiskin.json"));
        Save(epitaph);
    }
    @Override
    public void show() {
        map = new TmxMapLoader().load("Maps/DeathChart.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        camera.position.set(X,Y,0);
        camera.zoom = (float) (320 * 256)  / (Dclass.height * Dclass.Width) * (float) 4.5;
        camera.update();
        layout = new Table();
        items = new Label("",skin,"borderless");

        TextButton ng = new TextButton("New Game", skin);
        ng.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.ResetMusicFile("Sound/Music/main menu.mp3");
                game.player = new Player();
                game.setScreen(new CharCreation(game));
            }
        });
        TextButton dl = new TextButton("Main menu", skin);
        dl.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.ResetMusicFile("Sound/Music/main menu.mp3");
                game.player = new Player();
                game.setScreen(new MainMenu(game));
            }
        });
        load();
        ScrollPane scrollPane = new ScrollPane(items,skin);
        scrollPane.setPosition(250,125);
        scrollPane.setWidth(523);
        scrollPane.setHeight(600);
        layout.add(ng);
        layout.row();
        layout.add(dl);
        layout.setPosition(515,100);
        stage.addActor(layout);
        stage.addActor(scrollPane);
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
    public void Save(String epitaph){
        Player player = game.player;
        String save = ""+ player.getPlayerClass()+";"+player.name+";"+player.killCount+";"+player.xp+";"+epitaph+"\n";
        System.out.println(save);
        try {
            FileHandle fh = Gdx.files.internal("Save/Chart.sv");
            System.out.println(fh.file().getAbsoluteFile());
            FileUtils.writeStringToFile(fh.file(), save,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void load(){
        BufferedReader reader;
        try {
            FileHandle fh = Gdx.files.internal("Save/Chart.sv");
            reader = new BufferedReader(new FileReader(fh.file().getAbsoluteFile()
            ));
            String line = reader.readLine();
            List<String> lines = new ArrayList<>();
            while (line != null) {
                lines.add(line);
                // read next line
                line = reader.readLine();
            }
            for (String a: lines) {
                String[] split = a.split(";");
                String playerClass = "";
                items.setText(items.getText() + "Name: " + split[1]+" Class: " + playerClass + " Exp: "
                        + split[3] + " Kills: "+split[2] +"\n"+ split[4] + "\n");

            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
