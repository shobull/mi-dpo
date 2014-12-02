package cz.fit.dpo.mvcshooter.model.strategy;

import cz.fit.dpo.mvcshooter.model.entities.Coordinates;
import cz.fit.dpo.mvcshooter.model.entities.Missile;

/**
 * Vzor Strategy - implementace realistickeho pohybu (rovna strela)
 *
 * @author Lubos Palisek
 */
public class RealisticMovementStrategy implements IMovementStrategy {

	public RealisticMovementStrategy() {
		System.out.println("-> Vystrelena strela s RealisticMovementStrategy!");
	}

	@Override
	public Coordinates move(int gravity, Missile missile) {
		int force = (missile.getForce() / 4) == 0 ? 1 : (missile.getForce() / 4);

		int x = missile.getFirstX() + (int) (force * missile.getTime());
		int y = missile.getFirstY() - (int) (force * missile.getTime() * Math.toRadians(missile.getAngle()));

		return new Coordinates(x, y);
	}
}
