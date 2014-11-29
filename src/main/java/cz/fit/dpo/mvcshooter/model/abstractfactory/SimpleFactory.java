package cz.fit.dpo.mvcshooter.model.abstractfactory;

import cz.fit.dpo.mvcshooter.model.entities.Missile;
import cz.fit.dpo.mvcshooter.model.strategy.SimpleMovementStrategy;

/**
 * Vzor AbstractFactory - implementace zakladni factory, ktera vytvari simple objekty:
 * - staticke nepratele
 * - strely s pohybem sikmeho vrhu
 *
 * @author Lubos Palisek
 */
public class SimpleFactory implements IBasicFactory {

	public SimpleFactory() {
	}

	@Override
	public SimpleEnemy createEnemy(int x, int y) {
		return new SimpleEnemy(x, y);
	}

	@Override
	public Missile createMissile(int firstX, int firstY, int angle, int force) {
		Missile missile = new Missile(firstX, firstY, angle, force);
		// Navrhovy vzor Strategy - klient si zvoli, jaka strategie se pouzije
		missile.setIMovementStrategy(new SimpleMovementStrategy());
		return missile;
	}
}
