package com.tomnes.dd.gameScene.objects;

import com.badlogic.gdx.math.Vector2;
import com.tomnes.dd.AssetManager;
import com.tomnes.dd.framework.Animation;

public class SpiderBomb extends Enemy {

	public SpiderBomb(Vector2 position) {
		super(position, new Vector2(.8f, .8f), new Animation(new Animation(AssetManager.getTexture("spiderBomb"))));
		setHealth(2);
		this.setSpeed(1);
		this.getSprite().setAnimation(1, 4, 0, false);
	}
	
	public void update(float deltaTime) {
		super.update(deltaTime);
		if(this.isInRoom()) this.moveTowardsPlayer(deltaTime);
		getSprite().animate(deltaTime);
	}
	
	public void onDeath() {
		getScene().addObject(new Powerup(getPosition()));
		super.onDeath();
	}
}
