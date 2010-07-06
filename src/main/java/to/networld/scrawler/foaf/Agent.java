package to.networld.scrawler.foaf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Vector;

import org.dom4j.Element;

import to.networld.scrawler.common.RDFParser;
import to.networld.scrawler.interfaces.IFOAFAgent;

/**
 * Handles a FOAF Agent. The information are read out with the help of XPath queries.
 * 
 * @author Alex Oberhauser
 *
 */
public final class Agent extends RDFParser implements IFOAFAgent {		
	private final String uri;
	
	/**
	 * TODO: Exception handling should be improved!!! 
	 * 
	 * @param _url The URL that points to a valid FOAF file
	 * @param _context The context of the activity that calls this class (needed to access the cache files).
	 * @throws Exception Generic exception, doesn't matter what error occurs the agent could not be instantiated.
	 */
	public Agent(URL _url) throws Exception {
		this.uri = _url.toString();
		this.document = this.reader.read(_url);
		this.namespace.put("dive", "http://scubadive.networld.to/dive.rdf#");
		this.namespace.put("foaf", "http://xmlns.com/foaf/0.1/");
		this.namespace.put("geo", "http://www.w3.org/2003/01/geo/wgs84_pos#");
		this.setQueryPrefix();
	}
	
	/**
	 * Set the query prefix that handles the node of the person that is described by the FOAF file.
	 */
	private void setQueryPrefix() {
		List<Element> nameNodes = this.getLinkNodes("/rdf:RDF/foaf:PersonalProfileDocument/foaf:primaryTopic");
		if (nameNodes.size() > 0) {
			this.queryPrefix = "/rdf:RDF/foaf:Person[@*='" + nameNodes.get(0).valueOf("@rdf:resource") + "']";
			if ( this.getLinkNodes(this.queryPrefix).size() > 0 )
				return;
			this.queryPrefix = "/rdf:RDF/foaf:Person[@*='" + nameNodes.get(0).valueOf("@rdf:resource").replace("#", "") + "']";
			if ( this.getLinkNodes(this.queryPrefix).size() > 0 )
				return;
		}
	}

	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getName()
	 */
	public String getName() {
		List<Element> nameNodes = this.getLinkNodes(this.queryPrefix + "/foaf:name");
		if ( nameNodes.size() == 0 ) {
			String firstname = this.getSingleNode("foaf:firstName");
			String surname = this.getSingleNode("foaf:surname");
			return firstname + " " + surname;
		}
		return nameNodes.get(0).getText();
	}
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getGender()
	 */
	public String getGender() { return this.getSingleNode("foaf:gender"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getImageURL()
	 */
	public String getImageURL() {
		List<Element> nameNodes = this.getLinkNodes(this.queryPrefix + "/foaf:img");
		if ( nameNodes.size() > 0 )
			return nameNodes.get(0).valueOf("@rdf:resource");
		nameNodes = this.getLinkNodes(this.queryPrefix + "/foaf:depiction");
		if  (nameNodes.size() > 0)
			return nameNodes.get(0).valueOf("@rdf:resource");
		return "";
	}
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getDateOfBirth()
	 */
	public String getDateOfBirth() { return this.getSingleNode("foaf:dateOfBirth"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getWebsite()
	 */
	public String getWebsite() { return this.getSingleResourceNode("foaf:homepage", "rdf:resource"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getWeblog()
	 */
	public String getWeblog() { return this.getSingleResourceNode("foaf:weblog", "rdf:resource"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getSchoolHomepage()
	 */
	public String getSchoolHomepage() { return this.getSingleResourceNode("/foaf:schoolHomepage", "rdf:resource"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getWorkplaceHomepage()
	 */
	public String getWorkplaceHomepage() { return this.getSingleResourceNode("/foaf:workplaceHomepage", "rdf:resource"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getOpenid()
	 */
	public String getOpenid() { return this.getSingleResourceNode("foaf:openid", "rdf:resource"); }
	
	public Vector<Double> getLocation() {
		double lat = 0.0;
		double lon = 0.0;
		try {
			lat = Double.parseDouble(this.getSingleNode("/geo:lat"));
			lon = Double.parseDouble(this.getSingleNode("/geo:long"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Vector<Double> geo = new Vector<Double>();
		geo.add(0, lat);
		geo.add(1, lon);
		return geo;
	}
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getKnownAgents()
	 */
	public Vector<IFOAFAgent> getKnownAgents() {
		Vector<IFOAFAgent> knownAgents = new Vector<IFOAFAgent>();
		List<Element> knownNodes = this.getLinkNodes(this.queryPrefix + "/foaf:knows//rdfs:seeAlso");
		knownAgents.addAll(this._getKnownAgents(knownNodes));
		
		knownNodes = this.getLinkNodes(this.queryPrefix + "/foaf:knows");
		knownAgents.addAll(this._getKnownAgents(knownNodes));
		
		return knownAgents;
	}
	
	private Vector<IFOAFAgent> _getKnownAgents(List<Element> _nodes) {
		Vector<IFOAFAgent> knownAgents = new Vector<IFOAFAgent>();
		for (int count=0; count < _nodes.size(); count++) {
			String friendURL = _nodes.get(count).valueOf("@rdf:resource");
			if ( !friendURL.equals("") ) {
				try {
					knownAgents.add(new Agent(new URL(friendURL)));
				} catch (MalformedURLException e) {
					e.printStackTrace();
					continue;
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		}
		return knownAgents;
	}
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getInterests()
	 */
	public Vector<String> getInterests() {
		Vector<String> interests = new Vector<String>();
		List<Element> interestsNodes = this.getLinkNodes(this.queryPrefix + "/foaf:interest");
		for (int count=0; count < interestsNodes.size(); count++) {
			interests.add(interestsNodes.get(count).valueOf("@rdfs:label"));
		}
		return interests;
	}
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getEMails()
	 */
	public Vector<String> getEMails() {
		Vector<String> eMails = new Vector<String>();
		List<Element> eMailNodes = this.getLinkNodes(this.queryPrefix + "/foaf:mbox");
		for (int count=0; count < eMailNodes.size(); count++) {
			String mail = eMailNodes.get(count).valueOf("@rdf:resource").replaceAll("mailto:", "");
			if ( !mail.equals("") )
				eMails.add(mail);
		}
		return eMails;
	}
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getPhoneNumbers()
	 */
	public Vector<String> getPhoneNumbers() {
		Vector<String> phoneNumbers = new Vector<String>();
		List<Element> phoneNumberNodes = this.getLinkNodes(this.queryPrefix + "/foaf:phone");
		for (int count=0; count < phoneNumberNodes.size(); count++) {
			phoneNumbers.add(phoneNumberNodes.get(count).valueOf("@rdf:resource"));
		}
		return phoneNumbers;
	}
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getDiveCertificate()
	 */
	public String getDiveCertificate() { return this.getSingleResourceNode("dive:hasCertification", "rdf:resource"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getURI()
	 */
	public String getURI() { return this.uri; }
}
