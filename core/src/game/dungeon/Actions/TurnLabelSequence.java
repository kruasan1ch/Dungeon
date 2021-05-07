package game.dungeon.Actions;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class TurnLabelSequence {
    public TurnLabelSequence(int turnId, Label turn){
        if(turnId == 0){
            turn.setText("Players turn");
        }
        else {
            turn.setText("Enemies turn");
        }
        turn.addAction(sequence(delay(0.6f), new SetVisibleAction(turn,true),delay(2f), new SetVisibleAction(turn,false)));
    }
}
