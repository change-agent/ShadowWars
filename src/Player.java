/**
 * Player subclass for the game.
 * Renders and handles the player sprite, controlling its position based on keyboard input.
 */

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends Sprite {
	public Player(String imageSrc, float x, float y, float speed) throws SlickException {
		super(imageSrc, x, y, speed);
	}
	public void update(Input input, int delta) {
		// Move player according to keyboard input and update BoundingBox accordingly
		if (input.isKeyDown(Input.KEY_DOWN) && getY() <= App.SCREEN_HEIGHT - getSprite().getHeight()) {
			setY(getY() + getSpeed()*delta);
		} else if (input.isKeyDown(Input.KEY_UP) && getY() >= App.SCREEN_HEIGHT - App.SCREEN_HEIGHT) {
			setY(getY() - getSpeed()*delta);
		}
		if (input.isKeyDown(Input.KEY_RIGHT) && getX() <= App.SCREEN_WIDTH - getSprite().getWidth()) {
			setX(getX() + getSpeed()*delta);
		} else if (input.isKeyDown(Input.KEY_LEFT) && getX() >= App.SCREEN_WIDTH - App.SCREEN_WIDTH) {
			setX(getX() - getSpeed()*delta);
		}
		getBoundingBox().update(getX(), getY());
	}
	public void contactSprite(Enemy other) {
		// If player comes into contact with an enemy, game exits
		try {
			if(getBoundingBox().intersects(other.getBoundingBox())) {
				System.exit(0);
			}
		} 
		catch (NullPointerException npe) {
			npe.printStackTrace();
		}
	}
}
