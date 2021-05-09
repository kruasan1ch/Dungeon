package Entities.Enemies;

import Entities.Enemy;

import java.util.Random;

public class NormalEnemies {
   static Enemy[] enemies = {
            new Enemy("Skeleton warrior","TexturePacks/Enemies.atlas","SkeletonWarrior", 50,
                    13, 55,new float[]{0.9f,2f,0.5f,1f}),
            new Enemy("Big bat","TexturePacks/Enemies.atlas","BatBig", 35,
                    10,55 ,new float[]{1.5f,0.5f,1f,2f}),
            new Enemy("Rat","TexturePacks/Enemies.atlas","Rat", 30,
                    8,55,new float[]{1.5f,1.3f,1f,1.1f}),
            new Enemy("Goblin","TexturePacks/Enemies.atlas","Goblin", 40,
                    8,55,new float[]{1.5f,1.5f,1f,1.5f}),
            new Enemy("Bug","TexturePacks/Enemies.atlas","Bug", 35,
                    9,55,new float[]{1f,1f,1f,1f}),
            new Enemy("SnakeStrong","TexturePacks/Enemies.atlas","SnakeStrong", 35,
                   9,55,new float[]{1.5f,1.3f,1f,1.1f}),
            new Enemy("Zombie","TexturePacks/Enemies.atlas","Zombie", 45,
                   12,55,new float[]{1.2f,1.3f,1.1f,1.2f}),
            new Enemy("Ghost","TexturePacks/Enemies.atlas","Ghost", 40,
                   10,55,new float[]{0.9f,0.9f,0.9f,0.9f})
    };
    public static Enemy getRandom(){
        return enemies[new Random().nextInt(enemies.length + 1)];
    }
}
