package to.networld.scrawler.interfaces;

import java.util.Vector;

import to.networld.scrawler.annotations.RDFProperty;
import to.networld.scrawler.annotations.RDFProperty.Type;
import to.networld.scrawler.common.Ontologies;

/**
 * @author Alex Oberhauser
 *
 */
public interface IMetaData {

	/**
	 * Extract RDF links from the URL. Concatenates absolute URIs that starts with '/' to
	 * the complete URL.
	 * 
	 * @return A Vector with all found links that are of the type 'application/rdf+xml'
	 */
	public abstract Vector<String> getLinks();

	@RDFProperty(ontoURI = Ontologies.dcURI, value = "title", type = Type.LITERAL)
	public abstract Vector<String> getTitle();

	@RDFProperty(ontoURI = Ontologies.dcURI, value = "creator", type = Type.LITERAL)
	public abstract Vector<String> getCreator();

	@RDFProperty(ontoURI = Ontologies.dcURI, value = "subject", type = Type.LITERAL)
	public abstract Vector<String> getSubject();
	
	@RDFProperty(ontoURI = Ontologies.dcURI, value = "language", type = Type.LITERAL)
	public abstract Vector<String> getLanguage();
	
	@RDFProperty(ontoURI = Ontologies.dcURI, value = "rights", type = Type.LITERAL)
	public abstract Vector<String> getRights();
	
	@RDFProperty(ontoURI = Ontologies.dcURI, value = "description", type = Type.LITERAL)
	public abstract Vector<String> getDescription();
	
	@RDFProperty(ontoURI = Ontologies.dcURI, value = "type", type = Type.LITERAL)
	public abstract Vector<String> getType();
	
	@RDFProperty(ontoURI = Ontologies.dcURI, value = "format", type = Type.LITERAL)
	public abstract Vector<String> getFormat();

}