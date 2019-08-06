import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.gui.screens.Resolution;
import de.gurkenlabs.litiengine.resources.Resources;

public class MainLoader {
	
	public static void main (String[] args) {
		Game.init();
		
		Resources.load("resources/game.litidata");
		
		Game.window().setTitle("LITIengine Spritesheet Bug");
		Game.window().setResolution(Resolution.Ratio16x9.RES_1280x720);
		
		Game.screens().add(TestScreen.getInstance());
		
		Game.start();
	}
	
}
