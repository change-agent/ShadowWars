/**
 * World class for the game.
 * Handles initialisation, input and rendering for the objects in the world.
 */

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class World {
	private Background background = new Background("res/space.png");
	private static final float PLAYER_X = 480, PLAYER_Y = 688, ENEMY_X = 64, ENEMY_Y = 32, CENTERING_OFFSET = 8, PLAYER_SPEED = 0.5f, LASER_SPEED = 3;
	private Player player = new Player("res/spaceship.png", PLAYER_X, PLAYER_Y, PLAYER_SPEED);
	private Laser laser = new Laser("res/shot.png", player.getX(), player.getY(), LASER_SPEED);
	private Enemy enemies = new Enemy("res/basic-enemy.png", ENEMY_X, ENEMY_Y);
	
	public World() throws SlickException {
		// Initialise list of enemies
		enemies.init();
	}
	public void update(Input input, int delta) throws SlickException {
		// Update all of the sprites in the game
		background.update(delta);
		player.update(input, delta);
		laser.update(input, delta, player.getX() + player.getCentre() - CENTERING_OFFSET, player.getY());
		enemies.update();
		
		// Check if the player has collided with, or shot any of the enemies
		for (Enemy enemy : Enemy.getEnemies()) {
			player.contactSprite(enemy);
			for (Laser currentShot : Laser.getLasers()) {
				enemy.contactSprite(currentShot, enemy);
			}
		}
	}
	public void render() {	
		// Draw all of the sprites in the game
		background.render();
		player.render();
		for (Enemy enemy : Enemy.getEnemies()) {
			enemy.render();
		}
		for (Laser currentShot : Laser.getLasers()) {
			currentShot.render();
		}
	}
}
