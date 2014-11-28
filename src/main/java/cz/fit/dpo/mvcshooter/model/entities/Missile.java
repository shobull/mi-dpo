package cz.fit.dpo.mvcshooter.model.entities;

import cz.fit.dpo.mvcshooter.model.ModelConfig;

/**
 * User: Lubos Palisek
 * Date: 18. 10. 2014
 */
public class Missile extends GameObject {

	private int firstX;

	private int firstY;

	private int angle;

	private int force;

	private int time = 1;

	public Missile(int x, int y, int angle, int force) {
		super(x, y);
		this.firstX = x;
		this.firstY = y;
		this.angle = angle;
		this.force = force;
	}

	public void move(int gravity) {
		time++;
		// x = x0 + v0*t*cos(alpha)
		x = (int) (this.firstX + force * time / 10 * Math.cos(Math.toRadians(angle)));
		// y = y0 + v0*t*sin(alpha) - 1/2*g*t^2
		y = (int) (this.firstY - (force * time / 10 * Math.sin(Math.toRadians(angle))) + (0.5 * gravity * time / 10 * time / 10));
	}

	public boolean isVisible() {
		return y <= ModelConfig.PLAYGROUND_HEIGHT && x <= ModelConfig.PLAYGROUND_WIDTH;
	}
}
