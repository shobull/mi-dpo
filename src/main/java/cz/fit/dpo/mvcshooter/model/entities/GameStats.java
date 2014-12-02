package cz.fit.dpo.mvcshooter.model.entities;

import cz.fit.dpo.mvcshooter.model.Model;
import cz.fit.dpo.mvcshooter.model.ModelConfig;
import cz.fit.dpo.mvcshooter.model.visitor.IVisitor;

/**
 * Entita pro zaznamenani skore
 *
 * @author Lubos Palisek
 */
public class GameStats extends GameObject {

	private Model model;

	private int score = 0;

	public GameStats(Model model) {
		super(ModelConfig.INFO_X, ModelConfig.INFO_Y);
		this.model = model;
	}

	public int getScore() {
		return score;
	}

	public void addScore() {
		score += ModelConfig.HIT_POINTS;
	}

	public void minusScore() {
		score--;
	}

	public GameStats copy() {
		GameStats gameStats = new GameStats(model);
		gameStats.score = this.getScore();
		return gameStats;
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Dosažené skóre: ").append(score);
		sb.append(",");
		sb.append(" čas: " + model.getTimeString());
		sb.append(",");
		sb.append(" úhel: " + model.getCannon().getAngle());
		sb.append(",");
		sb.append(" síla: " + model.getCannon().getForce());
		sb.append(",");
		sb.append(" stav kanonu: " + model.getCannonStateString());
		return sb.toString();
	}
}
