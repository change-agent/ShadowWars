/**
 * Background class for the game.
 * Draws all four tile corners of the game's background, and updates the y-value in order for it to scroll continuously.
 */

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Background {
		private static final float PIXELS = 0.2f, INIT_COORDINATE = 0;
		private float x = INIT_COORDINATE, y = INIT_COORDINATE, width, height;
		private Image background;
	
	public Background(String imageSrc) throws SlickException{
		background = new Image(imageSrc);
		width = background.getWidth();
		height = background.getHeight();
	}
	
	public void update(int delta){
		// Generate the y-value for the looping background tiles
		y += PIXELS*delta;
		if (y >= height) {
		    y = height - y;
		}
	}
	
	public void render(){
		// Draw each of the background tiles
		background.draw(x, y);
		background.draw(x, height + y);
		background.draw(width, y);
		background.draw(width, height + y);
		background.draw(width, y - height);
		background.draw(x, y - height);
	}
}
