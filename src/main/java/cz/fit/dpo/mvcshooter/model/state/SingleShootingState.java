package cz.fit.dpo.mvcshooter.model.state;

import cz.fit.dpo.mvcshooter.model.abstractfactory.IBasicFactory;
import cz.fit.dpo.mvcshooter.model.entities.Cannon;
import cz.fit.dpo.mvcshooter.model.entities.Missile;

import java.util.ArrayList;

/**
 * Vzor State - implementace strely, pokud je atribut (vnitrni stav) entity Cannon nastaven na SingleShootingState.
 *
 * @author Lubos Palisek
 */
public class SingleShootingState implements IShootingState {

	@Override
	public ArrayList<Missile> shootMissile(Cannon cannon, IBasicFactory factory) {
		ArrayList<Missile> missiles = new ArrayList<Missile>();

		// Vytvareni strel na zaklade vzoru AbstractFactory
		Missile missile = factory.createMissile(cannon.getX(), cannon.getY(), cannon.getAngle(), cannon.getForce());
		missiles.add(missile);

		return missiles;
	}
}
