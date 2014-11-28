package cz.fit.dpo.mvcshooter.model.entities;

/**
 * User: Lubos Palisek
 * Date: 18. 10. 2014
 */
public class Enemy extends GameObject {

	/**
	 * image of enemy
	 */
	private int type;

	public Enemy(int x, int y) {
		super(x, y);
		type = (int) Math.round(Math.random());
	}

	public int getType() {
		return type;
	}
}
