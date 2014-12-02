package cz.fit.dpo.mvcshooter.model.abstractfactory;

import cz.fit.dpo.mvcshooter.model.ModelConfig;
import cz.fit.dpo.mvcshooter.model.entities.Enemy;

/**
 * Vzor AbstractFactory - Entita reprezenujici jednoducheho nepritele (staticky)
 *
 * @author Lubos Palisek
 */
public class SimpleEnemy extends Enemy {

	protected int remainingTime = ModelConfig.ENEMY_LIVE_TIME;

	public SimpleEnemy(int x, int y) {
		super(x, y);
	}

	public void setRemainingTime(int remainingTime) {
		this.remainingTime = remainingTime;
	}

	/**
	 * Implementace stani na miste :-)
	 */
	@Override
	public void move() {
		remainingTime--;
	}

	@Override
	public boolean isVisible() {
		return remainingTime >= 0;
	}

	@Override
	public Enemy copy() {
		SimpleEnemy se = new SimpleEnemy(x, y);
		se.setTime(time);
		se.setType(type);
		se.setRemainingTime(remainingTime);
		return se;
	}

}
