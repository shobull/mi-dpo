package test;

import cz.fit.dpo.mvcshooter.model.Model;
import cz.fit.dpo.mvcshooter.model.abstractfactory.SimpleEnemy;
import cz.fit.dpo.mvcshooter.model.entities.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Lubos Palisek
 */
public class NumberOfGameObjectsTest {

	@Test
	public void gameObjectsTest() {
		Model model = mock(Model.class);

		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		for (int i = 0; i < 5; i++) {
			Enemy e = mock(SimpleEnemy.class);
			enemies.add(e);
		}
		when(model.getEnemies()).thenReturn(enemies);

		ArrayList<Missile> missiles = new ArrayList<Missile>();
		for (int i = 0; i < 3; i++) {
			Missile m = mock(Missile.class);
			missiles.add(m);
		}
		when(model.getMissiles()).thenReturn(missiles);

		when(model.getCollisions()).thenReturn(new ArrayList<Collision>());

		GameStats gameStats = mock(GameStats.class);
		when(model.getGameStats()).thenReturn(gameStats);

		Cannon cannon = mock(Cannon.class);
		when(model.getCannon()).thenReturn(cannon);

		when(model.getAllGameObjects()).thenCallRealMethod();

		assertEquals(10, model.getAllGameObjects().size());
	}

}
