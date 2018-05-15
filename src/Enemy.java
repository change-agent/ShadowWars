/**
 * Enemy subclass for the game.
 * Renders and handles each enemy sprite by initialising a list of enemies, and then iterating through them.
 */

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.SlickException;

public class Enemy extends Sprite {
	private static final int NUM_ENEMIES = 8;
	private static final float ENEMY_PX_GAP = 128;
	private static List<Enemy> enemies = new ArrayList<Enemy>();
	private boolean dead = false;
	
	public Enemy(String imageSrc, float x, float y) throws SlickException {
		super(imageSrc, x, y);
	}
	public void init() throws SlickException {
		// Initialise ArrayList of enemies
		final float WIDTH = getSprite().getCenterOfRotationX();
		Enemy firstEnemy = new Enemy("res/basic-enemy.png", getX() - WIDTH, getY());
		getEnemies().add(firstEnemy);
		for (int i = 1; i < NUM_ENEMIES; i++) {
			Enemy enemy = new Enemy("res/basic-enemy.png", getX() + ENEMY_PX_GAP*i - WIDTH, getY());
			getEnemies().add(enemy);
        }
	}
	public void update() {
		// If an enemy has been shot, remove it from the ArrayList and adjust its size accordingly
		for (int i = 0; i < getEnemies().size(); i++) {
			Enemy enemy = getEnemies().get(i);
            if (enemy.dead){
            	int lastEnemy = getEnemies().size()-1;
            	getEnemies().set(i, getEnemies().get(lastEnemy));
            	getEnemies().remove(lastEnemy);
            }
        }
	}
	public void contactSprite(Laser shot, Enemy enemy) {
		// If a laser shot hits an enemy, set the enemy to be dead
		try {
			if(getBoundingBox().intersects(shot.getBoundingBox())) {
				enemy.dead = true;
			}
		}
		catch (NullPointerException npe) {
			npe.printStackTrace();
		}
	}
	public static List<Enemy> getEnemies() {
		return enemies;
	}
}