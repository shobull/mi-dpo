package cz.fit.dpo.mvcshooter.model.state;

import cz.fit.dpo.mvcshooter.model.ModelConfig;
import cz.fit.dpo.mvcshooter.model.abstractfactory.IBasicFactory;
import cz.fit.dpo.mvcshooter.model.entities.Cannon;
import cz.fit.dpo.mvcshooter.model.entities.Missile;

import java.util.ArrayList;

/**
 * Vzor State - implementace strely, pokud je atribut (vnitrni stav) entity Cannon nastaven na DoubleShootingState.
 *
 * @author Lubos Palisek
 */
public class DoubleShootingState implements IShootingState {

	@Override
	public ArrayList<Missile> shootMissile(Cannon cannon, IBasicFactory factory) {
		ArrayList<Missile> missiles = new ArrayList<Missile>();

		int angle1 = cannon.getAngle() + ModelConfig.CANNON_AIM_STEP;
		int angle2 = cannon.getAngle() - ModelConfig.CANNON_AIM_STEP;

		// Vytvareni strel na zaklade vzoru AbstractFactory
		Missile missile1 = factory.createMissile(cannon.getX(), cannon.getY(), angle1, cannon.getForce());
		Missile missile2 = factory.createMissile(cannon.getX(), cannon.getY(), angle2, cannon.getForce());
		missiles.add(missile1);
		missiles.add(missile2);

		return missiles;
	}
}
