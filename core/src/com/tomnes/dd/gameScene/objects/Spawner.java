package com.tomnes.dd.gameScene.objects;

import com.badlogic.gdx.math.Vector2;
import com.tomnes.dd.AssetManager;
import com.tomnes.dd.framework.Animation;
import com.tomnes.dd.framework.GameObject;

public class Spawner extends Enemy {

	public Spawner(Vector2 position) {
		super(position, new Vector2(2, 2), new Animation(AssetManager.getTexture("spawner")));
		// TODO Auto-generated constructor stub
		setHealth(4);
		this.setMaxFireRateTime(2);
		this.setSpeed(0.3f);
		setWorth(1000);
	}
	
	public void update(float deltaTime) {
		super.update(deltaTime);
		attack(deltaTime);
		shoot(deltaTime);
		
		//System.out.println(this.getFireRateTime());
		
		if(isInRoom()) {
			for(GameObject g : getScene().getObjects()) {
				if(g instanceof Player) {
					if(g.distanceTo(this.getPosition()) >= 4.5f) {
						this.moveTowardsPlayer(deltaTime);
					}
				}
			}
		}
	}
	
	public void onShoot() {
		super.onShoot();
		getScene().addObject(new SpiderBomb(this.getPosition()));
	}
}
