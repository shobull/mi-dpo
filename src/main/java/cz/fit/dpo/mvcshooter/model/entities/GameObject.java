package cz.fit.dpo.mvcshooter.model.entities;

import cz.fit.dpo.mvcshooter.model.ModelConfig;
import cz.fit.dpo.mvcshooter.model.visitor.IAcceptable;
import cz.fit.dpo.mvcshooter.model.visitor.IVisitor;

/**
 * @author Ondrej Stuchlik
 */
public abstract class GameObject implements IAcceptable {

	protected int x, y;

	public GameObject(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public abstract void accept(IVisitor visitor);

	public boolean collidesWith(GameObject anotherObject) {
		return Math.abs(this.x - anotherObject.x) < ModelConfig.COLLISION_MARGIN
				&& Math.abs(this.y - anotherObject.y) < ModelConfig.COLLISION_MARGIN;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		GameObject that = (GameObject) o;

		if (x != that.x) return false;
		if (y != that.y) return false;

		return true;
	}

	@Override
	public String toString() {
		return "GameObject{" +
				"x=" + x +
				", y=" + y +
				'}';
	}
}
