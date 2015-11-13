package com.tomnes.dd.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.tomnes.dd.AssetManager;
import com.tomnes.dd.framework.Rectangle;

public class Joystick extends UiElement {

	private Vector2 center;  //  relative to position
	private float angle, mag;
	
	public Joystick(Rectangle area) {
		super(area);
		keepGrabbed = true;
		this.center = new Vector2(area.getWidth() / 2, area.getHeight() / 2);
	}
	
	public boolean isPressed() {
		return super.isPressed();
	}
	
	public float getAngle() {
		return angle;
	}
	
	public float getMag() {
		mag *= 1.2f;
		if (mag > 1) return 1;
		else return mag;
	}
	
	public void onDrag() {
		Vector2 delta = getNewMouse().cpy().sub(new Vector2(center.x + getArea().getX(), center.y + getArea().getY()));
		System.out.println(delta);
		angle = MathUtils.atan2(delta.y, delta.x);
		System.out.println(angle);
		mag = delta.len() / center.x;
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
