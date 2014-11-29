package cz.fit.dpo.mvcshooter.model.abstractfactory;

import cz.fit.dpo.mvcshooter.model.entities.Missile;
import cz.fit.dpo.mvcshooter.model.strategy.RealisticMovementStrategy;

/**
 * Vzor AbstractFactory - implementace zakladni factory, ktera vytvari real objekty:
 * - pohybujici se nepratele
 * - strely s rovnym smerem
 *
 * @author Lubos Palisek
 */
public class RealFactory implements IBasicFactory {

	public RealFactory() {
	}

	@Override
	public RealEnemy createEnemy(int x, int y) {
		return new RealEnemy(x, y);
	}

	@Override
	public Missile createMissile(int firstX, int firstY, int angle, int force) {
		Missile missile = new Missile(firstX, firstY, angle, force);
		// Navrhovy vzor Strategy - klient si zvoli, jaka strategie se pouzije
		missile.setIMovementStrategy(new RealisticMovementStrategy());
		return missile;
	}
}
