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

	private final float speed = 3;
	private Joystick moveInput;
	
	public Player() {
		super(new Vector2(0, 0), new Vector2(1.5f, 1.5f), new Animation(AssetManager.getTexture("player")));

		moveInput = new Joystick(new Rectangle(125, -725, 250, 250));
	}

	public void update(float dt) {
		moveInput.update();
		
		if (moveInput.isPressed()) {
			float m = (moveInput.getMag() > .3f ? 1 : .3f);
			setPosition(getPosition().cpy().add(new Vector2(MathUtils.cos(moveInput.getAngle()), MathUtils.sin(moveInput.getAngle())).scl(dt * speed * m)));
		}
		
		super.update(dt);
	}
	
	public void drawUi(SpriteBatch batch) {
		moveInput.draw(batch);
	}
}
