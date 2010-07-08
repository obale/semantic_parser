package to.networld.scrawler.interfaces;

import to.networld.scrawler.annotations.RDFEntity;
import to.networld.scrawler.common.Ontologies;

/**
 * @author Alex Oberhauser
 *
 */
@RDFEntity(ontoURI = Ontologies.doapURI, concept = "Project")
public interface IDOAPProject extends IDOAP {
}
