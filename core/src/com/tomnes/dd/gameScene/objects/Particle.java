package com.tomnes.dd.gameScene.objects;

import com.badlogic.gdx.math.Vector2;
import com.tomnes.dd.AssetManager;
import com.tomnes.dd.framework.Animation;
import com.tomnes.dd.framework.GameObject;

public class Particle extends GameObject {
	private float angle;
	private float speed;
	
	private Vector2 velocity;
	
	public Particle(Vector2 position, float angle, float speed) {
		super(position, new Vector2(0.2f, 0.2f), new Animation(AssetManager.getTexture("blood")));
		this.speed = speed;
		this.angle = angle;
	}
	
	public void update(float deltaTime) {
		super.update(deltaTime);
		velocity = new Vector2((float)Math.cos(angle)*speed, (float)Math.sin(angle)*speed);
		getSprite().setRotation(angle);
		
		setPosition(getPosition().cpy().add(new Vector2(velocity.x*deltaTime, velocity.y*deltaTime)));
	}
}
