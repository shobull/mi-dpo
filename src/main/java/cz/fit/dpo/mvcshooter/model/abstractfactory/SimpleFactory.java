package cz.fit.dpo.mvcshooter.model.abstractfactory;

import cz.fit.dpo.mvcshooter.model.entities.Enemy;
import cz.fit.dpo.mvcshooter.model.entities.Missile;

/**
 * Created by lubos on 28.11.2014.
 */
public class SimpleFactory implements IBasicFactory {

	@Override
	public Enemy createEnemy() {
		return new SimpleEnemy();
	}

	@Override
	public void createMissile() {

	}
}
