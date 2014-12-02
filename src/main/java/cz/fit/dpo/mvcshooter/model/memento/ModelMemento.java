package cz.fit.dpo.mvcshooter.model.memento;

import cz.fit.dpo.mvcshooter.model.Model;
import cz.fit.dpo.mvcshooter.model.ModelConfig;
import cz.fit.dpo.mvcshooter.model.entities.Collision;
import cz.fit.dpo.mvcshooter.model.entities.Enemy;
import cz.fit.dpo.mvcshooter.model.entities.GameStats;
import cz.fit.dpo.mvcshooter.model.entities.Missile;

import java.util.ArrayList;
import java.util.List;

/**
 * Vzor Memento - kopie objektu Model, pro ukladani jeho stavu
 *
 * @author Lubos Palisek
 */
public class ModelMemento {

	private List<Enemy> enemies = new ArrayList<Enemy>();

	private List<Collision> collisions = new ArrayList<Collision>();

	private List<Missile> missiles = new ArrayList<Missile>();

	private int gravity = ModelConfig.DEFAULT_GRAVITY;

	private GameStats gameStats;

	public ModelMemento(Model model) {
		for (Enemy enemy : model.getEnemies()) {
			this.enemies.add(enemy.copy());
		}
		for (Collision collision : model.getCollisions()) {
			this.collisions.add(collision.copy());
		}
		for (Missile missile : model.getMissiles()) {
			this.missiles.add(missile.copy());
		}

		this.gameStats = model.getGameStats().copy();
		this.gravity = model.getGravity();

		System.out.println("Stav hry ulozen. (" + this.getGameStats().getScore() + ").");
	}

	public List<Enemy> getEnemies() {
		return enemies;
	}

	public List<Collision> getCollisions() {
		return collisions;
	}

	public List<Missile> getMissiles() {
		return missiles;
	}

	public int getGravity() {
		return gravity;
	}

	public GameStats getGameStats() {
		return gameStats;
	}
}
