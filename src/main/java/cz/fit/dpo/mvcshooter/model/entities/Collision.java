package cz.fit.dpo.mvcshooter.model.entities;

import cz.fit.dpo.mvcshooter.model.ModelConfig;
import cz.fit.dpo.mvcshooter.model.visitor.IVisitor;

/**
 * @author Lubos Palisek
 */
public class Collision extends GameObject {

	private int remainingTime = ModelConfig.COLLISION_LIVE_TIME;

	public Collision(int x, int y) {
		super(x, y);
	}

	public void setRemainingTime(int remainingTime) {
		this.remainingTime = remainingTime;
	}

	public void decreaseRemainingTime() {
		this.remainingTime--;
	}

	public boolean isVisible() {
		return this.remainingTime >= 0;
	}

	public Collision copy() {
		Collision collision = new Collision(x, y);
		collision.setRemainingTime(remainingTime);
		return collision;
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		return "Collision{" +
				"remainingTime=" + remainingTime +
				'}';
	}
}
