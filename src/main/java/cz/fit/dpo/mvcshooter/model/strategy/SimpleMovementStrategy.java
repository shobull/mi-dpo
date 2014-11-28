package cz.fit.dpo.mvcshooter.model.strategy;

import cz.fit.dpo.mvcshooter.model.entities.Coordinates;
import cz.fit.dpo.mvcshooter.model.entities.Missile;

/**
 * Created by lubos on 28.11.2014.
 */
public class SimpleMovementStrategy implements IMovementStrategy {

	public SimpleMovementStrategy() {
		System.out.println("-> Vystrelena strela se SimpleMovementStrategy!");
	}

	@Override
	public Coordinates move(int gravity, Missile missile) {
		// x = x0 + v0*t*cos(alpha)
		int x = (int) (missile.getFirstX() + missile.getForce() * missile.getTime() / 10 * Math.cos(Math.toRadians(missile.getAngle())));
		// y = y0 + v0*t*sin(alpha) - 1/2*g*t^2
		int y = (int) (missile.getFirstY() - (missile.getForce() * missile.getTime() / 10 * Math.sin(Math.toRadians(missile.getAngle()))) + (0.5 * gravity * missile.getTime() / 10 * missile.getTime() / 10));
		return new Coordinates(x, y);
	}
}
