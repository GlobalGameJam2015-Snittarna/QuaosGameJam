package com.tomnes.dd.gameScene.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.tomnes.dd.AssetManager;
import com.tomnes.dd.framework.Animation;
import com.tomnes.dd.framework.GameObject;
import com.tomnes.dd.framework.Rectangle;
import com.tomnes.dd.ui.Button;
import com.tomnes.dd.ui.Joystick;

public class Player extends GameObject {

	private Joystick moveInput;
	
	public Player() {
		super(new Vector2(0, 0), new Vector2(1, 1), new Animation(AssetManager.getTexture("player")));

		moveInput = new Joystick(new Rectangle(200, -750, 200, 200));
	}

	public void update(float dt) {
		moveInput.update();
		
		if (moveInput.isPressed()) {
			setPosition(getPosition().cpy().add(new Vector2(MathUtils.cos(moveInput.getAngle()), MathUtils.sin(moveInput.getAngle())).scl(.1f)));
		}
		
		super.update(dt);
	}
	
	public void drawUi(SpriteBatch batch) {
		moveInput.draw(batch);
	}
}
