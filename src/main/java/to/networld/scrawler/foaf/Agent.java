package to.networld.scrawler.foaf;

import java.io.InputStream;
import java.util.Vector;

import to.networld.scrawler.interfaces.IFOAFAgent;
import to.networld.scrawler.common.RDFEntity;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.RDFNode;

/**
 * Agent entity that handles person. The selfURI is a FOAF file that could be queried with SPARQL.
 * The class don't search the friend automatically. The friends are the only information that are
 * stored. All other information are gained ad-hoc.
 * 
 * @author Alex Oberhauser
 *
 */
public class Agent extends RDFEntity implements IFOAFAgent {
	
	public Agent(String _selfURI) {
		super(_selfURI);
	}
	
	public Agent(InputStream _xmlFile) {
		super(_xmlFile);
	}
	
	private boolean checkURI(String friendURI) {
		if ( friendURI.startsWith("http") ) return true;
		else if ( friendURI.startsWith("file") ) return true;
		else if ( friendURI.startsWith("ftp") ) return true;
		return false;
	}
	
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getFriends()
	 */
	public Vector<IFOAFAgent> getFriends() {
		Vector<IFOAFAgent> friendVector = new Vector<IFOAFAgent>();
		Vector<String> friendStringVector = this.getOwnProperty("foaf:knows");
		for ( String entry : friendStringVector ) {
			if ( this.checkURI(entry) )	friendVector.add(new Agent(entry));
		}
		return friendVector;
	}
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getInterests()
	 */
	public Vector<String> getInterests() {
		StringBuffer strbuffer = this.getHeader();
		strbuffer.append("SELECT ?interest\n");
		strbuffer.append("WHERE {\n");
		strbuffer.append("[] foaf:interest ?uri . \n");
		strbuffer.append("  OPTIONAL { ?uri rdfs:label ?interest . }\n");
		strbuffer.append("  OPTIONAL { <" + this.selfURI + "> foaf:interest ?interest . }\n");
		strbuffer.append("}");
		ResultSet results = this.rdfhandler.executeQuery(strbuffer.toString());
		Vector<String> retVector = new Vector<String>();
		RDFNode entry = null;
		while ( results.hasNext() ) {
			entry = results.next().get("interest");
			if ( entry.isLiteral() )
				retVector.add(entry.asNode().getLiteralLexicalForm());
		}
		return retVector;
	}

	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getGender()
	 */
	@Override
	public String getGender() {
		throw new UnsupportedOperationException();
	}

	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getImageURL()
	 */
	@Override
	public String getImageURL() {
		throw new UnsupportedOperationException();
	}

	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getName()
	 */
	@Override
	public String getName() {
		Vector<String> nameVector = this.getOwnProperty("foaf:name");
		if ( nameVector.size() > 0 )
			return nameVector.firstElement();
		return null;
	}
}
