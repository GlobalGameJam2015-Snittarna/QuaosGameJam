package com.tomnes.dd.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tomnes.dd.AssetManager;
import com.tomnes.dd.framework.Animation;

public class Room {
	private float difficulty;
	
	private Animation sprite;
	
	private Vector2[] doors;
	
	private int maxEnemiesToSpawn;
	
	private boolean completed;
	
	public Room() {
		sprite =  new Animation(AssetManager.getTexture("room1"));
		sprite.setSize(9, 16);
		
		sprite.setPosition(-4.5f, -8);
		
		doors = new Vector2[4];
		
		doors[0] = Vector2.Zero;
		doors[1] = Vector2.Zero;
		doors[2] = Vector2.Zero;
		doors[3] = Vector2.Zero;
		
		this.maxEnemiesToSpawn = (int)(this.difficulty+1)*10;
	}
	
	public void update() {
		System.out.println(maxEnemiesToSpawn);
		if(this.maxEnemiesToSpawn <= 0) {
			completed = true;
		}
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
