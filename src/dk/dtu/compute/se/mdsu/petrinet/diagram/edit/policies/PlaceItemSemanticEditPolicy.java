package dk.dtu.compute.se.mdsu.petrinet.diagram.edit.policies;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.common.core.command.ICompositeCommand;
import org.eclipse.gmf.runtime.common.core.util.Proxy;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.ArrangeRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

import dk.dtu.compute.se.mdsu.petrinet.Place;
import dk.dtu.compute.se.mdsu.petrinet.Token;
import dk.dtu.compute.se.mdsu.petrinet.diagram.edit.commands.ArcCreateCommand;
import dk.dtu.compute.se.mdsu.petrinet.diagram.edit.commands.ArcReorientCommand;
import dk.dtu.compute.se.mdsu.petrinet.diagram.edit.commands.TokenCreateCommand;
import dk.dtu.compute.se.mdsu.petrinet.diagram.edit.parts.ArcEditPart;
import dk.dtu.compute.se.mdsu.petrinet.diagram.edit.parts.TokenEditPart;
import dk.dtu.compute.se.mdsu.petrinet.diagram.part.PetrinetVisualIDRegistry;
import dk.dtu.compute.se.mdsu.petrinet.diagram.providers.PetrinetElementTypes;

/**
 * @generated
 */
public class PlaceItemSemanticEditPolicy extends PetrinetBaseItemSemanticEditPolicy {

	/**
	* @generated
	*/
	public PlaceItemSemanticEditPolicy() {
		super(PetrinetElementTypes.Place_2002);
	}

	/**
	* @generated
	*/
	protected Command getCreateCommand(CreateElementRequest req) {
		if (PetrinetElementTypes.Token_3001 == req.getElementType()) {
			return getGEFWrapper(new TokenCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

	/**
	* @generated
	*/
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		View view = (View) getHost().getModel();
		CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(getEditingDomain(), null);
		cmd.setTransactionNestingEnabled(false);
		for (Iterator<?> it = view.getTargetEdges().iterator(); it.hasNext();) {
			Edge incomingLink = (Edge) it.next();
			if (PetrinetVisualIDRegistry.getVisualID(incomingLink) == ArcEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
		}
		for (Iterator<?> it = view.getSourceEdges().iterator(); it.hasNext();) {
			Edge outgoingLink = (Edge) it.next();
			if (PetrinetVisualIDRegistry.getVisualID(outgoingLink) == ArcEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
		}
		EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
		if (annotation == null) {
			// there are indirectly referenced children, need extra commands: false
			addDestroyChildNodesCommand(cmd);
			addDestroyShortcutsCommand(cmd, view);
			// delete host element
			cmd.add(new DestroyElementCommand(req));
		} else {
			cmd.add(new DeleteCommand(getEditingDomain(), view));
		}
		return getGEFWrapper(cmd.reduce());
	}

	/**
	* @generated
	*/
	private void addDestroyChildNodesCommand(ICompositeCommand cmd) {
		View view = (View) getHost().getModel();
		for (Iterator<?> nit = view.getChildren().iterator(); nit.hasNext();) {
			Node node = (Node) nit.next();
			switch (PetrinetVisualIDRegistry.getVisualID(node)) {
			case TokenEditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(
						new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			}
		}
	}

	/**
	 * @generated
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		Command command = req.getTarget() == null ? getStartCreateRelationshipCommand(req)
				: getCompleteCreateRelationshipCommand(req);
		return command != null ? command : super.getCreateRelationshipCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getStartCreateRelationshipCommand(CreateRelationshipRequest req) {
		if (PetrinetElementTypes.Arc_4001 == req.getElementType()) {
			return getGEFWrapper(new ArcCreateCommand(req, req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(CreateRelationshipRequest req) {
		if (PetrinetElementTypes.Arc_4001 == req.getElementType()) {
			return getGEFWrapper(new ArcCreateCommand(req, req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * Returns command to reorient EClass based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientRelationshipCommand(ReorientRelationshipRequest req) {
		switch (getVisualID(req)) {
		case ArcEditPart.VISUAL_ID:
			return getGEFWrapper(new ArcReorientCommand(req));
		}
		return super.getReorientRelationshipCommand(req);
	}

	/**
	 * Overridden method to make sure that positions for automatically
	 * generated tokens in the request are updated before the Command is created.
	 * There are nicer ways of doing this; but for now this does the job.
	 * 
	 * @author ekki@dtu.dk
	 * @generated NOT
	 */
	@Override
	public Command getCommand(Request request) {
		if (request instanceof ArrangeRequest) {
			return UnexecutableCommand.INSTANCE;
		}

		if (request instanceof CreateViewRequest) {
			CreateViewRequest req = (CreateViewRequest) request;
			
			List<? extends ViewDescriptor> descriptors = req.getViewDescriptors();
			if (!descriptors.isEmpty()) {
				ViewDescriptor descriptor = descriptors.get(0);
				Object model = this.getHost().getModel();
				if (model instanceof Node) {
					Node node = (Node) model;
					Place place = (Place) node.getElement();
					int m = place.getTokens().size() - 1;

					Bounds bounds = (Bounds) (node).getLayoutConstraint();
					float px = bounds.getX();
					float py = bounds.getY();
					float prw = bounds.getWidth();
					float prh = bounds.getHeight();

					if (prw < 0) {
						prw = 20;
					} else {
						prw = prw / 2;
					}

					if (prh < 0) {
						prh = 20;
					} else {
						prh = prh / 2;
					}
					
					IAdaptable adaptable = descriptor.getElementAdapter();
					if (adaptable instanceof Proxy) {
						Object object = ((Proxy) adaptable).getRealObject();
						if (object instanceof Token) {
							boolean init = false;
							for (Adapter adapter: ((Token) object).eAdapters()) {
								String name = adapter.getClass().getName();
								if ("dk.dtu.compute.se.mdsu.petrinet.diagram.part.PetrinetDocumentProvider$ResourceSetModificationListener".equals(name)) {
									init = true;
									break;
								}
							}
							if (init) {
								px = 0;
								py = 0;
							}
						}
						
					}

					int x = (int) ((px + prw) - 5);
					int y = (int) ((py + prh) - 5);
					if (m != 0) {
						x = (int) (x + prw * (Math.random() - 0.5));
						y = (int) (y + prh * (Math.random() - 0.5));
					}

					
					Point point = new Point(x,y);
					IFigure figure = ((GraphicalEditPart) getHost()).getFigure();
					// translate the position to the absolute positions which
					// are needed in the command.
					figure.translateToAbsolute(point);
					req.setLocation(point);
				}
			}
		}

		return super.getCommand(request);
	}

}
