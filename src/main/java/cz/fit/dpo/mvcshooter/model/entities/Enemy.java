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

	/**
	 * Remaining time of life
	 */
	private int remainingTime;

	public Enemy(int x, int y) {
		super(x, y);
		this.type = (int) Math.round(Math.random());
		this.remainingTime = 3;
	}

	public int getType() {
		return type;
	}

	public void decreaseRemainingTime() {
		this.remainingTime--;
	}

	public boolean isAlive() {
		return this.remainingTime >= 0;
	}
}
