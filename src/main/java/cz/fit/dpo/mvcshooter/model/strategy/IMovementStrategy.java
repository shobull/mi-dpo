package cz.fit.dpo.mvcshooter.model.strategy;

import cz.fit.dpo.mvcshooter.model.entities.Coordinates;
import cz.fit.dpo.mvcshooter.model.entities.Missile;

/**
 * Vzor Strategy - interface definujici funkce pro pohyb strely
 *
 * @author Lubos Palisek
 */
public interface IMovementStrategy {

	public Coordinates move(int gravity, Missile missile);

}
