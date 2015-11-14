package com.tomnes.dd.gameScene.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.tomnes.dd.AssetManager;
import com.tomnes.dd.framework.Animation;
import com.tomnes.dd.framework.GameObject;
import com.tomnes.dd.framework.Killable;
import com.tomnes.dd.framework.Rectangle;
import com.tomnes.dd.gameScene.GameScene;
import com.tomnes.dd.ui.Button;
import com.tomnes.dd.ui.Joystick;

public class Player extends Killable {

	private final float speed = 3;
	private Joystick moveInput;
	private Joystick shootInput;
	private Button powerupPickup;
	
	private float firerate = .5f;
	private float firerateCounter;
	
	private Powerup.ShootStyle shootStyle;
	private Powerup.ShotType shotType;
	
	private Powerup intersectedPowerup;
	
	public Player() {
		super(new Vector2(0, 0), new Vector2(.85f, 1.7f), new Animation(AssetManager.getTexture("player")), 1);

		shootStyle = shootStyle.Regular;
		shotType = shotType.Bullet;
		
		shootInput = new Joystick(new Rectangle(125, -725, 250, 250));
		moveInput = new Joystick(new Rectangle(-375, -725, 250, 250));
		powerupPickup = new Button("Pick up", new Rectangle(-150, -600, 300, 100));
	}

	public void update(float dt) {
		moveInput.update();
		shootInput.update();
		
		firerateCounter -= dt;
		
		if (moveInput.isPressed()) {
			float m = (moveInput.getMag() > .3f ? 1 : .3f);
			move(MathUtils.cos(moveInput.getAngle()) * dt * speed * m, MathUtils.sin(moveInput.getAngle()) * dt * speed * m);
		}
		
		if (shootInput.isPressed() && firerateCounter <= 0) {
			getScene().addObject(new Bullet(getPosition(), shootInput.getAngle(), 4, false));
			firerateCounter = firerate;
		}
		
		intersectedPowerup = intersectsPowerup();
		
		super.update(dt);
	}
	
	private Powerup intersectsPowerup() {
		for (GameObject g : getScene().getObjects()) {
			if  (g instanceof Powerup && getHitbox().collision(g.getHitbox())) return (Powerup)g;
		}
		return null;
	}
	
	public void move(float x, float y) {
		Vector2 res = ((GameScene)getScene()).getRoom().tryMove(getPosition(), getSize(), new Vector2(x, y));
		if (res == null) setPosition(getPosition().cpy().add(new Vector2(x, y)));
		else  setPosition(res);
	}
	
	public void drawUi(SpriteBatch batch) {
		moveInput.draw(batch);
		shootInput.draw(batch);
		if (intersectedPowerup != null) {
			intersectedPowerup.drawName(batch);
			powerupPickup.draw(batch);
		}
	}
}
