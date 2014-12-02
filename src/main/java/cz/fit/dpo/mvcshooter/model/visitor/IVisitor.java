package cz.fit.dpo.mvcshooter.model.visitor;

import cz.fit.dpo.mvcshooter.model.entities.*;

/**
 * Navrhovy vzor Visitor - Interface implementujici navstevu objektu
 *
 * @author Lubos Palisek
 */
public interface IVisitor {

	void visit(Missile missile);

	void visit(Enemy enemy);

	void visit(Cannon cannon);

	void visit(Collision collision);

	void visit(GameStats gameStats);
}
