package com.tomnes.dd.gameScene.objects;

import com.badlogic.gdx.math.Vector2;
import com.tomnes.dd.AssetManager;
import com.tomnes.dd.framework.Animation;
import com.tomnes.dd.framework.GameObject;

public class Explosion extends GameObject {
	private boolean dangerous;
	
	public Explosion(Vector2 position, boolean dangerous) {
		super(position, new Vector2(2, 2), new Animation(AssetManager.getTexture("explosion")));
		getSprite().setAnimation(0.2f, 4, 0, false);
		this.dangerous = dangerous;
	}

	public void update(float deltaTime) {
		super.update(deltaTime);
		getSprite().animate(deltaTime);
		
		if(dangerous && getSprite().getCurrentFrame() <= 2) {
			for(GameObject g : getScene().getObjects()) {
				if(g instanceof Enemy) {
					if(g.getHitbox().collision(getHitbox())) {
						((Enemy) g).setHealth(0);
					}
				}
			}
		}
		
		if(getSprite().animationDone()) getScene().removeObject(this);
	}
}
