package com.tomnes.dd.level;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.tomnes.dd.framework.Scene;
import com.tomnes.dd.gameScene.GameScene;
import com.tomnes.dd.gameScene.objects.Shooter;
import com.tomnes.dd.gameScene.objects.Spawner;
import com.tomnes.dd.gameScene.objects.SpiderBomb;

public class EnemySpawnManager {
	enum EnemyTypes { 
		RUNNER(0), SHOOTER(1), SPAWNER(2);
		private int value; 
		
		EnemyTypes(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	};
	
	final int DOOR_LEFT = 0;
	final int DOOR_UP = 1;
	final int DOOR_RIGHT = 2;
	final int DOOR_DOWN = 3;
	
	private float maxEnemySpawnTime[];
	private float enemySpawnTime[];
	
	Random random;
	
	public EnemySpawnManager() {
		maxEnemySpawnTime = new float[3];
		enemySpawnTime = new float[3];
		
		for(int i = 0; i < maxEnemySpawnTime.length; i++) {
			maxEnemySpawnTime[i] = 4+i;
		}
	}
	
	public void setNewMaxSpawnTimes(GameScene gameScene) {
		for(int i = 0; i < maxEnemySpawnTime.length; i++) {
			maxEnemySpawnTime[i] = (4+i-gameScene.getRoom().getDifficulty() >= 0) ? 2+i-gameScene.getRoom().getDifficulty() : 0.1f;
		}
	}
	
	public void update(GameScene gameScene, float deltaTime) {
		random = new Random();
		
		for(int i = 0; i < maxEnemySpawnTime.length; i++) {
			enemySpawnTime[i] += 1 * deltaTime;
		
			if(enemySpawnTime[i] >= maxEnemySpawnTime[i]) {
				if(gameScene.getRoom().getDifficulty() >= 2 && i > 0) {
					//gameScene.getRoom().decresseEnemiesToSpawn();
					if(i == 1) {
						gameScene.addObject(new Shooter(getSpawnPosition(random.nextInt(4))));
					}
					if(i == 2) {
						gameScene.addObject(new Spawner(getSpawnPosition(random.nextInt(4))));
					}
				}
				if(i <= 0) {
					gameScene.addObject(new SpiderBomb(getSpawnPosition(random.nextInt(4))));
					gameScene.getRoom().decresseEnemiesToSpawn();
				}
				enemySpawnTime[i] = 0;
			}
		}
	}
	
	public Vector2 getSpawnPosition(int side) {
		Vector2 position;
		
		if(side == DOOR_LEFT) {
			position = new Vector2(-5, 0);
		} else if(side == DOOR_RIGHT) {
			position = new Vector2(5, 0);
		} else if(side == DOOR_UP) {
			position = new Vector2(0, 8);
		} else if(side == DOOR_DOWN) {
			position = new Vector2(0, -8);
		} else {
			position = new Vector2(0, 0);
		}
		
		return position;
	}
}
