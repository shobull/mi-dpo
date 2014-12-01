package cz.fit.dpo.mvcshooter.model.visitor;

/**
 * Navrhovy vzor Visitor - Interface pro objekty, ktere prijimaji visitora
 *
 * @author Lubos Palisek
 */
public interface IAcceptable {

	public void accept(IVisitor visitor);
}
