package game.dungeon.Actions;

import Entities.Enemy;
import Entities.Player;
import com.badlogic.gdx.scenes.scene2d.Action;
import game.dungeon.UI.BattleUI;

public class EnemyAttack extends Action {
    private BattleUI bUi;
    private Player player;
    private Enemy enemy;
    public EnemyAttack(Player player, BattleUI bUi, Enemy enemy){
        this.player = player;
        this.bUi = bUi;
        this.enemy = enemy;
    }
    @Override
    public boolean act(float delta) {
        player.DamageHealth(enemy.first.Ammount, enemy, bUi);
        bUi.UpdateHpLabel();
        return true;
    }
}
