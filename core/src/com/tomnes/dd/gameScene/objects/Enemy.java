package com.tomnes.dd.gameScene.objects;

import com.badlogic.gdx.math.Vector2;
import com.tomnes.dd.framework.Animation;
import com.tomnes.dd.framework.GameObject;
import com.tomnes.dd.framework.Killable;

public abstract class Enemy extends Killable {
	private float shootAngle;
	private float movmentAngle;
	
	private float speed;
	
	private float fireRateTime;
	private float maxFireRateTime; 
	
	private Vector2 velocity;

	private Vector2 target;
	
	private boolean inRoom;
	
	public Enemy(Vector2 position, Vector2 size, Animation sprite) {
		super(position, size, sprite);
		
		if(getPosition().x >= 4.5f) {
			target = new Vector2(this.getPosition().x-3, this.getPosition().y);
		}
		else if(getPosition().x <= -4.5f) {
			target = new Vector2(this.getPosition().x+3, this.getPosition().y);
		}
		else if(getPosition().y >= 8) {
			target = new Vector2(this.getPosition().x, this.getPosition().y-3);
		}
		else if(getPosition().y <= -8) {
			target = new Vector2(this.getPosition().x, this.getPosition().y+3);
		}
		else {
			inRoom = true;
		}
	}
	
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		velocity = new Vector2((float)Math.cos(movmentAngle)*speed, (float)Math.sin(movmentAngle)*speed);
		
		attack(deltaTime);
		
		if(!inRoom) {
			this.moveTowardsTarget(deltaTime);
			System.out.println(this.getPosition());
			if(this.getPosition().sub(target).len() <= 0.1f) {
				inRoom = true;
			}
		}
	}
	
	public void attack(float deltaTime) {
		for(GameObject g : getScene().getObjects()) {
			if (g instanceof Player) {
				shootAngle = (float)Math.atan2(g.getPosition().y-getPosition().y, g.getPosition().x-getPosition().x);
			}
		}
	}
	
	public void shoot(float deltaTime) {
		if(inRoom) fireRateTime += 0.1f * deltaTime;
		
		if(fireRateTime >= maxFireRateTime) {
			onShoot();
			fireRateTime = 0;
		}
	}
	
	public void onShoot() {
		
	}
	
	public void moveTowardsPlayer(float deltaTime) {
		movmentAngle = shootAngle;
		setPosition(getPosition().cpy().add(new Vector2(velocity.x*deltaTime, velocity.y*deltaTime)));
	}
	
	public void moveTowardsTarget(float deltaTime) {
		movmentAngle = (float)Math.atan2(getPosition().y-target.y, getPosition().x-target.x);
		setPosition(getPosition().cpy().add(new Vector2(velocity.x*deltaTime, velocity.y*deltaTime)));
	}
	
	public float getShootAngle() {
		return shootAngle;
	}
	
	public float getFireRateTime() {
		return fireRateTime;
	}
	
	public float getMaxFireRateTime() {
		return maxFireRateTime;
	}
	
	public void setShootAngle(float shootAngle) {
		this.shootAngle = shootAngle;
	}
	
	public void setFireRateTime(float fireRateTime) {
		this.fireRateTime = fireRateTime;
	}
	
	public void setMaxFireRateTime(float maxFireRateTime) {
		this.maxFireRateTime = maxFireRateTime;
	}
	
	public float getMovmentAngle() {
		return movmentAngle;
	}

	public void setMovmentAngle(float movmentAngle) {
		this.movmentAngle = movmentAngle;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public Vector2 getTarget() {
		return target;
	}

	public void setTarget(Vector2 target) {
		this.target = target;
	}

	public boolean isInRoom() {
		return inRoom;
	}

	public void setInRoom(boolean inRoom) {
		this.inRoom = inRoom;
	}
}
