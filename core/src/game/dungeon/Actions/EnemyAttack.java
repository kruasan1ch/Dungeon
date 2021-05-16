package game.dungeon.Actions;

import Entities.Enemy;
import Entities.Player;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Action;
import game.dungeon.Dclass;
import game.dungeon.UI.BattleUI;

public class EnemyAttack extends Action {
    private BattleUI bUi;
    private Player player;
    private Enemy enemy;
    private Dclass game;
    public EnemyAttack(Dclass game,BattleUI bUi, Enemy enemy){
        this.player = game.player;
        this.bUi = bUi;
        this.enemy = enemy;
        this.game = game;
    }
    @Override
    public boolean act(float delta) {
        player.DamageHealth(enemy.first.Ammount, enemy, bUi, game);
        bUi.UpdateHpLabel();
        return true;
    }
}
