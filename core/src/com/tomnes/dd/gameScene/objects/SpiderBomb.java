package com.tomnes.dd.gameScene.objects;

import com.badlogic.gdx.math.Vector2;
import com.tomnes.dd.AssetManager;
import com.tomnes.dd.framework.Animation;

public class SpiderBomb extends Enemy {

	public SpiderBomb(Vector2 position) {
		super(position, new Vector2(.8f, .8f), new Animation(new Animation(AssetManager.getTexture("spiderBomb"))));
		setHealth(5);
		this.setSpeed(1);
	}
	
	public void update(float deltaTime) {
		super.update(deltaTime);
		this.moveTowardsPlayer(deltaTime);
		
	}
}
