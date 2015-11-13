package com.tomnes.dd.utils;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.tomnes.dd.Game;
import com.tomnes.dd.framework.Point;

public class Input {
	final static int points = 3; //how many multitouch points are checked
	
	public static float[] x;
	public static float[] y;
	public static boolean[] isPressed;
	
	public static float[] oldX;
	public static float[] oldY;
	public static boolean[] wasPressed;
	
	private static float scaleX;
	private static float scaleY;
	
	private static float W, H; // viewport size
	private static float OX, OY; // viewport origin
	
	public static void initialize(float _W, float _H, float _OX, float _OY) {
		W = _W;
		H = _H;
		OX = _OX;
		OY = _OY;
		
		x = new float[points];
		y = new float[points];
		isPressed = new boolean[points];
		oldX = new float[points];
		oldY = new float[points];
		wasPressed = new boolean[points];
		
		scaleX = W / Gdx.graphics.getWidth();
		scaleY = H / Gdx.graphics.getHeight();
		getAllInput();
		update();
	}
	
	public static boolean wasJustPressed(int pointerIndex) {
		return isPressed[pointerIndex] && !wasPressed[pointerIndex];
	}
	
	private static void getAllInput() {
		for (int i = 0; i < points; i++) getInput(i);
	}
	
	private static void getInput(int pointer) {
		x[pointer] = Gdx.input.getX(pointer) * scaleX - OX;
		y[pointer] = H - (Gdx.input.getY(pointer) * scaleY) - OY;
		wasPressed[pointer] = isPressed[pointer];
		isPressed[pointer] = Gdx.input.isTouched(pointer);		
	}
	
	public static void update() {
		oldX = x;
		oldY = y;
		getAllInput();
	}
	
	public static boolean isPressed() {
		for (boolean b : isPressed) if (b) return true;
		return false;
	}
	
	public static boolean wasPressed() {
		for (boolean b : wasPressed) if (b) return true;
		return false;
	}
	
	public static Vector2[] getTouchPoints() {
		ArrayList<Vector2> ret = new ArrayList<Vector2>();
		for (int i = 0; i < points; i++) {
			if (isPressed[i]) ret.add(new Vector2(x[i], y[i]));
		}
		return ret.toArray(new Vector2[ret.size()]);
	}
	
	public static boolean wasJustPressed() {
		return isPressed() && !wasPressed();
	}
	
	public static boolean intersectingWith(float _x, float _y, float width, float height) {
		for (int i = 0; i < points; i++) if (x[i] >= _x && y[i] >= _y && x[i] <= _x + width && y[i] <= _y + height) return true;
		return false;
	}
	
	public static boolean areaIsClicked(float _x, float _y, float width, float height) {
		for (int i = 0; i < points; i++) if (x[i] >= _x && y[i] >= _y && x[i] <= _x + width && y[i] <= _y + height && isPressed[i]) return true;
		return false;
	}
	
	public static boolean areaWasJustClicked(float _x, float _y, float width, float height) {
		for (int i = 0; i < points; i++) if (x[i] >= _x && y[i] >= _y && x[i] <= _x + width && y[i] <= _y + height && isPressed[i] && !wasPressed[i]) return true;
		return false;
	}
}