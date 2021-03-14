package game.dungeon;

import Entities.Player;
import Screens.MainMenu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainMenu(this));
		player = new Player();
		Pixmap pm = new Pixmap(Gdx.files.internal("Cursor/Cursor.png"));
		Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
		pm.dispose();
	}

	@Override
	public void render () {
		super.render();
	}
}
