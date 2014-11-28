package cz.fit.dpo.mvcshooter.controller;

import cz.fit.dpo.mvcshooter.model.Model;
import cz.fit.dpo.mvcshooter.view.MainWindow;

import java.awt.event.KeyEvent;

/**
 * @author Ondrej Stuchlik
 */
public class Controller {

	private Model model;

	private MainWindow view;

	public Controller(Model model) {
		this.model = model;
	}

	public void keyPressed(KeyEvent evt) {
		switch (evt.getKeyCode()) {
			case KeyEvent.VK_DOWN:
				model.moveCannonDown();
				break;
			case KeyEvent.VK_UP:
				model.moveCannonUp();
				break;
			case KeyEvent.VK_LEFT:
				model.angleUp();
				break;
			case KeyEvent.VK_RIGHT:
				model.angleDown();
				break;
			case KeyEvent.VK_SPACE:
				model.shootMissile();
				break;
			case KeyEvent.VK_F1:
				view.showHelp();
				break;

			default:
				if ('+' == evt.getKeyChar()) {
					model.forceUp();
					break;
				} else if ('-' == evt.getKeyChar()) {
					model.forceDown();
					break;
				}
				System.out.println("cokoli " + evt.getKeyChar());
				break;
		}
	}

	public void setView(MainWindow view) {
		this.view = view;
	}


}
