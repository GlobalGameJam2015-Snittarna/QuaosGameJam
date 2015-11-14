package com.tomnes.dd.gameScene.objects;

import com.badlogic.gdx.math.Vector2;
import com.tomnes.dd.AssetManager;
import com.tomnes.dd.framework.Animation;
import com.tomnes.dd.framework.GameObject;

public class Shooter extends Enemy {

	public Shooter(Vector2 position) {
		super(position, new Vector2(.85f, 1.7f), new Animation(AssetManager.getTexture("shooter")));
		// TODO Auto-generated constructor stub
		this.setHealth(2);
		this.setSpeed(0.5f);
		this.setMaxFireRateTime(1);
		setWorth(333);
	}
	
	public void update(float deltaTime) {
		super.update(deltaTime);
		attack(deltaTime);
		shoot(deltaTime);
		
		if(isInRoom()) {
			for(GameObject g : getScene().getObjects()) {
				if(g instanceof Player) {
					if(g.distanceTo(this.getPosition()) >= 4) {
						this.moveTowardsPlayer(deltaTime);
					}
				}
			}
		}
	}
	
	public void onShoot() {
		super.onShoot();
		getScene().addObject(new Bullet(getPosition(), this.getShootAngle(), true));
	}
}
