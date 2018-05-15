/**
 * Laser subclass for the game.
 * Renders and handles each laser shot by generating a list of shots based on keyboard input, and then iterating through them.
 */

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Laser extends Sprite {
	private static List<Laser> lasers = new ArrayList<Laser>();
	public Laser(String imageSrc, float x, float y, float speed) throws SlickException {
		super(imageSrc, x, y, speed);
	}
	public void update(Input input, int delta, float player_X, float player_Y) throws SlickException {
		// Generate the y value for the moving laser sprite
		if (input.isKeyPressed(Input.KEY_SPACE)) {
			Laser shot = new Laser("res/shot.png", player_X, player_Y, getSpeed());
			getLasers().add(shot);
		}
		// Update the BoundingBox for each laser shot
		for (Laser currentShot : getLasers()) {
			currentShot.setY(currentShot.getY() - getSpeed()*delta);
			try {
				currentShot.getBoundingBox().update(currentShot.getX(), currentShot.getY());
			} catch (NullPointerException npe) {
				npe.printStackTrace();
			}
		}
		// Remove the current laser shot from the ArrayList if it's moved off-screen
		getLasers().removeIf(currentShot -> (currentShot.getY() < 0));
	}
	public void render() {
		getSprite().draw(getX(), getY());
	}
	public static List<Laser> getLasers() {
		return Laser.lasers;
	}
}