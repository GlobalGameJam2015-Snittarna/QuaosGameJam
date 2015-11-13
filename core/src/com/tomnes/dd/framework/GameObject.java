/**
 * 
 */
package com.tomnes.dd.framework;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * @author Johannes
 */
public abstract class GameObject {
	private Vector2 position, size, origin;
	private Animation sprite;
	private Scene scene;
	private float depth;
	
	public void setPosition(Vector2 p) { 
		position = p;
		if (sprite != null) sprite.setPosition(position.x - sprite.getOriginX() / sprite.getRegionWidth(), position.y - sprite.getOriginY() / sprite.getRegionHeight());
	}
	
	public Vector2 getPosition() { return position; }
	
	// position on the screen, in world space coordinates
	public Vector2 getScreenCoords() {
		Vector3 v = scene.getCamera().project(new Vector3(getPosition(), 0));
		return new Vector2(v.x, v.y);
	}
	
	public Vector2 getUiScreenCoords() {
		return getScreenCoords().scl(2).sub(new Vector2(scene.UI_W / 2, scene.UI_H / 2));
	}
	
	public void setSize(Vector2 s) { 
		size = s;
		if (sprite != null) sprite.setSize(s.x, s.y); 
	}
	
	public void setColor(Color c) {
		if(sprite != null) sprite.setColor(c);
	}
	
	public Vector2 getSize() { return size; }
	
	public Animation getSprite() { return sprite; }

	public Scene getScene() { return scene; }
	
	//note that origin is in pixels on the texture region, not world coordinates
	protected void setOrigin(Vector2 origin) {
		this.origin = origin;
		if (sprite != null) sprite.setOrigin(origin.x, origin.y);
	}
	
	protected Vector2 getOrigin() {
		return origin;
	}
	
	protected void setOriginCenter() {
		getSprite().setOriginCenter();
		origin = new Vector2(sprite.getOriginX()/2, sprite.getOriginY()/2);
	}
	
	public float getDepth() {
		return depth;
	}
	
	public void setDepth(float depth) {
		this.depth = depth;
		if (scene != null) scene.onDepthChange();
	}
	
	public void setSprite(TextureRegion texture) {
		sprite.setRegion(texture);
	}
	
	public void setSprite(Animation sprite) {
		this.setSprite(sprite, false);
	}
	
	public void setSprite(Animation sprite, boolean discardInfo) {
		this.sprite = sprite;
		if (!discardInfo) {
			setPosition(position);
			setSize(size);
			setOrigin(origin);
		}
	}
		
	public GameObject(Vector2 position, Vector2 size, Animation sprite) {
		this.sprite = sprite;
		setOriginCenter();
		setPosition(position);
		setSize(size);
	}
	
	public GameObject(Vector2 position, Animation sprite) {
		this(position, new Vector2((sprite != null ? sprite.getWidth() : 0), (sprite != null ? sprite.getHeight() : 0)), sprite);
	}
	
	public void onAdd(Scene scene) {
		this.scene = scene;
	}
	
	public Rectangle getHitbox(){
		return new Rectangle(this.position, this.size);
	}
	
	public void onRemove() { };
	
	public float distanceTo(Vector2 v) {
		Vector2 delta = v.cpy().sub(getPosition().cpy());
		float f = delta.len();
		return f;
	}
	
	public void update(float deltaTime) { }
	
	public void draw(SpriteBatch batch) {
		if (sprite != null) sprite.draw(batch);
	}
	
	public void drawUi(SpriteBatch batch) { } 
}
