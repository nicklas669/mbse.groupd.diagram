
package dk.dtu.compute.se.mdsu.petrinet.diagram.part;

import java.util.Collections;

import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.tooling.runtime.part.DefaultLinkToolEntry;
import org.eclipse.gmf.tooling.runtime.part.DefaultNodeToolEntry;

import dk.dtu.compute.se.mdsu.petrinet.diagram.providers.PetrinetElementTypes;

/**
 * @generated
 */
public class PetrinetPaletteFactory {

	/**
	* @generated
	*/
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createPetrinet1Group());
	}

	/**
	* Creates "petrinet" palette tool group
	* @generated
	*/
	private PaletteContainer createPetrinet1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(Messages.Petrinet1Group_title);
		paletteContainer.setId("createPetrinet1Group"); //$NON-NLS-1$
		paletteContainer.add(createPlace1CreationTool());
		paletteContainer.add(createTransition2CreationTool());
		paletteContainer.add(createToken3CreationTool());
		paletteContainer.add(createArc4CreationTool());
		return paletteContainer;
	}

	/**
	* @generated
	*/
	private ToolEntry createPlace1CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.Place1CreationTool_title,
				Messages.Place1CreationTool_desc, Collections.singletonList(PetrinetElementTypes.Place_2002));
		entry.setId("createPlace1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(PetrinetElementTypes.getImageDescriptor(PetrinetElementTypes.Place_2002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createTransition2CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.Transition2CreationTool_title,
				Messages.Transition2CreationTool_desc, Collections.singletonList(PetrinetElementTypes.Transition_2001));
		entry.setId("createTransition2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(PetrinetElementTypes.getImageDescriptor(PetrinetElementTypes.Transition_2001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createToken3CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.Token3CreationTool_title,
				Messages.Token3CreationTool_desc, Collections.singletonList(PetrinetElementTypes.Token_3001));
		entry.setId("createToken3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(PetrinetElementTypes.getImageDescriptor(PetrinetElementTypes.Token_3001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createArc4CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(Messages.Arc4CreationTool_title,
				Messages.Arc4CreationTool_desc, Collections.singletonList(PetrinetElementTypes.Arc_4001));
		entry.setId("createArc4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(PetrinetElementTypes.getImageDescriptor(PetrinetElementTypes.Arc_4001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

}
