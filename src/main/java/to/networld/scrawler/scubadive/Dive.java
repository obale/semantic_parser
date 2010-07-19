/**
 * Semantic Crawler Library
 *
 * Copyright (C) 2010 by Networld Project
 * Written by Alex Oberhauser <oberhauseralex@networld.to>
 * All Rights Reserved
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this software.  If not, see <http://www.gnu.org/licenses/>
 */

package to.networld.scrawler.scubadive;

import java.net.URL;
import java.util.List;
import java.util.Vector;

import org.dom4j.DocumentException;
import org.dom4j.Element;

import to.networld.scrawler.common.RDFParser;
import to.networld.scrawler.interfaces.IScubaDiveBuddy;
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
		super(_url);
		this.filename = _url.toString();
		this.document = this.reader.read(_url);
		this.initDive(_nodeID);
	}
	
	private void initDive(String _nodeID) {
		this.nodeid = _nodeID;;
		this.namespace.put("dive", "http://scubadive.networld.to/dive.rdf#");
		this.namespace.put("foaf", "http://xmlns.com/foaf/0.1/");
		this.namespace.put("geo", "http://www.w3.org/2003/01/geo/wgs84_pos#");
		this.queryPrefix = "/rdf:RDF/dive:Dive[@rdf:ID='" + _nodeID + "']";
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
	@Override
	public String getActivity() { return this.getSingleNode("dive:activity"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getDate()
	 */
	@Override
	public String getDate() { return this.getSingleNode("dive:date"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getDivesite()
	 */
	@Override
	public String getDivesite() { return this.getSingleNode("dive:divesite"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getLocation()
	 */
	@Override
	public String getLocation() { return this.getSingleNode("dive:location"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getComment()
	 */
	@Override
	public String getComment() { return this.getSingleNode("dive:comment"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getEntranceType()
	 */
	@Override
	public String getEntranceType() { return this.getSingleNode("dive:entrancetype"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getWaterType()
	 */
	@Override
	public String getWaterType() { return this.getSingleNode("dive:watertype"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getWeight()
	 */
	@Override
	public String getWeight() { return this.getSingleNode("dive:weight"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getExposureProtection()
	 */
	@Override
	public String getExposureProtection() { return this.getSingleNode("dive:exposureprotection"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getMaxDeep()
	 */
	@Override
	public String getMaxDeep() { return this.getSingleNode("dive:maxdeep"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getBottomTime()
	 */
	@Override
	public String getBottomTime() { return this.getSingleNode("dive:bottomtime"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getLatitude()
	 */
	@Override
	public String getLatitude() { return this.getSingleNode("/geo:lat"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getLongitude()
	 */
	@Override
	public String getLongitude() { return this.getSingleNode("/geo:long"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getGeoImage()
	 */
	@Override
	public String getGeoImage() { return this.getSingleNodeResource("geo:image", "resource"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getBuddies()
	 */
	@Override
	public Vector<IScubaDiveBuddy> getBuddies() {
		Vector<IScubaDiveBuddy> buddies = new Vector<IScubaDiveBuddy>();
		Vector<String> buddiesPostfix = this.getNodesResource("dive:partner", "rdf:resource");
		for ( String entry : buddiesPostfix ) {
			IScubaDiveBuddy buddy = new Buddy(this.document, entry.replace("#", ""));
			buddies.add(buddy);
		}
		
		return buddies;
	}
	
	public String getFilename() { return this.filename; }
	
	public String getNodeID() { return this.nodeid; }
	
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getID()
	 */
	@Override
	public int getID() { return this.id; }
	
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getName()
	 */
	@Override
	public String getName() { return this.name; }

	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getDivebase()
	 */
	@Override
	public String getDivebase() { return this.getSingleNode("dive:divebase"); }

	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveDive#getDiver()
	 */
	@Override
	public IScubaDiveBuddy getDiver() {
		String diverStr = this.getSingleNodeResource("dive:diver", "rdf:resource");
		if ( diverStr == null || diverStr.equals("") )
			return null;
		else
			return new Buddy(this.document, diverStr.replace("#", ""));
	}
}
