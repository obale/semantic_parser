package to.networld.scrawler.interfaces;

import to.networld.scrawler.annotations.RDFEntity;
import to.networld.scrawler.annotations.RDFProperty;
import to.networld.scrawler.annotations.RDFProperty.Type;
import to.networld.scrawler.common.Ontologies;

/**
 * @author Alex Oberhauser
 *
 */
@RDFEntity(ontoURI = Ontologies.diveURI, concept = "Diver")
public interface IScubaDiveBuddy {
	
	public abstract void initDive(String _nodeID);

	public abstract String getFilename();

	public abstract String getNodeID();

	@RDFProperty(ontoURI=Ontologies.diveURI, value="role")
	public abstract String getRole();

	@RDFProperty(ontoURI=Ontologies.foafURI, value="name", type = Type.LITERAL)
	public abstract String getName();

	@RDFProperty(ontoURI=Ontologies.diveURI, value="certorg", type = Type.LITERAL)
	public abstract String getCertOrg();

	@RDFProperty(ontoURI=Ontologies.diveURI, value="certnr", type = Type.LITERAL)
	public abstract String getCertNr();

	@RDFProperty(ontoURI=Ontologies.diveURI, value="certdate", type = Type.LITERAL)
	public abstract String getCertDate();
}