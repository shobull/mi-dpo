package cz.fit.dpo.mvcshooter.model.abstractfactory;

import cz.fit.dpo.mvcshooter.model.ModelConfig;
import cz.fit.dpo.mvcshooter.model.entities.Enemy;

/**
 * Vzor AbstractFactory - Entita reprezentujici realneho nepritele (pohybuje se)
 *
 * @author Lubos Palisek
 */
public class RealEnemy extends Enemy {

	public RealEnemy(int x, int y) {
		super(x, 0);
	}

	/**
	 * Primitivni implementace padajiciho pohybu
	 */
	public void move() {
		time++;
		y = 0 + time;
	}

	public boolean isVisible() {
		return y <= ModelConfig.PLAYGROUND_HEIGHT && x <= ModelConfig.PLAYGROUND_WIDTH;
	}

}
