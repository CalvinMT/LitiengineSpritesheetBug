import java.awt.event.KeyEvent;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.gui.ImageComponent;
import de.gurkenlabs.litiengine.gui.ImageScaleMode;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.resources.Resources;

public class TestScreen extends Screen {
	
	private static TestScreen instance;
	
	private ImageComponent background;

	private ImageComponent num0;
	private ImageComponent num1;
	private ImageComponent num2;
	
	private int counter;
	private static final int MINIMUM = 0;
	private static final int MAXIMUM = 2;

	private static final String RED = "red";
	private static final String GREEN = "green";
	private static final String BLUE = "blue";
	
	
	
	private TestScreen () {
		super("TEST");
	}
	
	public static TestScreen getInstance () {
		if (instance == null) {
			instance = new TestScreen();
		}
		return instance;
	}
	
	
	
	@Override
	public void prepare () {
		super.prepare();
		
		this.counter = MINIMUM;
	}
	
	@Override
	public void initializeComponents () {
		double screenWidth = Game.window().getResolution().getWidth();
		double screenHeight = Game.window().getResolution().getHeight();
		
		this.background = new ImageComponent(0.0, 0.0, screenWidth, screenHeight, null, RED, Resources.spritesheets().get(RED).getImage());
		this.background.setImageScaleMode(ImageScaleMode.STRETCH);

		this.num0 = new ImageComponent(0.0, 0.0, 100.0, 100.0, null, "0", null);
		this.num1 = new ImageComponent((screenWidth / 2.0) - 50.0, 0.0, 100.0, 100.0, null, "", null);
		this.num2 = new ImageComponent(screenWidth - 100.0, 0.0, 100.0, 100.0, null, "", null);
		
		this.getComponents().add(this.background);
		this.getComponents().add(this.num0);
		this.getComponents().add(this.num1);
		this.getComponents().add(this.num2);
		
		this.initInputEvents();
	}
	
	
	
	private void initInputEvents () {
		Input.keyboard().onKeyTyped(KeyEvent.VK_LEFT, key -> {
			if (this.counter > MINIMUM) {
				this.counter--;
				this.changeBackground();
				this.updateNumber();
			}
		});
		
		Input.keyboard().onKeyTyped(KeyEvent.VK_RIGHT, key -> {
			if (this.counter < MAXIMUM) {
				this.counter++;
				this.changeBackground();
				this.updateNumber();
			}
		});
	}
	
	
	
	private void changeBackground () {
		switch (this.counter) {
			case 0:
				this.background.setText(RED);
				this.background.setImage(Resources.spritesheets().get(RED).getImage());
				break;
			case 1:
				this.background.setText(GREEN);
				this.background.setImage(Resources.spritesheets().get(GREEN).getImage());
				break;
			case 2:
				this.background.setText(BLUE);
				this.background.setImage(Resources.spritesheets().get(BLUE).getImage());
				break;
			default:
				this.counter = MINIMUM;
				this.changeBackground();
				break;
		}
	}
	
	private void updateNumber () {
		this.num0.setText("");
		this.num1.setText("");
		this.num2.setText("");
		
		switch (this.counter) {
			case 0:
				this.num0.setText("0");
				break;
			case 1:
				this.num1.setText("1");
				break;
			case 2:
				this.num2.setText("2");
				break;
			default:
				this.num0.setText(Integer.toString(this.counter));
				break;
		}
	}
	
}
