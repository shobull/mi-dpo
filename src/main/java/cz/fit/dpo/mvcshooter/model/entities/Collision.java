package cz.fit.dpo.mvcshooter.model.entities;

/**
 * @author Lubos Palisek
 */
public class Collision extends GameObject {

	private int remainingTime = 3;

	public Collision(int x, int y) {
		super(x, y);
	}

	public void decreaseRemainingTime() {
		this.remainingTime--;
	}

	public boolean isVisible() {
		return this.remainingTime >= 0;
	}
}
