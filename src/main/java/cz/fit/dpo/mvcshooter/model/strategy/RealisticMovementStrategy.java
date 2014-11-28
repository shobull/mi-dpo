package cz.fit.dpo.mvcshooter.model.strategy;

import cz.fit.dpo.mvcshooter.model.entities.Coordinates;
import cz.fit.dpo.mvcshooter.model.entities.Missile;

/**
 * Created by lubos on 28.11.2014.
 */
public class RealisticMovementStrategy implements IMovementStrategy {

	public RealisticMovementStrategy() {
		System.out.println("-> Vystrelena strela s RealisticMovementStrategy!");
	}

	@Override
	public Coordinates move(int gravity, Missile missile) {
		int x = missile.getFirstX() + (missile.getTime() / 2) * missile.getFirstX();
		int y = missile.getFirstY();
		return new Coordinates(x, y);
	}
}
