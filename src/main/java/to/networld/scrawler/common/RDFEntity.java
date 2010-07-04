package to.networld.scrawler.common;

import java.io.InputStream;
import java.util.Vector;

import com.hp.hpl.jena.query.ResultSet;

/**
 * Super class for all RDF entities. Puts the main function that are needed for querying the RDF files
 * at disposal. 
 * 
 * @author Alex Oberhauser
 *
 */
public class RDFEntity {
	protected String selfURI = null;
	protected RDFHandler rdfhandler = null;
	
	protected RDFEntity(String _selfURI) {
		this.selfURI = _selfURI;
		this.rdfhandler = new RDFHandler(this.selfURI);
	}
	
	protected RDFEntity(InputStream _xmlFile) {
		this.rdfhandler = new RDFHandler(null);
		this.rdfhandler.readStream(_xmlFile, null);
	}
	
	protected StringBuffer getHeader() {
		StringBuffer strbuffer = new StringBuffer();
		strbuffer.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n");
		strbuffer.append("PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n");
		strbuffer.append("PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n");
		strbuffer.append("PREFIX sioc: <http://rdfs.org/sioc/ns#>\n");
		strbuffer.append("PREFIX scot: <http://scot-project.org/scot/ns#>\n");
		strbuffer.append("PREFIX dc: <http://purl.org/dc/elements/1.1/>\n");
		strbuffer.append("PREFIX dct: <http://purl.org/dc/terms/>\n");
		strbuffer.append("PREFIX dbpedia: <http://dbpedia.org/>\n");
		strbuffer.append("PREFIX dbpedia2: <http://dbpedia.org/property/>\n");
		strbuffer.append("PREFIX dbpediaEntry: <http://dbpedia.org/resource/>\n");
		strbuffer.append("PREFIX skos: <http://www.w3.org/2004/02/skos/core#>");
		return strbuffer;
	}
	
	/**
	 * Return only the objects with the following form:
	 * 
	 *     <fileuri> inputProperty retValue
	 *     
	 * inputProperty is the parameter as string and retValue will be returned. 
	 * If there are more than one match all results are returned. 
	 * 
	 * @param property The property as String that should be read out.
	 * @return All objects that matches the query.
	 */
	public Vector<String> getOwnProperty(String property) {
		StringBuffer strbuffer = this.getHeader();
		strbuffer.append("SELECT ?property\n");
		strbuffer.append("WHERE {\n");
		strbuffer.append("<" + this.selfURI + "> " + property + " ?property . \n");
		strbuffer.append("}");
		ResultSet results = this.rdfhandler.executeQuery(strbuffer.toString());
		Vector<String> retVector = new Vector<String>();
		while ( results.hasNext() ) {
			retVector.add(results.next().get("property").toString());
		}
		return retVector;
	}
	
	/**
	 * Return all object of the form:
	 * 
	 *     [] inputProperty retValue
	 *     
	 * inputProperty is the parameter as string and retValue will be returned.
	 * The subject doesn't matter.
	 * If there are more than one match all results are returned. 
	 * 
	 * @param property The property as String that should be read out.
	 * @return All objects that matches the query.
	 */
	public Vector<String> getAllProperty(String property) {
		StringBuffer strbuffer = this.getHeader();
		strbuffer.append("SELECT ?property\n");
		strbuffer.append("WHERE {\n");
		strbuffer.append(" [] " + property + " ?property . \n");
		strbuffer.append("}");
		ResultSet results = this.rdfhandler.executeQuery(strbuffer.toString());
		Vector<String> retVector = new Vector<String>();
		while ( results.hasNext() ) {
			retVector.add(results.next().get("property").toString());
		}
		return retVector;
	}
	
	public String getURI() { return this.selfURI; }
}
