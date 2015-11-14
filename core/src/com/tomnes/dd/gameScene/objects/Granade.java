package com.tomnes.dd.gameScene.objects;

import com.badlogic.gdx.math.Vector2;
import com.tomnes.dd.AssetManager;
import com.tomnes.dd.framework.Animation;

public class Granade extends Projectile {

	//  TODO: remove speed argument
	public Granade(Vector2 position, float angle, float speed, float damege, boolean hitsPlayer) {
		super(position, new Vector2(.2f, .2f), new Animation(AssetManager.getTexture("bullet")), angle, 2, 1, hitsPlayer);
		// TODO Auto-generated constructor stub
	}
	
	public void onHit() {
		getScene().addObject(new Explosion(this.getPosition().add(new Vector2(-0.5f, -0.5f)), true));
		super.onHit();
	}
	
	public void onHitWall() {
		getScene().addObject(new Explosion(this.getPosition().add(new Vector2(-0.5f, -0.5f)), true));
		super.onHitWall();
	}
}
