package com.tomnes.dd.gameScene.objects;

import menuScenes.GameOverScene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.tomnes.dd.AssetManager;
import com.tomnes.dd.Game;
import com.tomnes.dd.framework.Animation;
import com.tomnes.dd.framework.GameObject;
import com.tomnes.dd.framework.Killable;
import com.tomnes.dd.framework.Rectangle;
import com.tomnes.dd.gameScene.GameScene;
import com.tomnes.dd.gameScene.objects.Powerup.Type;
import com.tomnes.dd.ui.Bar;
import com.tomnes.dd.ui.Button;
import com.tomnes.dd.ui.Joystick;
import com.tomnes.dd.ui.ShootCotroll;

public class Player extends Killable {

	private final float speed = 3;
	private Joystick moveInput;
	//private Joystick shootInput;
	private ShootCotroll shootInput;
	private Button powerupPickup;
	private Bar hpBar;
	
	private float firerate = .5f;
	private float firerateCounter;
	
	private Powerup.ShootStyle shootStyle;
	private Powerup.ShotType shotType;
	
	private Powerup intersectedPowerup;
	
	private int score;

	public Player() {
		super(new Vector2(0, 0), new Vector2(.85f, 1.7f), new Animation(AssetManager.getTexture("player")), 10);

		shootStyle = shootStyle.Regular;
		shotType = shotType.Bullet;
		
		//shootInput = new Joystick(new Rectangle(125, -725, 250, 250));
		shootInput = new ShootCotroll(new Rectangle(125, -725, 250, 250), this);
		moveInput = new Joystick(new Rectangle(-375, -725, 250, 250));
		powerupPickup = new Button("Pick up", new Rectangle(-75, -420, 150, 50));
		hpBar = new Bar(new Rectangle(-150, 670, 300, 50), AssetManager.getTexture("hp-bar"));
	}

	public void update(float dt) {
		moveInput.update();
		shootInput.update();
		powerupPickup.update();
		
		firerateCounter -= dt;
		
		if (moveInput.isPressed()) {
			float m = (moveInput.getMag() > .3f ? 1 : .3f);
			move(MathUtils.cos(moveInput.getAngle()) * dt * speed * m, MathUtils.sin(moveInput.getAngle()) * dt * speed * m);
		}
		
		/*if (shootInput.isPressed() && firerateCounter <= 0) {
			getScene().addObject(new Bullet(getPosition(), shootInput.getAngle(), 4, false));
			firerateCounter = firerate;
		}*/
		
		intersectedPowerup = intersectsPowerup();
		if (intersectedPowerup != null && powerupPickup.isPressed2()) {
			getScene().removeObject(intersectedPowerup);
			if (intersectedPowerup.getType() == Type.ShootStyle) {
				this.shootStyle = intersectedPowerup.getShootStyle();
			} else {
				this.shotType = intersectedPowerup.getShotType();
			}
		}
		
		hpBar.val = getHealth() / getMaxHealth();
		
		super.update(dt);
	}
	
	public void onDeath() {
		Game.setScene(new GameOverScene());
		super.onDeath();
	}
	
	public void shoot(float angle) {
		int shots = 1;
		switch (shootStyle) {
		case Regular: shots = 1; break;
		case Double: shots = 2; break;
		case Triple: shots = 3; break;
		}
		for (int i = 0; i < shots; i++) {
			float da = (i - shots / 2) * .25f;
			switch (shotType) {
			case Bullet: getScene().addObject(new Bullet(getPosition().cpy().add(getSize().cpy().scl(.0f, .1f)), angle + da, false)); break;
			case Grenade: getScene().addObject(new Granade(getPosition().cpy().add(getSize().cpy().scl(.0f, .1f)), angle + da, 1, 1, false)); break;
			case Laser: getScene().addObject(new Laser(getPosition().cpy().add(getSize().cpy().scl(.0f, .1f)), angle + da, false)); break;
			}
		}
	}
	
	private Powerup intersectsPowerup() {
		for (GameObject g : getScene().getObjects()) {
			if  (g instanceof Powerup && getHitbox().collision(g.getHitbox())) return (Powerup)g;
		}
		return null;
	}
	
	public void move(float x, float y) {
		Vector2 res = ((GameScene)getScene()).getRoom().tryMove(getPosition(), getSize(), new Vector2(x, y));
		if (res == null) setPosition(getPosition().cpy().add(new Vector2(x, y)));
		else  setPosition(res);
	}
	
	public void drawUi(SpriteBatch batch) {
		batch.draw(AssetManager.getTexture("powerupShootStyle" + shootStyle), -450, 700, 100, 100);
		batch.draw(AssetManager.getTexture("powerupShotType" + shotType), 350, 700, 100, 100);
		
		moveInput.draw(batch);
		shootInput.draw(batch);
		hpBar.draw(batch);
		AssetManager.font.draw(batch, "Score: " + getScore(), -100, 650);
		if (intersectedPowerup != null) {
			intersectedPowerup.drawName(batch);
			powerupPickup.draw(batch);
		}
		
		if (((GameScene)getScene()).getRoom().isLevelCleared()) AssetManager.font.draw(batch, "Room cleared!\nChoose a path", -160, 50);
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void addScore(int add) {
		this.score += add;
	}
}
