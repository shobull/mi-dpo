package cz.fit.dpo.mvcshooter.model.abstractfactory;

import cz.fit.dpo.mvcshooter.model.entities.Enemy;

/**
 * Vzor AbstractFactory - Entita reprezenujici jednoducheho nepritele (staticky)
 *
 * @author Lubos Palisek
 */
public class SimpleEnemy extends Enemy {

	protected int remainingTime = 250;

	public SimpleEnemy(int x, int y) {
		super(x, y);
	}

	/**
	 * Implementace stani na miste :-)
	 */
	public void move() {
		remainingTime--;
	}

	public boolean isVisible() {
		return remainingTime >= 0;
	}

}
