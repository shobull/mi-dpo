package cz.fit.dpo.mvcshooter.model.entities;

/**
 * User: Lubos Palisek
 * Date: 18. 10. 2014
 */
public class Collision extends GameObject {

	private int remainingTime;

	public Collision(int x, int y) {
		super(x, y);
		this.remainingTime = 3;
	}

	public void decreaseRemainingTime() {
		this.remainingTime--;
	}

	public boolean isVisible() {
		return this.remainingTime >= 0;
	}
}
