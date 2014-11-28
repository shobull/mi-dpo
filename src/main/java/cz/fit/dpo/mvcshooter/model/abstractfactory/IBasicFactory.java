package cz.fit.dpo.mvcshooter.model.abstractfactory;

import cz.fit.dpo.mvcshooter.model.entities.Enemy;

/**
 * Created by lubos on 28.11.2014.
 */
public interface IBasicFactory {

	public Enemy createEnemy();

	public void createMissile();

}
