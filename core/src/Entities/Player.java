package Entities;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.Array;
import game.dungeon.Damage;
import game.dungeon.UI.BattleUI;

import java.util.Random;

public class Player extends Actor {
    private int playerClass=0;
    TextureAtlas atlas ;
    TextureRegion region;
    Sprite sprite;
    private final float spriteScale = 2f;
    private float health = 50;
    private int maxHealth = 50;
    public Damage first;
    public Damage second;
    public String name;
    public int killCount = 0;
    public int xp = 0;
    public int xpToNextLevel = 100;
    public int potions = 5;
    public SwingAnimation SwingAnimation;
    public boolean hasLeveled = false;
    public Player(){
        atlas = new TextureAtlas("TexturePacks/Player.atlas");
        region = atlas.findRegion("Back");
        sprite = new Sprite(region);
        sprite.scale(spriteScale);
        setBounds(sprite.getX(),sprite.getY(),sprite.getWidth(),sprite.getHeight());
        setTouchable(Touchable.enabled);
        first = new Damage();
        first.Ammount = 10;
        second = new Damage();
        second.Ammount = 10;
        name ="";
        SwingAnimation = new SwingAnimation("TexturePacks/SwordAttackFront.atlas",506,340);
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setHasLeveled(boolean hasLeveled) {
        this.hasLeveled = hasLeveled;
    }

    public boolean addXp(int xp){
        if(playerClass == 0){
            this.xp += 0.9*xp;
        }else{
            this.xp += xp;
        }
        if(this.xp >= xpToNextLevel){
            xpToNextLevel *= 1.5;
            return true;
        }
        return false;
    }
    public void SetStartPosition(float startX,float startY){
        DoMoveAction(startX,startY,0.00001f);
    }
    public void DoMoveAction(float X, float Y, float duration){
        MoveToAction move = new MoveToAction();
        move.setPosition(X,Y);
        move.setDuration(duration);
        Player.this.addAction(move);
    }
    public void ClearActions(){
        Array<Action> actions = Player.this.getActions();
        if(actions.size > 0){
            for (Action action : actions){
                Player.this.removeAction(action);
            }
        }
    }
    public void UsePotion(){
        health += 1.3 * maxHealth;
        if (health >= maxHealth){
            health = maxHealth;
        }
        potions -=1;
    }
    public void DamageHealth(float damage, Enemy enemy, BattleUI bUI){
        Random rnd = new Random();
        int i = rnd.nextInt(100);
        switch(playerClass){
            case 0:
                health -= damage;
                break;
            case 1:
                health -= damage * 0.8;
                break;
            case 2:
                if(i <= 5){
                    enemy.health -= damage * 2;
                    bUI.addBattlelogLine("Critical miss! " + enemy.name + " hit itself with " + damage*2 + " damage!");
                }else if(i <= 15){
                    enemy.health -= damage;
                    bUI.addBattlelogLine(enemy.name + "missed and hit itself with " + damage + " damage!");
                }else if(i <= 30){
                    enemy.health -= damage*0.5;
                    bUI.addBattlelogLine(enemy.name + "scratched itself with " + damage*0.5 + " damage!");
                }else{
                    health -= damage;
                }
                break;
        }

    }
    public void setPlayerClass(int playerClass){
        this.playerClass = playerClass;
    }
    public int getPlayerClass(){
        return playerClass;
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }
    public void SetSpriteByRegion(String name){
        region = atlas.findRegion(name);
        sprite = new Sprite(region);
        sprite.scale(spriteScale);
    }
    @Override
    protected void positionChanged() {
        sprite.setPosition(getX(),getY());
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public float getHealth() {
        return  health;
    }

    public void setHp(int maxHealth) {
        this.health = maxHealth;
    }
}
