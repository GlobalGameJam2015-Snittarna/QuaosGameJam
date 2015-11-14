package com.tomnes.dd.gameScene.objects;

import com.badlogic.gdx.math.Vector2;
import com.tomnes.dd.AssetManager;
import com.tomnes.dd.framework.Animation;

public class Laser extends Projectile {

	public Laser(Vector2 position, float angle, boolean hitsPlayer) {
		super(position, new Vector2(.2f, .2f), new Animation(AssetManager.getTexture("laser")), angle, 8, 4, hitsPlayer);
		// TODO Auto-generated constructor stub
	}

}

