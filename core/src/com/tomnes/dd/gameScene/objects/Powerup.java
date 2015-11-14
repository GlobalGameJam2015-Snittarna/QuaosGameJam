package com.tomnes.dd.gameScene.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.tomnes.dd.AssetManager;
import com.tomnes.dd.framework.Animation;
import com.tomnes.dd.framework.GameObject;

public class Powerup extends GameObject {

	public enum Type { ShotType, ShootStyle };
	
	public enum ShotType { Bullet, Laser, Grenade };
	
	public enum ShootStyle { Regular, Double, Triple };
	
	private Type type;
	private ShotType shotType;
	private ShootStyle shootStyle;
	
	private String name;
	
	public Powerup(Vector2 position) {
		super(position, new Vector2(.3f, .3f), new Animation(AssetManager.getTexture("error")));
	
		if (MathUtils.randomBoolean()) {
			type = Type.ShotType;
			shotType = ShotType.values()[MathUtils.random(2)];
			setSprite(AssetManager.getTexture("powerup" + type + shotType));
			name = "Bullet type:\n" + shotType.name();
		} else {
			type = Type.ShootStyle;
			shootStyle = ShootStyle.values()[MathUtils.random(1)];
			setSprite(AssetManager.getTexture("powerup" + type + shootStyle));
			name = "Shooting style:\n" + shootStyle.name();
		}
		
	}
	
	
	
	public void drawName(SpriteBatch batch) {
		AssetManager.font.draw(batch, name, getUiScreenCoords().x, getUiScreenCoords().y);
	}
}
