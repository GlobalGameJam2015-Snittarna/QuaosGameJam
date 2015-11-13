package com.tomnes.dd.gameScene.objects;

import com.badlogic.gdx.math.Vector2;
import com.tomnes.dd.AssetManager;
import com.tomnes.dd.framework.Animation;
import com.tomnes.dd.framework.GameObject;

public class Player extends GameObject {

	public Player() {
		super(new Vector2(0, 0), new Vector2(1, 1), new Animation(AssetManager.getTexture("player")));
		// TODO Auto-generated constructor stub
	}

}
