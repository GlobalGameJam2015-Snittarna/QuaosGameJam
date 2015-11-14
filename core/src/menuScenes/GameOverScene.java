package menuScenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tomnes.dd.AssetManager;
import com.tomnes.dd.Game;
import com.tomnes.dd.framework.Rectangle;
import com.tomnes.dd.framework.Scene;
import com.tomnes.dd.gameScene.GameScene;
import com.tomnes.dd.ui.Button;

public class GameOverScene extends Scene {

	private Button restart;
	
	private int score;
	
	public GameOverScene(int score) {
		restart = new Button("Restart", new Rectangle(-100, -100, 200, 100));
		this.score = score;
	}
	
	public void update(float dt) {
		restart.update();
		
		if (restart.isPressed2()) {
			Game.setScene(new IntroScene());
		}
	}
	
	public void drawUi(SpriteBatch batch) {
		AssetManager.font.draw(batch, "Game Over!\nScore: " + score, -100, 200);
		restart.draw(batch);
	}
}
