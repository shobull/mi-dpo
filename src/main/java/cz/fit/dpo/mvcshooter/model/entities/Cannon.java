package cz.fit.dpo.mvcshooter.model.entities;

import cz.fit.dpo.mvcshooter.model.ModelConfig;
import cz.fit.dpo.mvcshooter.model.abstractfactory.IBasicFactory;
import cz.fit.dpo.mvcshooter.model.enums.ECannonMode;
import cz.fit.dpo.mvcshooter.model.state.DoubleShootingState;
import cz.fit.dpo.mvcshooter.model.state.IShootingState;
import cz.fit.dpo.mvcshooter.model.state.SingleShootingState;
import cz.fit.dpo.mvcshooter.model.visitor.IVisitor;

import java.util.ArrayList;

/**
 * @author Ondrej Stuchlik
 */
public class Cannon extends GameObject {

	private int angle = ModelConfig.CANNON_DEFAULT_ANGLE;

	private int force = ModelConfig.CANNON_DEFAULT_FORCE;

	private ECannonMode mode = ECannonMode.SINGLE_SHOOTING_MODE;

	private IShootingState shootingState = new SingleShootingState();

	public Cannon() {
		super(ModelConfig.CANNON_X, ModelConfig.CANNON_DEFAULT_Y);
	}

	public int getAngle() {
		return angle;
	}

	public int getForce() {
		return force;
	}

	public ECannonMode getMode() {
		return mode;
	}

	public IShootingState getShootingState() {
		return shootingState;
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

	public void angleUp() {
		if (angle + ModelConfig.CANNON_AIM_STEP < ModelConfig.CANNON_MAX_UP_ANGLE) {
			angle += ModelConfig.CANNON_AIM_STEP;
		}
	}

	public void angleDown() {
		if (angle - ModelConfig.CANNON_AIM_STEP > ModelConfig.CANNON_MAX_DOWN_ANGLE) {
			angle -= ModelConfig.CANNON_AIM_STEP;
		}
	}

	public void forceUp() {
		if (force + ModelConfig.CANNON_FORCE_STEP <= ModelConfig.CANNON_MAX_FORCE) {
			force += ModelConfig.CANNON_FORCE_STEP;
		}
	}

	public void forceDown() {
		if (force - ModelConfig.CANNON_FORCE_STEP >= ModelConfig.CANNON_MIN_FORCE) {
			force -= ModelConfig.CANNON_FORCE_STEP;
		}
	}

	public ArrayList<Missile> shootMissile(IBasicFactory factory) {
		return this.shootingState.shootMissile(this, factory);
	}

	public void changeShootingMode() {
		if (ECannonMode.SINGLE_SHOOTING_MODE.equals(mode)) {
			this.mode = ECannonMode.DOUBLE_SHOOTING_MODE;
			this.shootingState = new DoubleShootingState();
		} else {
			this.mode = ECannonMode.SINGLE_SHOOTING_MODE;
			this.shootingState = new SingleShootingState();
		}
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		return "Cannon{" +
				"angle=" + angle +
				", force=" + force +
				", shootingState=" + shootingState +
				'}';
	}
}
