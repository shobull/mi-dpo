package cz.fit.dpo.mvcshooter.model.entities;

import cz.fit.dpo.mvcshooter.model.ModelConfig;

/**
 * Zakladni abstraktni entita pro nepratele. Implementuje chovani, ktere je stejne pro oba typy nepratel
 *
 * @author Lubos Palisek
 */
public abstract class Enemy extends GameObject {

	/**
	 * Obrazek nepritele
	 */
	private int type;

	protected int time = 1;

	public Enemy(int x, int y) {
		super(x, y);
		this.type = (int) Math.round(Math.random());
	}

	public abstract void move();

	public abstract boolean isVisible();

	public int getType() {
		return type;
	}

}
