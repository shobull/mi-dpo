package cz.fit.dpo.mvcshooter.model.entities;

import cz.fit.dpo.mvcshooter.model.ModelConfig;

/**
 * @author Ondrej Stuchlik
 */
public class Cannon extends GameObject {

	private int angle = ModelConfig.CANNON_DEFAULT_ANGLE;

	private int force = ModelConfig.CANNON_DEFAULT_FORCE;

	public Cannon() {
		super(ModelConfig.CANNON_X, ModelConfig.CANNON_DEFAULT_Y);
	}

	public void moveUp() {
		if (y >= ModelConfig.CANNON_TOP_MARGIN) {
			y -= ModelConfig.CANNON_MOVE_STEP;
		}
	}

	public void moveDown() {
		if (y <= ModelConfig.PLAYGROUND_HEIGHT - ModelConfig.CANNON_BOTTOM_MARGIN) {
			y += ModelConfig.CANNON_MOVE_STEP;
		}
	}

	public int getAngle() {
		return angle;
	}

	public int getForce() {
		return force;
	}

	public void angleUp() {
		if (angle + ModelConfig.CANNON_AIM_STEP < ModelConfig.CANNON_MAX_UP_ANGLE) {
			angle += ModelConfig.CANNON_AIM_STEP;
		}
		System.out.println("Kanon nastaven na " + angle + " st.");
	}

	public void angleDown() {
		if (angle - ModelConfig.CANNON_AIM_STEP > ModelConfig.CANNON_MAX_DOWN_ANGLE) {
			angle -= ModelConfig.CANNON_AIM_STEP;
		}
		System.out.println("Kanon nastaven na " + angle + " st.");
	}

	public void forceUp() {
		if (force + ModelConfig.CANNON_FORCE_STEP <= ModelConfig.CANNON_MAX_FORCE) {
			force += ModelConfig.CANNON_FORCE_STEP;
		}
		System.out.println("Sila kanonu nastavena na " + force + ".");
	}

	public void forceDown() {
		if (force - ModelConfig.CANNON_FORCE_STEP >= ModelConfig.CANNON_MIN_FORCE) {
			force -= ModelConfig.CANNON_FORCE_STEP;
		}
		System.out.println("Sila kanonu nastavena na " + force + ".");
	}
}
