package cz.fit.dpo.mvcshooter.controller;

import cz.fit.dpo.mvcshooter.model.Model;
import cz.fit.dpo.mvcshooter.model.memento.Caretaker;
import cz.fit.dpo.mvcshooter.view.MainWindow;

import java.awt.event.KeyEvent;

/**
 * @author Ondrej Stuchlik
 */
public class Controller {

	private Model model;

	private MainWindow view;

	private Caretaker caretaker = new Caretaker();

	public Controller(Model model) {
		this.model = model;
	}

	public void keyPressed(KeyEvent evt) {
		System.out.println("TEST");
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
			case KeyEvent.VK_Q:
				System.out.println("TEST 2");
				model.changeShootingMode();
				break;
			case KeyEvent.VK_G:
				model.gravityUp();
				break;
			case KeyEvent.VK_H:
				model.gravityDown();
				break;
			case KeyEvent.VK_F1:
				view.showHelp();
				break;
			case KeyEvent.VK_F5:
				caretaker.saveState(model);
				break;
			case KeyEvent.VK_F6:
				caretaker.restoreState(model);
				break;
			default:
				if ('+' == evt.getKeyChar()) {
					model.forceUp();
					break;
				} else if ('-' == evt.getKeyChar()) {
					model.forceDown();
					break;
				}
				break;
		}
	}

	public void setView(MainWindow view) {
		this.view = view;
	}


}
