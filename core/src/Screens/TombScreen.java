package Screens;

import Entities.Enemy;
import Entities.Player;
import Entities.SwingAnimation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
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
import game.dungeon.Actions.EnemyAttack;
import game.dungeon.Dclass;
import game.dungeon.Actions.SetVisibleAction;
import game.dungeon.UI.BattleUI;
import game.dungeon.UI.EscWindow;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class TombScreen implements Screen {
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
    private BattleUI bUI;
    private boolean PlayersTurn = true;
    private byte AttackType = -1;
    private Label turn;
    private SwingAnimation swingAnimation;
    private List<Enemy> enemyList = new ArrayList();
    public TombScreen(Dclass game){
        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("Skin/uiskin.json"));
        player = game.player;
        bUI = new BattleUI(game,skin);
    }
    @Override
    public void show() {
        map = new TmxMapLoader().load("Maps/Tomb.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        camera.position.set(X,Y,0);
        escWindow = new EscWindow("",skin,X*4f - 250,Y*3f - 250,500,500,game);
        camera.zoom = (float) (320 * 256)  / (Dclass.height * Dclass.Width) * (float) 4.5;
        camera.update();
        player.SetStartPosition(506,300);

        final Enemy enemy = new Enemy("Skeleton","TexturePacks/Enemies.atlas","Skeleton", 30,15,new float[]{0.9f,2f,0.5f,1f});
        enemy.setPosition(510,600);
        bUI.firstAttack.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Pixmap pm = new Pixmap(Gdx.files.internal("Cursor/CursorBattle.png"));
                Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
                pm.dispose();
                AttackType = 0;
            }
        });
        bUI.secondAttack.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Pixmap pm = new Pixmap(Gdx.files.internal("Cursor/CursorBattle.png"));
                Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
                pm.dispose();
                AttackType = 1;
            }
        });
        bUI.potion.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(PlayersTurn){
                    if(AttackType >= 0){
                        Pixmap pm = new Pixmap(Gdx.files.internal("Cursor/Cursor.png"));
                        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
                        pm.dispose();
                    }
                    if (player.potions >0)
                    {
                        player.UsePotion();
                        AttackType = -1;
                        bUI.UpdatePotionLabel();
                        bUI.UpdateHpLabel();
                        PlayersTurn = false;
                    }
                }
            }
        });
        enemy.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(PlayersTurn){
                    if(AttackType ==0) {
                        bUI.battleLog.setText(bUI.battleLog.getText() + "You hit " + enemy.name + " with " +
                                enemy.DamageEnemy(player.first.Ammount, player.first.Type, player) + "\n");
                    }
                    if(AttackType == 1){
                        bUI.battleLog.setText(bUI.battleLog.getText() + "You hit " + enemy.name + " with " +
                                enemy.DamageEnemy(player.second.Ammount, player.second.Type, player) + "\n");
                    }
                    AttackType = -1;
                    Pixmap pm = new Pixmap(Gdx.files.internal("Cursor/Cursor.png"));
                    Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
                    pm.dispose();

                    swingAnimation.start(0.05f);

                    if(enemy.health <= 0){
                        bUI.battleLog.setText(bUI.battleLog.getText() + "You killed " + enemy.name + "\n");
                        player.killCount +=1;
                        enemy.remove();
                    }else{
                        PlayersTurn = false;
                        turnLabelSequence(1);
                    }

                }
            }
        });
        swingAnimation = new SwingAnimation("TexturePacks/SwordAttackFront.atlas",506,340);
        stage.addActor(swingAnimation);
        enemyList.add(enemy);
        turn = new Label("Players Turn",skin);
        turn.setVisible(false);
        turn.addAction(sequence(delay(0.5f), new SetVisibleAction(turn,true),delay(2f), new SetVisibleAction(turn,false)));
        turn.setPosition(510 - turn.getWidth()/2,500);
        stage.addActor(turn);
        stage.addActor(enemy);
        stage.addActor(bUI);
        stage.addActor(player);
        stage.addActor(escWindow);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            escWindow.setVisible(!game.Pause);

            game.Pause = !game.Pause;
        }
        if(!PlayersTurn){
            EnemyAction();
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
    private void turnLabelSequence(int turnId){
        if(turnId == 0){
            turn.setText("Players turn");
        }
        else {
            turn.setText("Enemies turn");
        }
        turn.addAction(sequence(delay(0.3f), new SetVisibleAction(turn,true),delay(2f), new SetVisibleAction(turn,false)));
    }
    private void EnemyAction(){

        for (int i = 0; i < enemyList.size(); i++) {
            enemyList.get(i).addAction(sequence(delay(2f),new EnemyAttack(player,bUI,enemyList.get(i))));
            if(i == enemyList.size() -1){
                PlayersTurn = true;
                turnLabelSequence(0);
            }
        }
    }
}