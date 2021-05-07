package Entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import game.dungeon.Damage;

public class Enemy extends Actor {
    TextureAtlas atlas ;
    TextureRegion region;
    Sprite sprite;
    private final float spriteScale = 2f;
    public float health;
    public Damage first;
    public String name;
    private final float[] DamageMultipliers;
    public SwingAnimation SwingAnimation;
    public Enemy(String name,String atlasPath,String atlasRegion,float health,float damageAmount, float[] DamageMultipliers, int AttackX, int AttackY){
        atlas = new TextureAtlas(atlasPath);
        region = atlas.findRegion(atlasRegion);
        sprite = new Sprite(region);
        sprite.scale(spriteScale);
        setBounds(sprite.getX(),sprite.getY(),sprite.getWidth(),sprite.getHeight());
        setTouchable(Touchable.enabled);
        this.health = health;
        first = new Damage();
        first.Ammount = damageAmount;
        first.Type = 0;
        this.name =name;
        this.DamageMultipliers = DamageMultipliers;
        SwingAnimation = new SwingAnimation("TexturePacks/SwordAttackBack.atlas",AttackX,  AttackY);
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }
    @Override
    protected void positionChanged() {
        sprite.setPosition(getX(),getY());
    }

    public float DamageEnemy(float ammount, int type, Player player) {
        if(type < DamageMultipliers.length){
            float health = this.health;
            if(player.getPlayerClass() == 0){
                this.health -= (ammount * DamageMultipliers[type] + player.killCount * 0.5f);
            }
            this.health -= (ammount * DamageMultipliers[type]);
            return (health - this.health);
        }
        else {
            System.out.println("There will be index out of array exception, so fix damage type");
            return  -1;
        }
    }

}