package com.tomnes.dd.framework;

import com.badlogic.gdx.math.Vector2;
import com.tomnes.dd.framework.Animation;

public abstract class Killable extends GameObject {
	private float health;
	private float maxHealth;
	
	private float hitTime;
	private float maxHitTime;
	
	public Killable(Vector2 position, Vector2 size, Animation sprite) {
		super(position, size, sprite);
		// TODO Auto-generated constructor stub
	}
	
	public void update(float deltaTime) {
		if(health <= 0) onDeath();
		
		if(hitTime > 0) {
			hitTime += 0.1 * deltaTime;
			if(hitTime >= maxHitTime) {
				hitTime = 0;
				getSprite().setColor(1, 1, 1, 1);
			}
		}
		
		super.update(deltaTime);
	}
	
	// Can call on this to instantly kill object
	public void onDeath() {
		getScene().removeObject(this);
	}
	
	public void onHit() {
		hitTime = 0.1f;
		getSprite().setColor(1, 0, 0, 1);
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
	
	public float getHitTime() {
		return hitTime;
	}

	public void setHitTime(float hitTime) {
		this.hitTime = hitTime;
	}

	public float getMaxHitTime() {
		return maxHitTime;
	}

	public void setMaxHitTime(float maxHitTime) {
		this.maxHitTime = maxHitTime;
	}
}
