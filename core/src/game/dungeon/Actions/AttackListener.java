package game.dungeon.Actions;

import Entities.Enemy;
import Entities.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import game.dungeon.UI.BattleUI;

import java.util.List;

public class AttackListener{
    public AttackListener(boolean PlayersTurn,int AttackType, BattleUI bUI,Enemy enemy, Player player, List enemyList, Label turn){
        if (PlayersTurn) {
            if(AttackType ==0) {
                bUI.addBattlelogLine( "You hit " + enemy.name + " with " +
                        enemy.DamageEnemy(player.first.Ammount, player.first.Type, player) + "\n");
            }
            if(AttackType == 1){
                bUI.addBattlelogLine( "You hit " + enemy.name + " with " +
                        enemy.DamageEnemy(player.second.Ammount, player.second.Type, player) + "\n");
            }
            AttackType = -1;
            Pixmap pm = new Pixmap(Gdx.files.internal("Cursor/Cursor.png"));
            Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
            pm.dispose();

            player.SwingAnimation.start(0.05f);

            if(enemy.health <= 0){
                bUI.battleLog.setText(bUI.battleLog.getText() + "You killed " + enemy.name + "\n");
                player.killCount +=1;
                enemy.remove();
                enemyList.remove(enemyList.get(enemyList.indexOf(enemy)));
            }
        }
    }
}
