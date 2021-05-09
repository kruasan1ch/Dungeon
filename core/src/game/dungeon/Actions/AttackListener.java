package game.dungeon.Actions;

import Entities.Enemy;
import Entities.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import game.dungeon.UI.BattleUI;

import java.util.List;

public class AttackListener{
    public AttackListener(boolean PlayersTurn,int AttackType, BattleUI bUI,Enemy enemy, Player player, List enemyList){
        if (PlayersTurn) {
            if(AttackType ==0) {
                bUI.addBattlelogLine( "You hit " + enemy.name + " with " +
                        enemy.DamageEnemy(player.first.Ammount, player.first.Type, player));
            }
            if(AttackType == 1){
                bUI.addBattlelogLine( "You hit " + enemy.name + " with " +
                        enemy.DamageEnemy(player.second.Ammount, player.second.Type, player));
            }
            AttackType = -1;
            Pixmap pm = new Pixmap(Gdx.files.internal("Cursor/Cursor.png"));
            Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
            pm.dispose();

            player.SwingAnimation.start(0.05f);

            if(enemy.health <= 0){
                bUI.addBattlelogLine("You killed " + enemy.name);
                player.killCount +=1;
                if(player.addXp(enemy.getXp())){
                    player.setHasLeveled(true);
                }
                enemy.remove();
                enemyList.remove(enemyList.get(enemyList.indexOf(enemy)));
            }
        }
    }
}
