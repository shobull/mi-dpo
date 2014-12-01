package cz.fit.dpo.mvcshooter.model.entities;

import cz.fit.dpo.mvcshooter.model.visitor.IVisitor;

/**
 * Zakladni abstraktni entita pro nepratele. Implementuje chovani, ktere je stejne pro oba typy nepratel
 *
 * @author Lubos Palisek
 */
public abstract class Enemy extends GameObject {

	/**
	 * Obrazek nepritele
	 */
	protected int type;

	protected int time = 1;

	public Enemy(int x, int y) {
		super(x, y);
		this.type = (int) Math.round(Math.random());
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public abstract void move();

	public abstract boolean isVisible();

	public abstract Enemy copy();


	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		return "Enemy{" +
				"type=" + type +
				", time=" + time +
				'}';
	}
}
