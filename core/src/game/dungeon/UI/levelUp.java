package game.dungeon.UI;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import game.dungeon.Dclass;

public class levelUp extends Window {
    private final Dclass game;
    public levelUp(String title, Skin skin, float X, float Y, float Width, float Height, final Dclass game, final BattleUI bUI) {
        super(title, skin);
        this.setPosition(X, Y);
        this.setWidth(Width);
        this.setHeight(Height);
        this.setMovable(false);
        this.setResizable(false);
        this.setVisible(false);
        this.game = game;
        TextButton hp = new TextButton(" + ", skin);
        hp.setPosition(hp.getWidth() / 2, Height * 0.9f);
        hp.setWidth(hp.getWidth() + 10);
        hp.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                game.player.setMaxHealth((int)(game.player.getMaxHealth() * 1.5));
                game.player.setHp(game.player.getMaxHealth());
                game.player.setHasLeveled(false);
                levelUp.super.setVisible(false);
                bUI.addBattlelogLine("Max hp leveled " + game.player.getMaxHealth());
                bUI.UpdateHpLabel();
            }
        });

        TextButton dmgFirst = new TextButton(" + ", skin);
        dmgFirst.setPosition(dmgFirst.getWidth() / 2, Height * 0.9f);
        dmgFirst.setWidth(dmgFirst.getWidth() + 10);
        dmgFirst.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.player.first.Ammount *= 1.25;
                game.player.setHasLeveled(false);
                levelUp.super.setVisible(false);
                bUI.addBattlelogLine("First attack leveled " + game.player.first.Ammount);
            }
        });
        TextButton dmgSecond = new TextButton(" + ", skin);
        dmgSecond.setPosition(dmgSecond.getWidth() / 2, Height * 0.9f);
        dmgSecond.setWidth(dmgSecond.getWidth() + 10);
        dmgSecond.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.player.second.Ammount *= 1.25;
                game.player.setHasLeveled(false);
                levelUp.super.setVisible(false);
                bUI.addBattlelogLine("Second attack leveled " + game.player.second.Ammount);
            }
        });
        Table layout = new Table();
        layout.add(new Label("hp: " + game.player.getMaxHealth(),skin)).padLeft(5).left();
        layout.add(hp).padLeft(5).left();
        layout.row();
        String first = "";
        String second = "";
        switch(game.player.getPlayerClass()){
            case 0:
                first = "Slash ";
                second = "Bash ";
                break;
            case 1:
                first = "Swing ";
                second = "Thrust ";
                break;
            case 2:
                first = "Swing ";
                second = "Slash ";
                break;
        }
        layout.add(new Label(first + game.player.first.Ammount, skin)).padLeft(5).left();
        layout.add(dmgFirst).padLeft(5).left();
        layout.row();
        layout.add(new Label(second + game.player.second.Ammount, skin)).padLeft(5).left();
        layout.add(dmgSecond).padLeft(5).left();
        this.add(layout);
    }
}
