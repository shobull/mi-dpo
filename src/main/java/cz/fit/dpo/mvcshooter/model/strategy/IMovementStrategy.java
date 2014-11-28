package cz.fit.dpo.mvcshooter.model.strategy;

import cz.fit.dpo.mvcshooter.model.entities.Coordinates;
import cz.fit.dpo.mvcshooter.model.entities.Missile;

/**
 * Trida pro navrhovy vzor Strategy - ovlivnuje let strely
 *
 * @author Lubos Palisek
 */
public interface IMovementStrategy {

	public Coordinates move(int gravity, Missile missile);

}
