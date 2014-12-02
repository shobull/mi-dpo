package cz.fit.dpo.mvcshooter.model;

import cz.fit.dpo.mvcshooter.model.abstractfactory.IBasicFactory;
import cz.fit.dpo.mvcshooter.model.entities.*;
import cz.fit.dpo.mvcshooter.model.enums.ECannonMode;
import cz.fit.dpo.mvcshooter.model.memento.ModelMemento;

import java.time.LocalTime;
import java.util.*;

/**
 * Pro vzor Memento je tato trida Originator
 *
 * @author Ondrej Stuchlik
 */
public class Model {

	private IBasicFactory factory;

	private List<ModelObserver> observers = new ArrayList();

	private Timer timer;

	private Cannon cannon;

	private List<Enemy> enemies = new ArrayList<Enemy>();

	private List<Collision> collisions = new ArrayList<Collision>();

	private List<Missile> missiles = new ArrayList<Missile>();

	private int gravity = ModelConfig.DEFAULT_GRAVITY;

	private GameStats gameStats;

	private int timeTicks = 0;

	public Model(IBasicFactory factory) {
		this.factory = factory;
		initTimer();
		initDefaultObjects();
	}

	// ####################### model controlling #########################
	public void moveCannonUp() {
		getCannon().moveUp();
		notifyObservers();
	}

	public void moveCannonDown() {
		getCannon().moveDown();
		notifyObservers();
	}

	public void angleUp() {
		getCannon().angleUp();
	}

	public void angleDown() {
		getCannon().angleDown();
	}

	public void forceUp() {
		cannon.forceUp();
	}

	public void forceDown() {
		cannon.forceDown();
	}

	public void gravityUp() {
		gravity += ModelConfig.GRAVITY_STEP;
	}

	public void gravityDown() {
		gravity -= ModelConfig.GRAVITY_STEP;
	}

	public void shootMissile() {
		ArrayList<Missile> newMissiles = cannon.shootMissile(factory);
		missiles.addAll(newMissiles);
		notifyObservers();
	}

	public void changeShootingMode() {
		// Zmena vnitrniho stavu Cannon (vzor State)
		cannon.changeShootingMode();
	}

	public ModelMemento saveToMemento() {
		System.out.println("Ukladam hru. (" + this + ")");
		return new ModelMemento(this);
	}

	public void restoreFromMemento(ModelMemento modelMemento) {
		this.enemies = new ArrayList<Enemy>();
		for (Enemy enemy : modelMemento.getEnemies()) {
			Enemy restoredEnemy = enemy.copy();
			this.enemies.add(restoredEnemy);
		}
		this.collisions = new ArrayList<Collision>();
		for (Collision collision : modelMemento.getCollisions()) {
			Collision restoredCollision = collision.copy();
			this.collisions.add(restoredCollision);
		}
		this.missiles = new ArrayList<Missile>();
		for (Missile missile : modelMemento.getMissiles()) {
			Missile resotredMissile = missile.copy();
			this.missiles.add(resotredMissile);
		}

		this.gameStats = modelMemento.getGameStats().copy();
		this.gravity = modelMemento.getGravity();

		System.out.println("Obnovuji stav hry. (" + this + ")");
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

	public GameStats getGameStats() {
		return gameStats;
	}

	public int getPlaygroundWidth() {
		return ModelConfig.PLAYGROUND_WIDTH;
	}

	public int getPlaygroundHeight() {
		return ModelConfig.PLAYGROUND_HEIGHT;
	}

	public int getGravity() {
		return gravity;
	}

	public IBasicFactory getFactory() {
		return factory;
	}

	public int getTimeTicks() {
		return timeTicks;
	}

	public String getTimeString() {
		return LocalTime.MIN.plusSeconds(timeTicks).toString();
	}

	public String getCannonStateString() {
		if (cannon.getMode().equals(ECannonMode.DOUBLE_SHOOTING_MODE)) {
			return "DOUBLE_SHOOTING_MODE";
		} else if (cannon.getMode().equals(ECannonMode.SINGLE_SHOOTING_MODE)) {
			return "SINGLE_SHOOTING_MODE";
		} else {
			return "-nenastaveno-";
		}
	}

	public List<GameObject> getAllGameObjects() {
		List<GameObject> gameObjects = new ArrayList<GameObject>();
		gameObjects.add(getCannon());
		gameObjects.addAll(getCollisions());
		gameObjects.addAll(getMissiles());
		gameObjects.addAll(getEnemies());
		gameObjects.add(getGameStats());
		return gameObjects;
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
				addNewEnemy();
			}
		}, 0, 5000);

		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				timeTicks++;
			}
		}, 0, 1000);
	}

	private void initDefaultObjects() {
		cannon = new Cannon();
		gameStats = new GameStats(this);
	}

	// ################################## private logic ##################################
	private void moveObjects() {
		refreshMissiles();
		refreshEnemies();
		refreshCollisions();
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
					gameStats.addScore();
					enemyIterator.remove();
					missileIterator.remove();
				}
			}
		}
	}

	private void refreshCollisions() {
		for (Iterator<Collision> collisionIterator = collisions.iterator(); collisionIterator.hasNext(); ) {
			Collision collision = collisionIterator.next();
			collision.decreaseRemainingTime();
			if (!collision.isVisible()) {
				collisionIterator.remove();
			}
		}
	}

	private void refreshEnemies() {
		boolean removed = false;
		for (Iterator<Enemy> it = enemies.iterator(); it.hasNext(); ) {
			Enemy enemy = it.next();
			enemy.move();
			if (!enemy.isVisible()) {
				it.remove();
				removed = true;
			}
		}
		if (removed) {
			addNewEnemy();
		}
	}

	private void refreshMissiles() {
		for (Iterator<Missile> it = missiles.iterator(); it.hasNext(); ) {
			Missile missile = it.next();
			missile.move(gravity);
			if (!missile.isVisible()) {
				gameStats.minusScore();
				it.remove();
			}
		}
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

		// Vytvareni nepritele na zaklade vzoru AbstractFactory
		Enemy enemy = factory.createEnemy(generatedX, generatedY);
		enemies.add(enemy);
		notifyObservers();
	}

	private void notifyObservers() {
		for (ModelObserver obs : observers) {
			obs.modelUpdated();
		}
	}

	@Override
	public String toString() {
		return "Model{" +
				", enemies=" + enemies.size() +
				", collisions=" + collisions.size() +
				", missiles=" + missiles.size() +
				", gravity=" + gravity +
				'}';
	}
}


