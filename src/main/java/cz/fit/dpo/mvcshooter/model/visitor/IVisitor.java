package cz.fit.dpo.mvcshooter.model.visitor;

import cz.fit.dpo.mvcshooter.model.entities.Cannon;
import cz.fit.dpo.mvcshooter.model.entities.Collision;
import cz.fit.dpo.mvcshooter.model.entities.Enemy;
import cz.fit.dpo.mvcshooter.model.entities.Missile;

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
}
