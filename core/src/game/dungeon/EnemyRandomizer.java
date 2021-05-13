package game.dungeon;

import Entities.Enemies.EasyEnemies;
import Entities.Enemies.NormalEnemies;
import Entities.Enemies.StrongEnemies;
import Entities.Enemy;
import Entities.Player;

import java.util.ArrayList;
import java.util.List;

public class EnemyRandomizer {
    Player player;
    public EnemyRandomizer(Player player){
        this.player = player;
    }
    public List<Enemy> Randomize(){
        List<Enemy> enemyList = new ArrayList();
        int[] N = {0,0,0};
        if(player.getMaxHealth() > 50 && player.getMaxHealth() < 150){
            N = new int[]{2, 0, 0};
        }else if(player.getMaxHealth() > 150 && player.getMaxHealth() < 250){
            N = new int[]{1, 1, 0};
        }else if(player.getMaxHealth() > 250 && player.getMaxHealth() < 350){
            N = new int[]{0, 2, 1};
        }
        if(N[0] != 0){
            for (int i = 0; i < N[0]; i++){
                enemyList.add(EasyEnemies.getRandom());
            }
        }
        if(N[1] != 0){
            for (int i = 0; i < N[1]; i++){
                enemyList.add(NormalEnemies.getRandom());
            }
        }
        if(N[2] != 0){
            for (int i = 0; i < N[2]; i++){
                enemyList.add(StrongEnemies.getRandom());
            }
        }
        return enemyList;
    }
}
