package com.tomnes.dd.gameScene;

import com.badlogic.gdx.math.Vector2;
import com.tomnes.dd.framework.Animation;
import com.tomnes.dd.framework.Killable;

public abstract class Enemy extends Killable {
	public Enemy(Vector2 position, Vector2 size, Animation sprite) {
		super(position, size, sprite);
		// TODO Auto-generated constructor stub
	}
	
	public void update(float deltaTime) {
		super.update(deltaTime);
	}
}
