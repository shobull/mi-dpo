package cz.fit.dpo.mvcshooter.model.entities;

import cz.fit.dpo.mvcshooter.model.ModelConfig;
import cz.fit.dpo.mvcshooter.model.strategy.IMovementStrategy;
import cz.fit.dpo.mvcshooter.model.visitor.IVisitor;

/**
 * Entita pro strelu
 *
 * @author Lubos Palisek
 */
public class Missile extends GameObject {

	private int firstX;

	private int firstY;

	private int angle;

	private int force;

	private int time = 1;

	private IMovementStrategy movementStrategy;

	public Missile(int x, int y, int angle, int force) {
		super(x, y);
		this.firstX = x;
		this.firstY = y;
		this.angle = angle;
		this.force = force;
	}

	public int getFirstX() {
		return firstX;
	}

	public int getFirstY() {
		return firstY;
	}

	public int getAngle() {
		return angle;
	}

	public int getForce() {
		return force;
	}

	public int getTime() {
		return time;
	}

	public void setIMovementStrategy(IMovementStrategy movementStrategy) {
		this.movementStrategy = movementStrategy;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public void move(int gravity) {
		time++;
		Coordinates coordinates = movementStrategy.move(gravity, this);
		x = coordinates.getX();
		y = coordinates.getY();
	}

	public boolean isVisible() {
		return y <= ModelConfig.PLAYGROUND_HEIGHT && x <= ModelConfig.PLAYGROUND_WIDTH;
	}

	public Missile copy() {
		Missile missile = new Missile(firstX, firstY, angle, force);
		missile.setTime(time);
		missile.setIMovementStrategy(movementStrategy);
		return missile;
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		return "Missile{" +
				"firstX=" + firstX +
				", firstY=" + firstY +
				", angle=" + angle +
				", force=" + force +
				", time=" + time +
				'}';
	}
}
