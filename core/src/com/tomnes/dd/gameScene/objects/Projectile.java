package com.tomnes.dd.gameScene.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tomnes.dd.AssetManager;
import com.tomnes.dd.framework.Animation;
import com.tomnes.dd.framework.GameObject;
import com.tomnes.dd.gameScene.GameScene;

public abstract class Projectile extends GameObject {

	private float angle, speed;
	
	public Projectile(Vector2 position, Vector2 size, Animation sprite, float angle, float speed) {
		super(position, size, sprite);
		this.angle = angle;
		this.speed = speed;
	}
	
	public void update(float dt) {
		Vector2 delta = new Vector2(speed * dt, 0).setAngleRad(angle);
		Vector2 target = getPosition().cpy().add(delta);
		Vector2 real = ((GameScene)getScene()).getRoom().tryMove(getPosition(), getSize(), delta);
		
		if (real == null) {
			setPosition(target);
		} else {
			onHitWall();
		}
	}
	
	public void onHitWall() {
		getScene().removeObject(this);
		System.out.println("hit wall");
	}
	
	public void draw(SpriteBatch batch) {
		batch.draw(getSprite().getTexture(), getPosition().x, getPosition().y, getSize().x, getSize().y);
	}
}
