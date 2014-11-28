package cz.fit.dpo.mvcshooter.model.entities;

import cz.fit.dpo.mvcshooter.model.ModelConfig;
import cz.fit.dpo.mvcshooter.model.enums.ECannonMode;
import cz.fit.dpo.mvcshooter.model.state.DoubleShootingState;
import cz.fit.dpo.mvcshooter.model.state.IShootingState;
import cz.fit.dpo.mvcshooter.model.state.SingleShootingState;

/**
 * @author Ondrej Stuchlik
 */
public class Cannon extends GameObject {

	private int angle = ModelConfig.CANNON_DEFAULT_ANGLE;

	private int force = ModelConfig.CANNON_DEFAULT_FORCE;

	private ECannonMode mode;

	private IShootingState shootingState;

	public Cannon() {
		super(ModelConfig.CANNON_X, ModelConfig.CANNON_DEFAULT_Y);
		mode = ECannonMode.SINGLE_SHOOTING_MODE;
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

	public void shootMissile() {
		this.shootingState.shootMissile();
	}

	public void changeShootingMode() {
		if (ECannonMode.SINGLE_SHOOTING_MODE.equals(mode)) {
			this.mode = ECannonMode.DOUBLE_SHOOTING_MODE;
			this.shootingState = new DoubleShootingState();
		} else {
			this.mode = ECannonMode.SINGLE_SHOOTING_MODE;
			this.shootingState = new SingleShootingState();
		}
		System.out.println("Zmena shooting mode na: " + this.mode);
	}
}
