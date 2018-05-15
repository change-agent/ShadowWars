/**
 * Sprite class for the game.
 * The parent abstract class for each of the sprite objects in the game world: Laser, Enemy, and Player.
 * Handles sprite image rendering and provides the majority of the required variables for each object.
 */

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

public abstract class Sprite {
	private float x, y, speed;
	private Image sprite;
	private BoundingBox BoundingBox;
	
	public Sprite(String imageSrc, float x, float y) throws SlickException {
		sprite = new Image(imageSrc);
		this.setX(x);
		this.setY(y);
		BoundingBox = new BoundingBox(sprite, x, y);
	}
	// Overload constructor for moving sprites, which require a speed parameter
	public Sprite(String imageSrc, float x, float y, float speed) throws SlickException {
		this(imageSrc, x, y);
		this.speed = speed;
	}
	public void render() {
		getSprite().draw(x, y);
	}
	public Image getSprite() {
		return sprite;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public BoundingBox getBoundingBox() {
		return BoundingBox;
	}
	public float getCentre() {
		return getSprite().getCenterOfRotationX();
	}
	public float getSpeed() {
		return speed;
	}
}