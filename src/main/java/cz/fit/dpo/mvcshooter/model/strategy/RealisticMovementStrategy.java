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

		double xTemp = Math.acos(Math.toRadians(missile.getAngle())) * (missile.getTime() * force);
		int x = (int) (missile.getFirstX() + xTemp);

		double yTemp = Math.asin(Math.toRadians(missile.getAngle())) * (missile.getTime() * force);
		int y = (int) (missile.getFirstY() + yTemp);

		return new Coordinates(x, y);
	}
}
