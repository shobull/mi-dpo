package cz.fit.dpo.mvcshooter.view;

import cz.fit.dpo.mvcshooter.model.entities.*;
import cz.fit.dpo.mvcshooter.model.visitor.IVisitor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author Ondrej Stuchlik
 */
public class GraphicsDrawer implements IVisitor {

	private BufferedImage cannonImage;

	private BufferedImage enemyImage1;

	private BufferedImage enemyImage2;

	private BufferedImage missileImage;

	private BufferedImage collisionImage;

	private Graphics g;

	public GraphicsDrawer() {
		try {
			cannonImage = ImageIO.read(getClass().getResourceAsStream("/images/cannon.png"));
			enemyImage1 = ImageIO.read(getClass().getResourceAsStream("/images/enemy1.png"));
			enemyImage2 = ImageIO.read(getClass().getResourceAsStream("/images/enemy2.png"));
			missileImage = ImageIO.read(getClass().getResourceAsStream("/images/missile.png"));
			collisionImage = ImageIO.read(getClass().getResourceAsStream("/images/collision.png"));
		} catch (IOException ex) {
			ex.printStackTrace(System.err);
		}
	}


	public void setGraphics(Graphics graphics) {
		this.g = graphics;
	}

	@Override
	public void visit(Missile missile) {
		g.drawImage(missileImage,
				missile.getX(),
				missile.getY(), null);
	}

	@Override
	public void visit(Enemy enemy) {
		BufferedImage chosenEnemyImage = enemy.getType() == 0 ? enemyImage1 : enemyImage2;
		g.drawImage(chosenEnemyImage,
				enemy.getX(),
				enemy.getY(), null);
	}

	@Override
	public void visit(Cannon cannon) {
		g.drawImage(cannonImage,
				cannon.getX() - cannonImage.getWidth() / 2,
				cannon.getY(), null);
	}

	@Override
	public void visit(Collision collision) {
		g.drawImage(collisionImage,
				collision.getX(),
				collision.getY(), null);
	}

	@Override
	public void visit(GameStats gameStats) {
		g.drawString(gameStats.toString(),
				gameStats.getX(),
				gameStats.getY());
	}
}
