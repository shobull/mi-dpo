package cz.fit.dpo.mvcshooter.model;

import cz.fit.dpo.mvcshooter.model.entities.Cannon;
import cz.fit.dpo.mvcshooter.model.entities.Collision;
import cz.fit.dpo.mvcshooter.model.entities.Enemy;
import cz.fit.dpo.mvcshooter.model.entities.Missile;
import cz.fit.dpo.mvcshooter.model.strategy.RealisticMovementStrategy;

import java.util.*;

/**
 * @author Ondrej Stuchlik
 */
public class Model {

	private Cannon cannon;

	private List<ModelObserver> observers = new ArrayList();

	private Timer timer;

	private int gravity = ModelConfig.DEFAULT_GRAVITY;

	private int score = 0;

	private List<Enemy> enemies = new ArrayList<Enemy>();

	private List<Collision> collisions = new ArrayList<Collision>();

	private List<Missile> missiles = new ArrayList<Missile>();

	public Model() {
		initTimer();
		initDefaultObjects();
	}

	// ####################### model controlling #########################
	public void moveCannonUp() {
		cannon.moveUp();
		notifyObservers();
	}

	public void moveCannonDown() {
		cannon.moveDown();
		notifyObservers();
	}

	public void angleUp() {
		cannon.angleUp();
	}

	public void angleDown() {
		cannon.angleDown();
	}

	public void forceUp() {
		cannon.forceUp();
	}

	public void forceDown() {
		cannon.forceDown();
	}

	/**
	 * Prida nepritele na vygenerovanou pozici
	 */
	private void addNewEnemy() {
		if (enemies.size() == ModelConfig.ENEMIES_COUNT) {
			return;
		}

		int generatedX, generatedY;

		// Aby se nepritel nemohl respawnovat primo pred prakem - alespon 100px od praku
		generatedX = (int) (Math.random() * (ModelConfig.PLAYGROUND_WIDTH - 100)) + 100;
		generatedY = (int) (Math.random() * ModelConfig.PLAYGROUND_HEIGHT);

		enemies.add(new Enemy(generatedX, generatedY));
		notifyObservers();
	}

	/**
	 * Odstrani nepratele, kterym vyprsel cas pro zivot
	 */
	private void removeDeadEnemies() {
		for (Iterator<Enemy> enemyIterator = enemies.iterator(); enemyIterator.hasNext(); ) {
			Enemy enemy = enemyIterator.next();
			enemy.decreaseRemainingTime();
			if (!enemy.isAlive()) {
				enemyIterator.remove();
			}
		}
	}

	private void removeCollisions() {
		for (Iterator<Collision> collisionIterator = collisions.iterator(); collisionIterator.hasNext(); ) {
			Collision collision = collisionIterator.next();
			collision.decreaseRemainingTime();
			if (!collision.isVisible()) {
				collisionIterator.remove();
			}
		}
	}

	public void shootMissile() {
		Missile missile = new Missile(cannon.getX(), cannon.getY(), cannon.getAngle(), cannon.getForce());
		// Navrhovy vzor Strategy - klient si zvoli, jaka strategie se pouzije
		missile.setIMovementStrategy(new RealisticMovementStrategy());
		missiles.add(missile);
		notifyObservers();
	}

	public void changeShootingMode() {
		cannon.changeShootingMode();
	}

	// ####################### getting data and registering #########################
	public void registerObserver(ModelObserver observer) {
		observers.add(observer);
	}

	public Cannon getCannon() {
		return cannon;
	}

	public List<Enemy> getEnemies() {
		return enemies;
	}

	public List<Missile> getMissiles() {
		return missiles;
	}

	public List<Collision> getCollisions() {
		return collisions;
	}

	public int getPlaygroundWidth() {
		return ModelConfig.PLAYGROUND_WIDTH;
	}

	public int getPlaygroundHeight() {
		return ModelConfig.PLAYGROUND_HEIGHT;
	}

	// ############################### private initialization ###############################
	private void initTimer() {
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				moveObjects();
			}
		}, 0, ModelConfig.TICK_TIME);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				removeDeadEnemies();
				addNewEnemy();
			}
		}, 0, 5000);

		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				removeCollisions();
			}
		}, 0, 1000);
	}

	private void initDefaultObjects() {
		cannon = new Cannon();
	}

	// ################################## private logic ##################################
	private void moveObjects() {
		// todo implement
		refreshMissiles();
		checkCollisions();

		notifyObservers();
	}

	private void checkCollisions() {
		for (Iterator<Enemy> enemyIterator = enemies.iterator(); enemyIterator.hasNext(); ) {
			Enemy enemy = enemyIterator.next();

			for (Iterator<Missile> missileIterator = missiles.iterator(); missileIterator.hasNext(); ) {
				Missile missile = missileIterator.next();

				if (missile.collidesWith(enemy)) {
					collisions.add(new Collision(enemy.getX(), enemy.getY()));
					score++;
					enemyIterator.remove();
					missileIterator.remove();
				}
			}
		}
	}

	private void refreshMissiles() {
		for (Iterator<Missile> it = missiles.iterator(); it.hasNext(); ) {
			Missile missile = it.next();
			missile.move(gravity);
			if (!missile.isVisible()) {
				it.remove();
			}
		}
	}

	private void notifyObservers() {
		for (ModelObserver obs : observers) {
			obs.modelUpdated();
		}
	}

}
