package com.tomnes.dd.framework;

import com.badlogic.gdx.math.Vector2;
import com.tomnes.dd.framework.Animation;

public abstract class Killable extends GameObject {
	private float health;
	private float maxHealth;
	
	public Killable(Vector2 position, Vector2 size, Animation sprite) {
		super(position, size, sprite);
		// TODO Auto-generated constructor stub
	}
	
	public void update(float deltaTime) {
		if(health <= 0) onDeath();
		
		super.update(deltaTime);
	}
	
	// Can call on this to instantly kill object
	public void onDeath() {
		getScene().removeObject(this);
	}
	
	public float getHealth() {
		return health;
	}
	
	public float getMaxHealth() {
		return maxHealth;
	}
	
	public void setHealth(float health) {
		this.health = health;
	}
	
	public void setMaxHealth(float maxHealth) {
		this.maxHealth = maxHealth;
	}
}
