package to.networld.scrawler.interfaces;

import to.networld.scrawler.annotations.RDFEntity;
import to.networld.scrawler.annotations.RDFProperty;

/**
 * @author Alex Oberhauser
 *
 */
@RDFEntity(ontoURI = Ontologies.diveURI)
public interface IScubaDiveBuddy {
	
	public abstract void initDive(String _nodeID);

	public abstract String getFilename();

	public abstract String getNodeID();

	@RDFProperty(ontoURI=Ontologies.diveURI, value="role")
	public abstract String getRole();

	@RDFProperty(ontoURI=Ontologies.foafURI, value="name")
	public abstract String getName();

	@RDFProperty(ontoURI=Ontologies.diveURI, value="certorg")
	public abstract String getCertOrg();

	@RDFProperty(ontoURI=Ontologies.diveURI, value="certnr")
	public abstract String getCertNr();

}