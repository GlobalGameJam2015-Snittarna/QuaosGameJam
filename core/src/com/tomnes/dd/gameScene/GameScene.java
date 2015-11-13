package com.tomnes.dd.gameScene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tomnes.dd.AssetManager;
import com.tomnes.dd.framework.Scene;
import com.tomnes.dd.gameScene.objects.Player;
import com.tomnes.dd.level.Room;

public class GameScene extends Scene {
	private Room room;
	
	public GameScene() {
		super();
		room = new Room();
		addObject(new Player());
	}
	
	public void drawUi(SpriteBatch batch) {
		AssetManager.font.draw(batch, "It works!", 0, 0);
		
		super.drawUi(batch);
	}
	
	public void draw(SpriteBatch batch) {
		room.draw(batch);
		super.draw(batch);
	}
	
	public Room getRoom() {
		return room;
	}
}
