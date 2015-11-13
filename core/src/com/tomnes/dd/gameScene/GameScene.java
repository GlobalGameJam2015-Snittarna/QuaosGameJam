package com.tomnes.dd.gameScene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tomnes.dd.AssetManager;
import com.tomnes.dd.framework.Scene;
import com.tomnes.dd.gameScene.objects.Player;
import com.tomnes.dd.level.Room;
import com.tomnes.dd.utils.Input;

public class GameScene extends Scene {
	private Room room;
	
	public GameScene() {
		super();
		room = new Room();
		addObject(new Player());
	}
	
	public void drawUi(SpriteBatch batch) {		
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
