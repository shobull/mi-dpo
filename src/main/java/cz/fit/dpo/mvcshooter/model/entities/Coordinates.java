package cz.fit.dpo.mvcshooter.model.entities;

/**
 * Created by lubos on 28.11.2014.
 */
public class Coordinates {

	private int x;

	private int y;

	public Coordinates() {
	}

	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
