package com.tomnes.dd.gameScene.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tomnes.dd.AssetManager;
import com.tomnes.dd.framework.Animation;
import com.tomnes.dd.framework.GameObject;
import com.tomnes.dd.gameScene.GameScene;

public abstract class Projectile extends GameObject {

	private float angle, speed;
	
	private float damage;
	
	private boolean hitsPlayer;
	
	public Projectile(Vector2 position, Vector2 size, Animation sprite, float angle, float speed, float damege, boolean hitsPlayer) {
		super(position, size, sprite);
		this.angle = angle;
		this.speed = speed;
		this.hitsPlayer = hitsPlayer;
		this.damage = damege;
		

		getSprite().setRotation(angle * 180 / 3.14f);
		getSprite().setOriginCenter();
	}
	
	public boolean  hitsPlayer() {
		return hitsPlayer;
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
	
	public void onHit() {
		
	}
	
	public void onHitWall() {
		getScene().removeObject(this);
	}
	
	public float getDamage() {
		return damage;
	}
	
	public void draw(SpriteBatch batch) {
		getSprite().draw(batch);
	}
}
