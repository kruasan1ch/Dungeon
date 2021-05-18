package game.dungeon;
import Entities.Enemy;
import Entities.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EnemyRandomizer {
    Player player;
    ArrayList<Enemy> enemyList = new ArrayList<>();
    Enemy[] easyEnemies = {
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
    Enemy[] normalEnemies = {
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
    Enemy[] strongEnemies = {
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
    public EnemyRandomizer(Player player){
        this.player = player;
    }
    public ArrayList<Enemy> Randomize(){
        int[] N = {0,0,0};
        if(player.getMaxHealth() >= 50 && player.getMaxHealth() <= 80){
            N = new int[]{2, 0, 0};
        }else if(player.getMaxHealth() >= 80 && player.getMaxHealth() <= 150){
            N = new int[]{1, 1, 0};
        }else if(player.getMaxHealth() >= 150){
            N = new int[]{1, 1, 1};
        }
        System.out.println(Arrays.toString(N));
        if(N[0] != 0){
            for (int i = 0; i < N[0]; i++){
                Enemy enemy = getRandomEasy();
                setEnemy(enemy);
            }
        }
        if(N[1] != 0){
            for (int i = 0; i < N[1]; i++){
                Enemy enemy = getRandomNormal();
                setEnemy(enemy);
            }
        }
        if(N[2] != 0){
            for (int i = 0; i < N[2]; i++){
                Enemy enemy = getRandomStrong();
                setEnemy(enemy);
            }
        }
        return enemyList;
    }
    public  Enemy getRandomEasy(){
        return easyEnemies[new Random().nextInt(easyEnemies.length)];
    }
    public  Enemy getRandomNormal(){
        return normalEnemies[new Random().nextInt(normalEnemies.length + 1)];
    }
    public  Enemy getRandomStrong(){
        return strongEnemies[new Random().nextInt(strongEnemies.length + 1)];
    }
    public void setEnemy(Enemy enemy){
        System.out.println(enemy.name);
        System.out.println(Arrays.toString(easyEnemies));
        List<String> names = new ArrayList<>();
        for(Enemy e : enemyList){
            names.add(e.name);
        }
        if (!names.contains(enemy.name)){
            enemyList.add(enemy);
        }
        else{
            System.out.println("fuck");
        }
    }
}
