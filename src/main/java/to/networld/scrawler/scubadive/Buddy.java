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

import org.dom4j.Document;
import org.dom4j.DocumentException;

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
	
	public Buddy(URL _url, String _nodeID) throws DocumentException {
		super(_url);
		this.filename = _url.toString();
		this.document = this.reader.read(_url);
		this.initDive(_nodeID);
	}
	
	public Buddy(Document _document, String _nodeID) {
		super(_document);
		this.initDive(_nodeID);
	}
	
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveBuddy#initDive(java.lang.String)
	 */
	@Override
	public void initDive(String _nodeID) {
		this.nodeid = _nodeID;
		this.namespace.put("dive", "http://scubadive.networld.to/dive.rdf#");
		this.namespace.put("foaf", "http://xmlns.com/foaf/0.1/");
		this.namespace.put("geo", "http://www.w3.org/2003/01/geo/wgs84_pos#");
		this.queryPrefix = "/rdf:RDF/dive:Diver[@rdf:ID='" + _nodeID + "']";
	}
	
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveBuddy#getFilename()
	 */
	@Override
	public String getFilename() { return this.filename; }
	
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveBuddy#getNodeID()
	 */
	@Override
	public String getNodeID() { return this.nodeid; }
	
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveBuddy#getRole()
	 */
	@Override
	public String getRole() { return this.getSingleNode("dive:role"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveBuddy#getName()
	 */
	@Override
	public String getName() { return this.getSingleNode("foaf:name"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveBuddy#getCertOrg()
	 */
	@Override
	public String getCertOrg() { return this.getSingleNode("dive:certorg"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveBuddy#getCertNr()
	 */
	@Override
	public String getCertNr() { return this.getSingleNode("dive:certnr"); }

	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveBuddy#getCertDate()
	 */
	@Override
	public String getCertDate() { return this.getSingleNode("dive:certdate"); }

	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveBuddy#getCurrentCertification()
	 */
	@Override
	public String getCurrentCertification() { return this.getSingleNodeResource("dive:currentCertification", "rdf:resource"); }

	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveBuddy#getTotalDivesUntilNow()
	 */
	@Override
	public String getTotalDivesUntilNow() { return this.getSingleNode("dive:totalDivesUntilNow"); }

	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveBuddy#getUsedEquiptment()
	 */
	@Override
	public Object getUsedEquiptment() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveBuddy#getDiverProfileURI()
	 */
	@Override
	public String getDiverProfileURI() { return this.getSingleNodeResource("dive:seeDiverProfile", "rdf:resource"); }

	/**
	 * @see to.networld.scrawler.interfaces.IScubaDiveBuddy#getProfileURI()
	 */
	@Override
	public String getProfileURI() { return this.getSingleNodeResource("rdfs:seeAlso", "rdf:resource"); }

}
