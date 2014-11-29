package cz.fit.dpo.mvcshooter.model.state;

import cz.fit.dpo.mvcshooter.model.abstractfactory.IBasicFactory;
import cz.fit.dpo.mvcshooter.model.entities.Cannon;
import cz.fit.dpo.mvcshooter.model.entities.Missile;

import java.util.ArrayList;

/**
 * Vzor State - interface definujici funkce pro typ strely
 *
 * @author Lubos Palisek
 */
public interface IShootingState {

	public ArrayList<Missile> shootMissile(Cannon cannon, IBasicFactory factory);

}
