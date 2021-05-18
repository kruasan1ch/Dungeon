package game.dungeon;

import Entities.Player;
import Screens.MainMenu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Dclass extends Game {
	public SpriteBatch batch;
	public static int Width = 1024;
	public static int height = 768;
	public static String title = "Dungeon";
	public Player player;
	public boolean Pause = false;
	public float fxVolume = 1;
	public float musicVolume = 1;
	private Music music;
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainMenu(this));
		player = new Player();
		music = Gdx.audio.newMusic(Gdx.files.internal("Sound/Music/main menu.mp3"));
		music.setVolume(musicVolume);
		music.setLooping(true);
		music.play();
		Pixmap pm = new Pixmap(Gdx.files.internal("Cursor/Cursor.png"));
		Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
		pm.dispose();
	}
	public void ResetVolume(){
		music.setVolume(musicVolume);
	}
	public void ResetMusicFile(String path){
		music.stop();
		music = Gdx.audio.newMusic(Gdx.files.internal(path));
		music.setVolume(musicVolume);
		music.setLooping(true);
		music.play();
	}
	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
		music.dispose();
	}
}
