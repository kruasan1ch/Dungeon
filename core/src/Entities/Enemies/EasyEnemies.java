package Entities.Enemies;

import Entities.Enemy;

import java.util.Random;

public class EasyEnemies {
   static Enemy[] enemies = {
            new Enemy("Skeleton","TexturePacks/Enemies.atlas","Skeleton", 30,
                    8, 30,new float[]{0.9f,2f,0.5f,1f}),
            new Enemy("Bat","TexturePacks/Enemies.atlas","Bat", 15,
                    5,30 ,new float[]{1.5f,0.5f,1f,2f}),
            new Enemy("Mouse","TexturePacks/Enemies.atlas","Mouse", 20,
                    5,30,new float[]{1.5f,1.3f,1f,1.1f}),
            new Enemy("Slime","TexturePacks/Enemies.atlas","Slime", 10,
                    3,15,new float[]{1.5f,1.5f,0.5f,1.5f}),
            new Enemy("Snake","TexturePacks/Enemies.atlas","Snake", 15,
                    6,25,new float[]{1.5f,1.3f,1f,1.1f})
    };
    public static Enemy getRandom(){
        return enemies[new Random().nextInt(enemies.length + 1)];
    }
}
