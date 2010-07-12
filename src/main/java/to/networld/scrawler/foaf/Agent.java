package to.networld.scrawler.foaf;

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
	
	/**
	 * 
	 * @param _url The URL that points to a valid FOAF file
	 * @throws Exception Generic exception, doesn't matter what error occurs the agent could not be instantiated.
	 */
	public Agent(URL _url) throws Exception {
		super(_url);
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
	 * TODO: Abstract this part
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getName()
	 */
	public String getName() {
		String name = this.getSingleNode("foaf:name");
		if ( name == null || name.equals("") ) {
			String firstname = this.getSingleNode("foaf:firstName");
			String surname = this.getSingleNode("foaf:surname");
			return firstname + " " + surname;
		}
		return name;
	}
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getGender()
	 */
	public String getGender() { return this.getSingleNode("foaf:gender"); }
	
	/**
	 * TODO: Abstract this part.
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getImageURL()
	 */
	public String getImageURL() {
		String image = this.getSingleNodeResource("foaf:depiction", "rdf:resource");
		if ( image == null || image.equals("") )
			image = this.getSingleNodeResource("foaf:img", "rdf:resource");
		return image;
	}
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getDateOfBirth()
	 */
	public String getDateOfBirth() { return this.getSingleNode("foaf:dateOfBirth"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getWebsite()
	 */
	public String getWebsite() { return this.getSingleNodeResource("foaf:homepage", "rdf:resource"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getWeblog()
	 */
	public String getWeblog() { return this.getSingleNodeResource("foaf:weblog", "rdf:resource"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getSchoolHomepage()
	 */
	public String getSchoolHomepage() { return this.getSingleNodeResource("/foaf:schoolHomepage", "rdf:resource"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getWorkplaceHomepage()
	 */
	public String getWorkplaceHomepage() { return this.getSingleNodeResource("foaf:workplaceHomepage", "rdf:resource"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getWorkInfoHomepage()
	 */
	public String getWorkInfoHomepage() { return this.getSingleNodeResource("foaf:workInfoHomepage", "rdf:resource"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getOpenid()
	 */
	public String getOpenid() { return this.getSingleNodeResource("foaf:openid", "rdf:resource"); }
	
	public Vector<Double> getLocation() {
		double lat = -1.0;
		double lon = -1.0;
		try {
			lat = Double.parseDouble(this.getSingleNode("/geo:lat"));
			lon = Double.parseDouble(this.getSingleNode("/geo:long"));
		} catch (Exception e) {
			/**
			 * XXX: Not the best practice to swallow the exception, but if there is no gps location we skip this step.
			 */
			return null;
		}
		Vector<Double> geo = new Vector<Double>();
		geo.add(0, lat);
		geo.add(1, lon);
		return geo;
	}
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getKnownAgents()
	 */
	public Vector<String> getKnownAgents() {
		Vector<String> retValues = new Vector<String>();
		retValues.addAll(this.getNodesResource("foaf:knows", "rdf:resource"));
		retValues.addAll(this.getNodesResource("foaf:knows//rdfs:seeAlso", "rdf:resource"));
		return retValues;
	}
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getPublications()
	 */
	public Vector<String> getPublications() { return this.getNodesResource("foaf:publications", "rdf:resource"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getInterests()
	 */
	public Vector<String> getInterests() { return this.getNodesResource("foaf:interest", "rdfs:label"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getEMails()
	 */
	public Vector<String> getEMails() { return this.getNodesResource("foaf:mbox", "rdf:resource"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getPhoneNumbers()
	 */
	public Vector<String> getPhoneNumbers() { return this.getNodesResource("foaf:phone", "rdf:resource"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFAgent#getDiveCertificate()
	 */
	public String getDiveCertificate() { return this.getSingleNodeResource("dive:hasCertification", "rdf:resource"); }
}
