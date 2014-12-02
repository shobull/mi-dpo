package test;

import cz.fit.dpo.mvcshooter.model.abstractfactory.RealFactory;
import cz.fit.dpo.mvcshooter.model.entities.Cannon;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.spy;

/**
 * @author Lubos Palisek
 */
public class NumberOfShotsTest {

	/**
	 * Zkontroluje, zda se vystreli 1 strela pri double shooting mode
	 */
	@Test
	public void shootingTest() {
		Cannon cannon = spy(new Cannon());

		int shots = cannon.shootMissile(new RealFactory()).size();
		Assert.assertEquals(1, shots);

		cannon.changeShootingMode();

		shots = cannon.shootMissile(new RealFactory()).size();
		Assert.assertEquals(2, shots);
	}

}
