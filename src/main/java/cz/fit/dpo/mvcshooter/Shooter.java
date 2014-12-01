package cz.fit.dpo.mvcshooter;

import cz.fit.dpo.mvcshooter.controller.Controller;
import cz.fit.dpo.mvcshooter.model.Model;
import cz.fit.dpo.mvcshooter.model.abstractfactory.IBasicFactory;
import cz.fit.dpo.mvcshooter.model.abstractfactory.SimpleFactory;
import cz.fit.dpo.mvcshooter.view.MainWindow;

import javax.swing.*;

/**
 * @author stue
 */
public class Shooter {

	public static void main(String[] args) {
		IBasicFactory factory = new SimpleFactory();
//		IBasicFactory factory = new RealFactory();

		final Model model = new Model(factory);
		final Controller controller = new Controller(model);

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new MainWindow(model, controller).setVisible(true);
			}
		});
	}
}
