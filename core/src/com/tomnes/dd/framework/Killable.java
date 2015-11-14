package com.tomnes.dd.framework;

import com.badlogic.gdx.math.Vector2;
import com.tomnes.dd.framework.Animation;
import com.tomnes.dd.gameScene.objects.Enemy;
import com.tomnes.dd.gameScene.objects.Player;
import com.tomnes.dd.gameScene.objects.Projectile;

public abstract class Killable extends GameObject {
	private float health;
	private float maxHealth;
	
	private float hitTime;
	private float maxHitTime;
	private float damegeToTake;
	
	public Killable(Vector2 position, Vector2 size, Animation sprite, float maxHealth) {
		super(position, size, sprite);
		this.maxHealth = maxHealth;
		this.health = this.maxHealth;
		maxHitTime = 1;
		// TODO Auto-generated constructor stub
	}
	
	public void update(float deltaTime) {
		if(health <= 0) onDeath();
		
		for(GameObject g : getScene().getObjects()) {
			if(g instanceof Projectile) {
				damegeToTake = ((Projectile) g).getDamage();
				for (GameObject k : getScene().getObjects()) {
					if(((Projectile) g).hitsPlayer()) {
						if(k instanceof Player) { 
							if(k.getHitbox().collision(g.getHitbox())) {
								//((Player) k).setHealth(((Player) k).getHealth()-1);
								((Player) k).onHit();
								g.getScene().removeObject(g);
							}
						}
					} else {
						if(k instanceof Enemy) { 
							if(k.getHitbox().collision(g.getHitbox())) {
								((Enemy) k).onHit();
								((Projectile) g).onHit();
								g.getScene().removeObject(g);
							}
						}
					}
				}
			}
		}
		
		if(hitTime > 0) {
			hitTime += 2 * deltaTime;
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
		if (hitTime <= 0) {
			health -= this.damegeToTake;
			hitTime = 0.1f;
			getSprite().setColor(1, 0, 0, 1);
		}
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
