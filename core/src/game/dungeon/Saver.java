package game.dungeon;

import Entities.Player;
import Screens.RandomScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Saver {
    private Dclass game;
    public Saver(Dclass game){
        this.game = game;
    }
    public void Save(){
        Player player = game.player;
        String save = ""+ player.getPlayerClass()+";"+(int)player.getHealth()+";"+player.getMaxHealth()+";"+
                player.first.Type+";"+(int)player.first.Ammount+";"+player.second.Type+";"+(int)player.second.Ammount+";"
                +player.name+";"+player.killCount+";"+player.xp+";"+player.xpToNextLevel+";"+player.potions;
        System.out.println(save);
        try {
            FileHandle fh = Gdx.files.internal("Save/Save.sv");
            System.out.println(fh.file().getAbsoluteFile());
            FileUtils.writeStringToFile(fh.file(), save);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void load(){
        BufferedReader reader;
        try {
            FileHandle fh = Gdx.files.internal("Save/Save.sv");
            reader = new BufferedReader(new FileReader(fh.file().getAbsoluteFile()
            ));
            String line = reader.readLine();
            String[] split = line.split(";");
            Damage first = new Damage();
            first.Type = Integer.parseInt(split[3]);
            first.Ammount = Float.parseFloat(split[4]);
            Damage second = new Damage();
            second.Type = Integer.parseInt(split[5]);
            second.Ammount = Integer.parseInt(split[6]);
            game.player = new Player(Integer.parseInt(split[0]),Float.parseFloat(split[1]),Integer.parseInt(split[2]),first,second,split[7]
                    ,Integer.parseInt(split[8]),Integer.parseInt(split[9]),Integer.parseInt(split[10]),Integer.parseInt(split[11]));
            game.setScreen(new RandomScreen(game));
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
