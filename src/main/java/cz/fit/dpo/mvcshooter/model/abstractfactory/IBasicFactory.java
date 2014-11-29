package cz.fit.dpo.mvcshooter.model.abstractfactory;

import cz.fit.dpo.mvcshooter.model.entities.Enemy;
import cz.fit.dpo.mvcshooter.model.entities.Missile;

/**
 * Vzor AbstractFactory - zakladni factory definujici operace, ktere zavisi na factory
 * - vytvareni nepratel
 * - vytvareni strel
 *
 * @author Lubos Palisek
 */
public interface IBasicFactory {

	/**
	 * Vytvoreni nepritele
	 *
	 * @param x souradnice X
	 * @param y souradnice Y
	 * @return Entita reprezentujici vytvoreneho nepritele
	 */
	public Enemy createEnemy(int x, int y);

	/**
	 * Vytvoreni strely
	 *
	 * @param firstX souradnice X
	 * @param firstY souradnice Y
	 * @param angle  uhel, pod jakym je strela vypalena
	 * @param force  sila, s jakou je strela vypalena
	 * @return Entita reprezentujici vytvrenou strelu
	 */
	public Missile createMissile(int firstX, int firstY, int angle, int force);
}
