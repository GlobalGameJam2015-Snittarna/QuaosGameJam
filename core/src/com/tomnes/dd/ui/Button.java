package com.tomnes.dd.ui;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tomnes.dd.AssetManager;
import com.tomnes.dd.framework.Rectangle;

public class Button extends UiElement {

	private String text;
	
	private GlyphLayout layout;
	
	public Button(String text, Rectangle area) {
		super(area);
		
		this.text = text;
		layout = new GlyphLayout(AssetManager.font, text);
	}

	public boolean isPressed() {
		return super.isPressed();
	}
	
	public void draw(SpriteBatch batch) {
		AssetManager.font.draw(batch, text, getArea().getX() + getArea().getWidth() / 2 - layout.width / 2, getArea().getY() - getArea().getHeight() / - layout.height / 2);
	}

}
