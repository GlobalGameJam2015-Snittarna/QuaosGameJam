package com.tomnes.dd.gameScene.objects;

import java.util.Random;

import com.badlogic.gdx.math.MathUtils;
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
	
	private int worth;

	private boolean inRoom;
	
	public Enemy(Vector2 position, Vector2 size, Animation sprite) {
		super(position, size, sprite, 5);
		
		target = new Vector2(0, 0);
	}
	
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		velocity = new Vector2((float)Math.cos(movmentAngle)*speed, (float)Math.sin(movmentAngle)*speed);
		
		attack(deltaTime);
		
		if(getHealth() <= 0) {
			for(GameObject g : getScene().getObjects()) {
				if(g instanceof Player) {
					((Player) g).addScore(worth);
				}
			}
		}
		
		for(GameObject g : getScene().getObjects()) {
			if(g instanceof Player) {
				if(g.getHitbox().collision(getHitbox())) {
					((Player) g).setHealth(((Player) g).getHealth()-1);
					((Player) g).setHitTime(0.1f);
					setHealth(0);
				}
			}
		}
		
		if(!inRoom) {
			this.moveTowardsTarget(deltaTime);
			//System.out.println(this.getPosition().sub(target).len());
			if(this.getPosition().sub(target).len() <= 4f) {
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
		if(inRoom) fireRateTime += 0.5f * deltaTime;
		
		if(fireRateTime >= maxFireRateTime) {
			onShoot();
			fireRateTime = 0;
		}
	}
	
	public void onDeath() {
		Random random = new Random();
		
		super.onDeath();
		for(int i = 0; i < 20; i++) {
			getScene().addObject(new Particle(this.getPosition(), random.nextFloat()*(float)Math.PI*2, random.nextInt(15)+10));
		}
		getScene().addObject(new Explosion(this.getPosition().add(new Vector2(-0.5f, -0.5f)), false));
		if (MathUtils.randomBoolean(.2f)) getScene().addObject(new Powerup(getPosition()));
	}
	
	public void onShoot() {
		
	}
	
	public void moveTowardsPlayer(float deltaTime) {
		movmentAngle = shootAngle;
		setPosition(getPosition().cpy().add(new Vector2(velocity.x*deltaTime, velocity.y*deltaTime)));
	}
	
	public void moveTowardsTarget(float deltaTime) {
		movmentAngle = (float)Math.atan2(target.y-getPosition().y, target.x-getPosition().x);
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
	
	public int getWorth() {
		return worth;
	}

	public void setWorth(int worth) {
		this.worth = worth;
	}
}
