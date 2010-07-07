package to.networld.scrawler.scubadive;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import to.networld.scrawler.common.RDFParser;
import to.networld.scrawler.interfaces.IScubaDiveBuddy;

/**
 * 
 * @author Alex Oberhauser
 *
 */
public class Buddy extends RDFParser implements IScubaDiveBuddy {
	private String filename = null;
	private String nodeid = null;
	
	public Buddy(File _file, String _nodeID) throws DocumentException {
		super();
		this.filename = _file.getAbsolutePath();
		this.document = this.reader.read(_file);
	}
	
	public Buddy(URL _url, String _nodeID) throws DocumentException {
		super();
		this.filename = _url.getPath();
		this.document = this.reader.read(_url);
		this.initDive(_nodeID);
	}
	
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveBuddy#initDive(java.lang.String)
	 */
	public void initDive(String _nodeID) {
		this.nodeid = _nodeID;
		this.namespace.put("dive", "http://scubadive.networld.to/dive.rdf#");
		this.namespace.put("foaf", "http://xmlns.com/foaf/0.1/");
		this.namespace.put("geo", "http://www.w3.org/2003/01/geo/wgs84_pos#");
		this.queryPrefix = "/rdf:RDF/dive:Diver[@ID='" + _nodeID + "']";
	}
	
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveBuddy#getFilename()
	 */
	public String getFilename() { return this.filename; }
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveBuddy#getNodeID()
	 */
	public String getNodeID() { return this.nodeid; }
	
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveBuddy#getRole()
	 */
	public String getRole() { return this.getSingleNode("dive:role"); }
	private String[] getDiverProfileURI() { return this.getSingleResourceNode("dive:seeDiverProfile", "resource").split("#"); }
	
	private String getExternProfileElement(String _nodeName) {
		String [] uri = this.getDiverProfileURI();
		if ( uri.length > 1 ) {
			File profileFile = new File(uri[0]);
			String prefix = "/rdf:RDF/dive:DiverProfile[@ID='" + uri[1] + "']";
			Document oldDocument = this.document;
			try {
				this.document = this.reader.read(profileFile);
				List<Element> nodeList = this.getLinkNodes(prefix + "/" + _nodeName, this.namespace);
				if ( nodeList.size() > 0 )
					return nodeList.get(0).getTextTrim();
			} catch (DocumentException e) {
				e.printStackTrace();
			} finally {
				this.document = oldDocument;	
			}
		}
		return null;
	}
	
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveBuddy#getName()
	 */
	public String getName() { return this.getExternProfileElement("foaf:name"); }
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveBuddy#getCertOrg()
	 */
	public String getCertOrg() { return this.getExternProfileElement("dive:certorg"); }
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveBuddy#getCertNr()
	 */
	public String getCertNr() { return this.getExternProfileElement("dive:certnr"); }
}
