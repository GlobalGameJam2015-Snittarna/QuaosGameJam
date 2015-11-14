package menuScenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tomnes.dd.AssetManager;
import com.tomnes.dd.Game;
import com.tomnes.dd.framework.Scene;
import com.tomnes.dd.gameScene.GameScene;
import com.tomnes.dd.utils.Input;

public class IntroScene extends Scene {

	String message;
	
	public IntroScene() {
		super();
		
		message = "Welcome to Dungeon Dummy!";
		message += "\n\nUse the left joystick to move";
		message += "\nAnd swipe the right one to shoot";
		message += "\n\nPick up powerups by walking on them";
		message += "\nAnd pressing the 'pick up' prompt";
		message += "\n\nSurvive as long as you can";
		message += "\n\nGood luck!";
	}
	
	public void update(float dt) {
		if (Input.wasJustPressed()) Game.setScene(new GameScene());
	}

	
	public void drawUi(SpriteBatch batch) {
		AssetManager.font.draw(batch, message, -350, 700);
	}
}
