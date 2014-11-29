package cz.fit.dpo.mvcshooter.model.memento;

import cz.fit.dpo.mvcshooter.model.Model;

/**
 * Vzor Memento - Caretaker, ktery udrzuje ulozeny stav hry
 *
 * @author Lubos Palisek
 */
public class Caretaker {

	private ModelMemento memento;

	public void saveState(Model model) {
		memento = model.saveToMemento();
	}

	public void restoreState(Model model) {
		if (memento == null) {
			System.out.println("Stav hry jeste nebyl ulozen.");
			return;
		}
		model.restoreFromMemento(memento);
	}

}
