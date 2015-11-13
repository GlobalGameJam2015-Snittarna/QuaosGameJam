package com.tomnes.dd.gameScene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tomnes.dd.AssetManager;
import com.tomnes.dd.framework.Scene;

public class GameScene extends Scene {
	
	public GameScene() {
		super();
		System.out.println("scene created");
	}
	
	public void drawUi(SpriteBatch batch) {
		AssetManager.font.draw(batch, "It works!", 0, 0);
		
		super.drawUi(batch);
	}
}
