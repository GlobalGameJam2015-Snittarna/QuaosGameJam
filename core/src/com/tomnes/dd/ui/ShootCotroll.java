package com.tomnes.dd.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.tomnes.dd.AssetManager;
import com.tomnes.dd.framework.Rectangle;
import com.tomnes.dd.gameScene.objects.Player;

public class ShootCotroll extends UiElement {

	private Vector2 touchPoint;
	private Player p;
	
	public ShootCotroll(Rectangle area, Player p) {
		super(area);
		// TODO Auto-generated constructor stub
		this.p = p;
	}
	
	public void onClick() {
		touchPoint = getNewMouse();
	}
	
	public void onRelease() {
		if (touchPoint != null) {
			Vector2 delta = getNewMouse().sub(touchPoint);
			float angle = MathUtils.atan2(delta.y, delta.x);
			p.shoot(angle);
		}
	}

	public void draw(SpriteBatch batch) {
		batch.draw(AssetManager.getTexture("joystick-body"), getArea().getX(), getArea().getY(), getArea().getWidth(), getArea().getHeight());
		if (isPressed()) {
			batch.draw(AssetManager.getTexture("joystick-center"), 
					getNewMouse().x - getArea().getWidth() / 6, 
					getNewMouse().y - getArea().getHeight() / 6, 
					getArea().getWidth() / 3, 
					getArea().getHeight() / 3);
		}
	}	
}
