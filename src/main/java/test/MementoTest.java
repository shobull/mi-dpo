package test;

import cz.fit.dpo.mvcshooter.model.Model;
import cz.fit.dpo.mvcshooter.model.entities.GameStats;
import cz.fit.dpo.mvcshooter.model.memento.ModelMemento;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Lubos Palisek
 */
public class MementoTest {

	@Test
	public void mementoTest() {
		Model model = mock(Model.class);
		GameStats gameStats = mock(GameStats.class);

		when(model.getGameStats()).thenReturn(gameStats);
		when(gameStats.getScore()).thenReturn(10000);
		when(gameStats.copy()).thenCallRealMethod();
		when(model.saveToMemento()).thenCallRealMethod();

		ModelMemento memento = model.saveToMemento();

		when(gameStats.getScore()).thenReturn(20000);

		assertEquals(10000, memento.getGameStats().getScore());
	}
}
