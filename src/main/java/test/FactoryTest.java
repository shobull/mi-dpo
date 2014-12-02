package test;

import cz.fit.dpo.mvcshooter.model.abstractfactory.RealEnemy;
import cz.fit.dpo.mvcshooter.model.abstractfactory.RealFactory;
import cz.fit.dpo.mvcshooter.model.abstractfactory.SimpleEnemy;
import cz.fit.dpo.mvcshooter.model.abstractfactory.SimpleFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Lubos Palisek
 */
public class FactoryTest {

	@Test
	public void birdTest() {
		RealFactory factory = new RealFactory();
		RealEnemy realEnemy = new RealEnemy(0, 0);
		RealEnemy realEnemyFromFactory = factory.createEnemy(0, 0);
		assertEquals(realEnemy, realEnemyFromFactory);

		SimpleFactory sFactory = new SimpleFactory();
		SimpleEnemy simpleEnemy = new SimpleEnemy(0, 0);
		SimpleEnemy simpleEnemyFromFactory = sFactory.createEnemy(0, 0);
		assertEquals(simpleEnemy, simpleEnemyFromFactory);

		assertNotEquals(simpleEnemy, realEnemy);
		assertNotEquals(simpleEnemyFromFactory, realEnemyFromFactory);
	}

}
