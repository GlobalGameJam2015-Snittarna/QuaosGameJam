package menuScenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tomnes.dd.Game;
import com.tomnes.dd.framework.Rectangle;
import com.tomnes.dd.framework.Scene;
import com.tomnes.dd.gameScene.GameScene;
import com.tomnes.dd.ui.Button;

public class GameOverScene extends Scene {

	private Button restart;
	
	public GameOverScene() {
		restart = new Button("Restart", new Rectangle(-100, 0, 200, 100));
	}
	
	public void update(float dt) {
		restart.update();
		
		if (restart.isPressed2()) {
			Game.setScene(new GameScene());
		}
	}
	
	public void drawUi(SpriteBatch batch) {
		restart.draw(batch);
	}
}
