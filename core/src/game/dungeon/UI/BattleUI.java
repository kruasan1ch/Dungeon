package game.dungeon.UI;
import com.badlogic.gdx.scenes.scene2d.ui.*;

import game.dungeon.Dclass;

public class BattleUI extends Table {
    private Label hp,potions, charIconLabel;
    private Icon charIcon;
    private final Skin skin;
    public Label battleLog;
    public TextButton firstAttack, secondAttack, potion;
    Dclass game;
    public BattleUI(Dclass game, Skin skin){
        this.skin = skin;
        this.game=game;
        hp = new Label("HP: " + game.player.getHealth(), skin, "borderless");
        potions = new Label("Potions: " + game.player.potions,skin, "borderless");
        charIconLabel = new Label("" + game.player.name,skin, "borderless");
        charIcon = new Icon();
        switch(game.player.getPlayerClass()){
            case 0: charIcon.init("Player/Barb.PNG"); break;
            case 1: charIcon.init("Player/Knight.PNG"); break;
            case 2: charIcon.init("Player/Loser.PNG"); break;
        }
        firstAttack = new TextButton("",skin);
        secondAttack = new TextButton("",skin);
        potion = new TextButton("Heal",skin);
        switch (game.player.first.Type){
            case 0: firstAttack.setText("Swing"); break;
            case 1: firstAttack.setText("Bash"); break;
            case 2: firstAttack.setText("Thrust"); break;
            case 3: firstAttack.setText("Slash"); break;
            default: firstAttack.setText("change switch in battleUI class"); break;
        }
        switch (game.player.second.Type){
            case 0: secondAttack.setText("Swing"); break;
            case 1: secondAttack.setText("Bash"); break;
            case 2: secondAttack.setText("Thrust"); break;
            case 3: secondAttack.setText("Slash"); break;
            default: secondAttack.setText("change switch in battleUI class"); break;
        }
        charIcon.setPosition(255,75);
        battleLog = new Label("",skin,"borderless");
        ScrollPane scrollPane = new ScrollPane(battleLog,skin);
        scrollPane.setPosition(120,-75);
        scrollPane.setWidth(300);
        scrollPane.setHeight(110);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setFlickScroll(false);
        this.add(hp).left();
        this.add(firstAttack).padLeft(5).left();
        this.row();
        this.add(potions).left();
        this.add(secondAttack).padLeft(5).left();
        this.row();
        this.add(charIconLabel).left();
        this.add(potion).left().padLeft(5);
        this.addActor(charIcon);
        this.addActor(scrollPane);
        this.setPosition(355,145);
    }
    public void UpdateHpLabel(){
        this.hp.setText("HP: " + game.player.getHealth());
    }
    public void UpdatePotionLabel(){
        this.potions.setText("Potions: " + game.player.potions);
    }
}
