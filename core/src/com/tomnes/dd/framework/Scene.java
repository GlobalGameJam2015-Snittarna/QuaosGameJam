package com.tomnes.dd.framework;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public abstract class Scene {
	public final int UI_W = 900, UI_H = 1600, G_W = 9, G_H = 16; // camera size in game and ui
	
	private boolean depthChanged;
	
	private ArrayList<GameObject> objects, toAdd, toRemove;
	private OrthographicCamera camera, uiCamera;
	
	public OrthographicCamera getCamera() { return camera; }
	public OrthographicCamera getUiCamera() { return uiCamera; }
	
	private float screenShake; // for both duration and intensity

	public ArrayList<GameObject> getObjects() { return objects; }
	
	public Scene() {
		objects = new ArrayList<GameObject>();
		toAdd = new ArrayList<GameObject>();
		toRemove = new ArrayList<GameObject>();
		uiCamera = new OrthographicCamera(UI_W, UI_H);
		camera = new OrthographicCamera(G_W, G_H);
	}
	
	public void update(float deltaTime) {
		if (depthChanged) {
			//could be more effective with knowledge of which object changed and a bunch of custom sorting
			Collections.sort(objects, new Comparator<GameObject>() {
				@Override
				public int compare(GameObject o1, GameObject o2) {
					return Float.compare(o1.getDepth(), o2.getDepth());
				}
			});
			depthChanged = false;
		}
		
		camera.update();
		
		for (GameObject g : objects) g.update(deltaTime);
		
		for (GameObject g : toAdd) {
			objects.add(g);
			g.onAdd(this);
			onDepthChange();
		}
		for (GameObject g : toRemove) {
			objects.remove(g);
			g.onRemove();
		}
		toAdd.clear();
		toRemove.clear();
	
		if (screenShake > 0) {
			screenShake -= deltaTime;
			if (screenShake < 0) screenShake = 0;
		}
	}
	
	public void setScreenShake(float screenShake) {
		this.screenShake = screenShake;
	}
	
	public void onDepthChange() {
		depthChanged = true;
	}
	
	public void onPause() { }
	public void onResume() { }
	
	public void addObject(GameObject g) { g.onAdd(this); toAdd.add(g); }
	public void removeObject(GameObject g) { toRemove.add(g); }
	
	public void drawUi(SpriteBatch batch) {
		for (GameObject g : objects) g.drawUi(batch);
	}
	
	public void draw(SpriteBatch batch) {
		float ss = MathUtils.clamp(screenShake, 0, .2f);
		
		if (screenShake > 0) camera.position.set(new Vector3(ss * MathUtils.random(-1, 1), ss * MathUtils.random(-1, 1), 0));
		else camera.position.set(new Vector3(0, 0, 0));
		
		for (GameObject g : objects) g.draw(batch);
	}
	
	public Vector2 getUiMouse() {
		Vector3 mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		uiCamera.unproject(mouse);
		return new Vector2(mouse.x, mouse.y);
	}
	
	public Vector2 getScreenMouse() {
		return new Vector2(Gdx.input.getX(), Gdx.input.getY());
	}
	
	public Vector2 getWorldMouse() {
		Vector3 mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(mouse);
		return new Vector2(mouse.x, mouse.y);
	}
}
