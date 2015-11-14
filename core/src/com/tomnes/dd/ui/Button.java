package com.tomnes.dd.ui;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tomnes.dd.AssetManager;
import com.tomnes.dd.framework.Rectangle;

public class Button extends UiElement {

	private String text;
	
	private GlyphLayout layout;
	
	boolean isPressed;
	
	public Button(String text, Rectangle area) {
		super(area);
		keepGrabbed = false;
		this.text = text;
		layout = new GlyphLayout(AssetManager.font, text);
	}

	public boolean isPressed2() {
		return isPressed;
	}
	
	public void onClick() {
		System.out.println("clicked");
		isPressed = true;
	}
	
	public void onDrag() {
		System.out.println("dragged");
		isPressed = false;
	}
	
	public void draw(SpriteBatch batch) {
		AssetManager.font.draw(batch, text, getArea().getX() + getArea().getWidth() / 2 - layout.width / 2, getArea().getY() + getArea().getHeight() - layout.height / 2);
		//batch.draw(AssetManager.getTexture("box"), getArea().getX(), getArea().getY(), getArea().getWidth(), getArea().getHeight());
	}

}
