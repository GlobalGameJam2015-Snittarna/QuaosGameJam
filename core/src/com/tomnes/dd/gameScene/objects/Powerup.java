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
	
	private final float dissapearTime = 5;
	private float timer;
	
	private Type type;
	private ShotType shotType;
	public ShotType getShotType() {
		return shotType;
	}

	public ShootStyle getShootStyle() {
		return shootStyle;
	}

	public Type getType() {
		return type;
	}

	private ShootStyle shootStyle;
	
	private String name;
	
	public Powerup(Vector2 position) {
		super(position, new Vector2(.4f, .4f), new Animation(AssetManager.getTexture("error")));
	
		if (MathUtils.randomBoolean()) {
			type = Type.ShotType;
			shotType = ShotType.values()[MathUtils.random(2)];
			setSprite(AssetManager.getTexture("powerup" + type + shotType));
			name = shotType.name();
		} else {
			type = Type.ShootStyle;
			shootStyle = ShootStyle.values()[MathUtils.random(2)];
			setSprite(AssetManager.getTexture("powerup" + type + shootStyle));
			name = shootStyle.name() + " shot";
		}
		
	}
	
	public void update(float dt) {
		timer += dt;
		if (timer >= dissapearTime) getScene().removeObject(this);
	}
	
	
	
	public void drawName(SpriteBatch batch) {
		AssetManager.font.draw(batch, name, getUiScreenCoords().x, getUiScreenCoords().y);
	}
}
