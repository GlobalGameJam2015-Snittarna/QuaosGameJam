package com.tomnes.dd.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tomnes.dd.AssetManager;
import com.tomnes.dd.framework.Animation;

public class Room {
	private float difficulty;
	
	private Animation sprite;
	
	private Vector2[] doors;
	
	public Room() {
		sprite =  new Animation(AssetManager.getTexture("room1"));
		sprite.setSize(9, 16);
		
		sprite.setPosition(-4.5f, -8);
		
		doors[0] = Vector2.Zero;
		doors[1] = Vector2.Zero;
		doors[2] = Vector2.Zero;
		doors[3] = Vector2.Zero;
	}
	
	public float getDifficulty() {
		return difficulty;
	}
	
	public void setDiffuclty(float difficulty) {
		this.difficulty = difficulty;
	}
	
	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
	}
}
