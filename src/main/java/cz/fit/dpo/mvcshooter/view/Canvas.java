package cz.fit.dpo.mvcshooter.view;

import cz.fit.dpo.mvcshooter.model.Model;
import cz.fit.dpo.mvcshooter.model.ModelObserver;
import cz.fit.dpo.mvcshooter.model.entities.Collision;
import cz.fit.dpo.mvcshooter.model.entities.Enemy;
import cz.fit.dpo.mvcshooter.model.entities.Missile;

import javax.swing.*;
import java.awt.*;

/**
 * @author Ondrej Stuchlik
 */
public class Canvas extends JPanel implements ModelObserver {

	GraphicsDrawer drawer = new GraphicsDrawer();

	Model model;

	public Canvas(int x, int y, int width, int height, Model model) {
		this.model = model;
		this.model.registerObserver(this);
		this.setBackground(Color.WHITE);
		this.setDoubleBuffered(true);
		this.setLocation(x, y);
		this.setPreferredSize(new Dimension(width, height));
		this.setVisible(true);
	}

	@Override
	public void modelUpdated() {
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		drawer.drawCannon(g, model.getCannon());

		for (Enemy e : model.getEnemies()) {
			drawer.drawEnemy(g, e);
		}

		for (Missile m : model.getMissiles()) {
			drawer.drawMissile(g, m);
		}

		for (Collision c : model.getCollisions()) {
			drawer.drawCollision(g, c);
		}
	}

}
