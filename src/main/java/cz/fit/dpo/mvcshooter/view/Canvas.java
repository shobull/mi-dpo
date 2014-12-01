package cz.fit.dpo.mvcshooter.view;

import cz.fit.dpo.mvcshooter.model.Model;
import cz.fit.dpo.mvcshooter.model.ModelObserver;
import cz.fit.dpo.mvcshooter.model.entities.GameObject;

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
		drawer.setGraphics(g);

		for (GameObject go : model.getAllGameObjects()) {
			go.accept(drawer);
		}
	}

}
