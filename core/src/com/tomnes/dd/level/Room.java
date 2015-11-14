package com.tomnes.dd.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tomnes.dd.AssetManager;
import com.tomnes.dd.framework.Animation;
import com.tomnes.dd.framework.GameObject;
import com.tomnes.dd.framework.Rectangle;
import com.tomnes.dd.framework.Scene;
import com.tomnes.dd.gameScene.GameScene;
import com.tomnes.dd.gameScene.objects.Enemy;
import com.tomnes.dd.gameScene.objects.Player;
import com.tomnes.dd.gameScene.objects.Powerup;
import com.tomnes.dd.gameScene.objects.Projectile;

public class Room {
	private float difficulty;
	
	private Animation sprite;
	
	private Rectangle[] doors;
	
	private int maxEnemiesToSpawn;
	
	private boolean completed;
	
	public Room() {
		sprite =  new Animation(AssetManager.getTexture("room1"));
		sprite.setSize(9, 16);
		
		sprite.setPosition(-4.5f, -8);
		
		doors = new Rectangle[4];
		
		doors[0] = new Rectangle(-4, 0, 0.3f, 1);
		doors[1] = new Rectangle(0, 7, 1, 0.3f);
		doors[2] = new Rectangle(4, 0, 0.5f, 1);
		doors[3] = new Rectangle(0, -7, 1, 0.3f);
		
		this.maxEnemiesToSpawn = (int)(this.difficulty+1)*10;
	}
	
	public void update(GameScene scene) {
		if(this.maxEnemiesToSpawn <= 0) {
			completed = true;
		}
		
		if(completed && scene.enemyCount() <= 0) {
			for(GameObject g : scene.getObjects()) {
				if(g instanceof Player) {
					for(int i = 0; i < 4; i++) {
						if(g.getHitbox().collision(doors[i])) {
							if(i == 0) {
								g.setPosition(new Vector2(3, 0));
							}
							if(i == 1) {
								g.setPosition(new Vector2(0, -5));
							}
							if(i == 2) {
								g.setPosition(new Vector2(-3, 0));
							}
							if(i == 3) {
								g.setPosition(new Vector2(0, 6));
							}
							restart(scene);
						}
					}
				}
			}
		}
	}
	
	public void restart(GameScene scene) {
		completed = false;
		difficulty += 0.3f;
		scene.getEnemySpawnManager().setNewMaxSpawnTimes(scene);
		for(GameObject g : scene.getObjects()) {
			if(g instanceof Enemy) {
				scene.removeObject(g);
			}
			
			if(g instanceof Projectile) {
				scene.removeObject(g);
			}
			
			if(g instanceof Powerup) {
				scene.removeObject(g);
			}
		}
		this.maxEnemiesToSpawn = (int)(this.difficulty+1)*10;
	}
	
	public float getDifficulty() {
		return difficulty;
	}
	
	public Vector2 tryMove(Vector2 start, Vector2 size, Vector2 delta) {
		float ws = getWallSize();
		Vector2 res = new Vector2(start.x + delta.x, start.y + delta.y);
		boolean  hit = false;
		if (res.x - size.x / 2 < -4.5f + ws) {
			res.x = -4.5f + ws + size.x / 2;
			hit = true;
		}
		if (res.x + size.x / 2 > 4.5f - ws)  {
			res.x = 4.5f - ws - size.x / 2;
			hit = true;
		}
		if (res.y - size.y / 2 < -8f + ws) {
			res.y = -8f + ws + size.y / 2;
			hit = true;
		}
		if (res.y + size.y / 2 > 8f - ws)  {
			res.y = 8f - ws - size.y / 2;
			hit = true;
		}
		
		if (hit) return res;
		else return null;
	}
	
	public void setDiffuclty(float difficulty) {
		this.difficulty = difficulty;
	}
	
	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
	}
	
	public float getWallSize() {
		return 0.36f;
	}
	
	public void setMaxEnemiesToSpawn(int maxEnemiesToSpawn) {
		this.maxEnemiesToSpawn = maxEnemiesToSpawn;
	}
	
	public void decresseEnemiesToSpawn() {
		this.maxEnemiesToSpawn -= 1;
	}
	
	public int getMaxEnemiesToSpawn() {
		return maxEnemiesToSpawn;
	}
	
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	public boolean isCompleted() {
		return completed;
	}
}
