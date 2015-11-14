package com.tomnes.dd.gameScene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tomnes.dd.AssetManager;
import com.tomnes.dd.framework.GameObject;
import com.tomnes.dd.framework.Scene;
import com.tomnes.dd.gameScene.objects.Enemy;
import com.tomnes.dd.gameScene.objects.Explosion;
import com.tomnes.dd.gameScene.objects.Player;
import com.tomnes.dd.gameScene.objects.Shooter;
import com.tomnes.dd.gameScene.objects.Spawner;
import com.tomnes.dd.gameScene.objects.SpiderBomb;
import com.tomnes.dd.level.EnemySpawnManager;
import com.tomnes.dd.level.Room;
import com.tomnes.dd.utils.Input;

public class GameScene extends Scene {
	private Room room;
	
	private EnemySpawnManager enemySpawnManager; 
	
	public GameScene() {
		super();
		room = new Room();
		addObject(new Player());
		enemySpawnManager = new EnemySpawnManager();
	}
	
	public void update(float deltaTime) {
		super.update(deltaTime);
		if(!room.isCompleted()) enemySpawnManager.update(this, deltaTime);
		room.update(this);
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
	
	public int enemyCount() {
		int count = 0;
		
		for(GameObject g : getObjects()) {
			if(g instanceof Enemy) {
				count += 1;
			}
		}
		
		return count;
	}
	
	public EnemySpawnManager getEnemySpawnManager() {
		return this.enemySpawnManager;
	}
}
