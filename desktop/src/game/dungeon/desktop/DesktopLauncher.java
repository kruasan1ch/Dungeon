package game.dungeon.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import game.dungeon.Dclass;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Dclass.Width;
		config.height = Dclass.height;
		config.title = Dclass.title;
		config.resizable = false;
		new LwjglApplication(new Dclass(), config);
	}
}
