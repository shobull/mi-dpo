package test;

import cz.fit.dpo.mvcshooter.model.Model;
import cz.fit.dpo.mvcshooter.model.ModelConfig;
import cz.fit.dpo.mvcshooter.model.entities.Cannon;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author Lubos Palisek
 */
public class MovingTest {

	@Test
	public void moveCannonTest() {
		Model model = mock(Model.class);
		Cannon cannon = new Cannon();

		when(model.getCannon()).thenReturn(cannon);
		doCallRealMethod().when(model).angleDown();
		doCallRealMethod().when(model).angleUp();

		assertEquals(ModelConfig.CANNON_DEFAULT_ANGLE, model.getCannon().getAngle());
		model.angleDown();
		assertEquals(ModelConfig.CANNON_DEFAULT_ANGLE - ModelConfig.CANNON_MOVE_STEP, model.getCannon().getAngle());
		model.angleUp();
		model.angleUp();
		assertEquals(ModelConfig.CANNON_DEFAULT_ANGLE + ModelConfig.CANNON_MOVE_STEP, model.getCannon().getAngle());
	}

}
