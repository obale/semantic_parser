package to.networld.scrawler.scubadive;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Vector;

import org.dom4j.DocumentException;
import org.dom4j.Element;

import to.networld.scrawler.common.RDFParser;
import to.networld.scrawler.interfaces.IScubaDiveDive;

/**
 * 
 * @author Alex Oberhauser
 *
 */
public class Dive extends RDFParser implements IScubaDiveDive {
	private String filename = null;
	private String nodeid = null;
	private int id = -1;
	private String name = null;
	
	public Dive(URL _url, String _nodeID) throws DocumentException {
		super();
		this.filename = _url.getPath();
		this.document = this.reader.read(_url);
		this.initDive(_nodeID);
	}
	
	private void initDive(String _nodeID) {
		this.nodeid = _nodeID;;
		this.namespace.put("dive", "http://scubadive.networld.to/dive.rdf#");
		this.namespace.put("foaf", "http://xmlns.com/foaf/0.1/");
		this.namespace.put("geo", "http://www.w3.org/2003/01/geo/wgs84_pos#");
		this.queryPrefix = "/rdf:RDF/dive:Dive[@ID='" + _nodeID + "']";
		this.setID();
		this.setName();
	}
	
	private void setID() {
		List<Element> ids = this.getLinkNodes(this.queryPrefix + "/dive:id", this.namespace);
		if ( ids.size() > 0 ) {
			try {
				this.id = new Integer(ids.get(0).getTextTrim());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void setName() {
		this.name = this.getSingleNode("dive:name");
	}
	
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getActivity()
	 */
	public String getActivity() { return this.getSingleNode("dive:activity"); }
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getDate()
	 */
	public String getDate() { return this.getSingleNode("dive:date"); }
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getDivesite()
	 */
	public String getDivesite() { return this.getSingleNode("dive:divesite"); }
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getLocation()
	 */
	public String getLocation() { return this.getSingleNode("dive:location"); }
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getComment()
	 */
	public String getComment() { return this.getSingleNode("dive:comment"); }
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getEntranceType()
	 */
	public String getEntranceType() { return this.getSingleNode("dive:entrancetype"); }
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getWaterType()
	 */
	public String getWaterType() { return this.getSingleNode("dive:watertype"); }
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getWeight()
	 */
	public String getWeight() { return this.getSingleNode("dive:weight"); }
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getExposureProtection()
	 */
	public String getExposureProtection() { return this.getSingleNode("dive:exposureprotection"); }
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getMaxDeep()
	 */
	public String getMaxDeep() { return this.getSingleNode("dive:maxdeep"); }
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getBottomTime()
	 */
	public String getBottomTime() { return this.getSingleNode("dive:bottomtime"); }
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getLatitude()
	 */
	public String getLatitude() { return this.getSingleNode("/geo:lat"); }
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getLongitude()
	 */
	public String getLongitude() { return this.getSingleNode("/geo:long"); }
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getGeoImage()
	 */
	public String getGeoImage() { return this.getSingleResourceNode("geo:image", "resource"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getBuddies()
	 */
	public Vector<Buddy> getBuddies() {
		Vector<Buddy> buddies = new Vector<Buddy>();
		List<Element> nodeList = this.getLinkNodes(this.queryPrefix + "/dive:partner", this.namespace);
		for ( Element entry : nodeList ) {
			String buddyURI = entry.valueOf("@resource");
			try {
				Buddy buddy = new Buddy(new URL(this.filename), buddyURI.replace("#", ""));
				buddies.add(buddy);
			} catch (DocumentException e) {
				e.printStackTrace();
				continue;
			} catch (MalformedURLException e) {
				e.printStackTrace();
				continue;
			}
		}
		return buddies;
	}
	
	public String getFilename() { return this.filename; }
	
	public String getNodeID() { return this.nodeid; }
	
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getID()
	 */
	public int getID() { return this.id; }
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getName()
	 */
	public String getName() { return this.name; }
}
