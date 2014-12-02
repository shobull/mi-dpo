package cz.fit.dpo.mvcshooter.view;

import cz.fit.dpo.mvcshooter.controller.Controller;
import cz.fit.dpo.mvcshooter.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Ondrej Stuchlik
 */
public class MainWindow extends JFrame {


	public MainWindow(Model model, final Controller controller) {
		try {
			controller.setView(this);
			Canvas view = new Canvas(
					0, 0, model.getPlaygroundWidth(), model.getPlaygroundHeight(), model);

			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setTitle("MyShooter");
			this.setResizable(false);

			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation(
					(int) (screen.getWidth() / 2 - 250),
					(int) (screen.getHeight() / 2 - 250));

			this.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent evt) {
					controller.keyPressed(evt);
				}
			});

			this.add(view);
			this.pack();
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		}
	}

	public void showHelp() {
		JOptionPane.showMessageDialog(this,
				"Ovládání: \n"
						+ "SPACE - střela \n"
						+ "Šipka doprava - úhel +10 \n"
						+ "Šipka doleva - úhel -10 \n"
						+ "+ (plus) - síla +1 \n"
						+ "- (minus) - síla -2 \n"
						+ "F5 - změna módu kanonu \n"
						+ "F6 - načtení stavu \n"
						+ "Q - změna módu kanonu \n"
						+ "G - gravitece +1 \n");
	}
}
