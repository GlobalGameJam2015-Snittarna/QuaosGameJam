package com.tomnes.dd.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tomnes.dd.framework.Rectangle;
import com.tomnes.dd.framework.Scene;
import com.tomnes.dd.utils.Input;

public abstract class UiElement {
	
	// defines where it listens to mouse events
	private Rectangle area;
	
	// the scene where it resides
	private Scene scene;
	
	private Vector2 oldMouse, newMouse;

	private int isPressed, wasPressed, isHover, wasHover; // index of pointer, -1 if nothing
	
	protected boolean keepGrabbed;
	
	protected boolean isHover() {
		return isHover != -1;
	}

	protected boolean wasHover() {
		return wasHover != -1;
	}

	public Rectangle getArea() {
		return area;
	}

	protected Vector2 getOldMouse() {
		return oldMouse;
	}

	protected Vector2 getNewMouse() {
		return newMouse;
	}

	protected boolean isPressed() {
		return isPressed != -1;
	}

	protected boolean wasPressed() {
		return wasPressed != -1;
	}

	
	public UiElement(Rectangle area) {
		this.area = area;
		newMouse = new Vector2(0, 0);
	}
	
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	protected Vector2 getRelMouse() {
		return getRelMouse(newMouse);
	}
	
	protected Vector2 getRelMouse(Vector2 sceneMouse) {
		return newMouse.cpy().sub(new Vector2(area.getX(), area.getY()));
	}
	
	public Scene getScene() {
		return scene;
	}
	
	public void onMouseEnter() {
		
	}
	
	public void onMouseExit() {
		
	}
	
	public void onClick() {
		
	}
	
	public void onDrag() {
		
	}
	
	public void onRelease() {
		
	}
	
	// called each frame
	public void update() {
		if (keepGrabbed && Input.isPressed(isPressed) && wasPressed()) {
			
		} else {
			isPressed = Input.areaIsClicked(area);
		}
		
		isHover   = Input.intersectingWith(area);
		if (isHover()) newMouse = Input.getTouchPoint(isHover);
		else if (keepGrabbed && isPressed()) newMouse = Input.getTouchPoint(isPressed);
		
		if (!wasHover() && isHover()) {
			onMouseEnter();
			//System.out.println("enter");
		}
		
		if (wasHover() && !isHover()) {
			onMouseExit();
			//System.out.println("exit");
		}
		
		if (!wasPressed() && isPressed())  {
			onClick();
			//System.out.println("press");
		}
		
		if (wasPressed() && !isPressed()) {
			onRelease();
			//System.out.println("release");
		}
		
		if (isPressed() && wasPressed()) {
			onDrag();
			//System.out.println("dragged");
		}
		
		wasHover = isHover;
		wasPressed = isPressed;
		oldMouse = newMouse.cpy();
	}
	
	public abstract void draw(SpriteBatch batch);
}
