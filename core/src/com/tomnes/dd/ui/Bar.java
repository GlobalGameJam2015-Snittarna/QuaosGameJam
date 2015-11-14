package com.tomnes.dd.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tomnes.dd.AssetManager;
import com.tomnes.dd.framework.Rectangle;

public class Bar extends UiElement {

	private TextureRegion foregroundTexture;
	public float val;
	
	public Bar(Rectangle area, TextureRegion foreground) {
		super(area);
		this.foregroundTexture = foreground;
	}
	
	public void draw(SpriteBatch batch) {
		batch.draw(AssetManager.getTexture("bar-background"), getArea().getX(), getArea().getY(), getArea().getWidth(), getArea().getHeight());
		batch.draw(foregroundTexture, getArea().getX(), getArea().getY(), getArea().getWidth() * val, getArea().getHeight());
	}
}
