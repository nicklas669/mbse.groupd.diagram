package dk.dtu.compute.se.mdsu.petrinet.diagram.providers;

import org.eclipse.gmf.tooling.runtime.providers.DefaultEditPartProvider;

import dk.dtu.compute.se.mdsu.petrinet.diagram.edit.parts.PetrinetEditPart;
import dk.dtu.compute.se.mdsu.petrinet.diagram.edit.parts.PetrinetEditPartFactory;
import dk.dtu.compute.se.mdsu.petrinet.diagram.part.PetrinetVisualIDRegistry;

/**
 * @generated
 */
public class PetrinetEditPartProvider extends DefaultEditPartProvider {

	/**
	* @generated
	*/
	public PetrinetEditPartProvider() {
		super(new PetrinetEditPartFactory(), PetrinetVisualIDRegistry.TYPED_INSTANCE, PetrinetEditPart.MODEL_ID);
	}

}
