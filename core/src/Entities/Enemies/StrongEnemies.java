package Entities.Enemies;

import Entities.Enemy;

import java.util.Random;

public class StrongEnemies {
   static Enemy[] enemies = {
            new Enemy("Lich","TexturePacks/Enemies.atlas","Lich", 70,
                    20,100 ,new float[]{1.5f,1.5f,1f,0.5f}),
           new Enemy("Goblin chief","TexturePacks/Enemies.atlas","GoblinChief", 75,
                   18,100,new float[]{1.5f,1.5f,1f,1.5f}),
            new Enemy("Goblin warrior","TexturePacks/Enemies.atlas","GoblinWarrior", 65,
                    16,100,new float[]{1.5f,1.5f,1f,1.5f}),
            new Enemy("Fire bug","TexturePacks/Enemies.atlas","FireBug", 65,
                    18,100,new float[]{0.9f,0.9f,0.9f,0.9f}),
            new Enemy("Strong zombie","TexturePacks/Enemies.atlas","ZombieStrong", 60,
                   15,100,new float[]{1.2f,1.3f,1.1f,1.2f}),
            new Enemy("Powerful ghost","TexturePacks/Enemies.atlas","GhostPowerful", 50,
                   18,100,new float[]{0.7f,0.7f,0.7f,0.7f})
    };
    public static Enemy getRandom(){
        return enemies[new Random().nextInt(enemies.length + 1)];
    }
}
