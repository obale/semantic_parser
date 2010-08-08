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

package to.networld.scrawler.foaf;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Vector;

import org.dom4j.Element;

import to.networld.scrawler.common.RDFParser;
import to.networld.scrawler.interfaces.IFOAFPerson;

/**
 * Handles a FOAF Person. The information are read out with the help of XPath queries.
 * 
 * @author Alex Oberhauser
 */
public final class Person extends RDFParser implements IFOAFPerson {		
	
	/**
	 * @param _url The URL that points to a valid FOAF file
	 * @throws Exception Generic exception, doesn't matter what error occurs the agent could not be instantiated.
	 */
	public Person(URL _url) throws Exception {
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
	 * @see to.networld.scrawler.interfaces.IFOAFPerson#getName()
	 */
	@Override
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
	 * @see to.networld.scrawler.interfaces.IFOAFPerson#getGender()
	 */
	@Override
	public String getGender() { return this.getSingleNode("foaf:gender"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFPerson#getImageURL()
	 */
	@Override
	public String getImageURL() {
		String image = this.getSingleNodeResource("foaf:depiction", "rdf:resource");
		if ( image == null || image.equals("") )
			image = this.getSingleNodeResource("foaf:img", "rdf:resource");
		return image;
	}
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFPerson#getDateOfBirth()
	 */
	@Override
	public String getDateOfBirth() { return this.getSingleNode("foaf:dateOfBirth"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFPerson#getWebsite()
	 */
	@Override
	public String getWebsite() { return this.getSingleNodeResource("foaf:homepage", "rdf:resource"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFPerson#getWeblog()
	 */
	@Override
	public String getWeblog() { return this.getSingleNodeResource("foaf:weblog", "rdf:resource"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFPerson#getSchoolHomepage()
	 */
	@Override
	public String getSchoolHomepage() { return this.getSingleNodeResource("/foaf:schoolHomepage", "rdf:resource"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFPerson#getWorkplaceHomepage()
	 */
	@Override
	public String getWorkplaceHomepage() { return this.getSingleNodeResource("foaf:workplaceHomepage", "rdf:resource"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFPerson#getWorkInfoHomepage()
	 */
	@Override
	public String getWorkInfoHomepage() { return this.getSingleNodeResource("foaf:workInfoHomepage", "rdf:resource"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFPerson#getOpenid()
	 */
	@Override
	public String getOpenid() { return this.getSingleNodeResource("foaf:openid", "rdf:resource"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFPerson#getLocation()
	 */
	@Override
	public Vector<Double> getLocation() {
		double lat = -1.0;
		double lon = -1.0;
		Vector<Double> geo = new Vector<Double>();
		try {
			lat = Double.parseDouble(this.getSingleNode("/geo:lat"));
			lon = Double.parseDouble(this.getSingleNode("/geo:long"));
		} catch (Exception e) {
			return geo;
		}
		geo.add(0, lat);
		geo.add(1, lon);
		return geo;
	}
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFPerson#getKnownAgents()
	 */
	@Override
	public Vector<String> getKnownAgents() {
		Vector<String> retValues = new Vector<String>();
		retValues.addAll(this.getNodesResource("foaf:knows", "rdf:resource"));
		retValues.addAll(this.getNodesResource("foaf:knows//rdfs:seeAlso", "rdf:resource"));
		return retValues;
	}
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFPerson#getPublications()
	 */
	@Override
	public Vector<String> getPublications() { return this.getNodesResource("foaf:publications", "rdf:resource"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFPerson#getInterests()
	 */
	@Override
	public Vector<String> getInterests() { return this.getNodesResource("foaf:interest", "rdfs:label"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFPerson#getEMails()
	 */
	@Override
	public Vector<String> getEMails() { return this.getNodesResource("foaf:mbox", "rdf:resource"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFPerson#getPhoneNumbers()
	 */
	@Override
	public Vector<String> getPhoneNumbers() { return this.getNodesResource("foaf:phone", "rdf:resource"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFPerson#getDiveCertificate()
	 */
	@Override
	public String getDiveCertificate() { return this.getSingleNodeResource("dive:hasCertification", "rdf:resource"); }
	
    private static String convertToHex(byte[] data) { 
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) { 
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do { 
                if ((0 <= halfbyte) && (halfbyte <= 9)) 
                    buf.append((char) ('0' + halfbyte));
                else 
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while(two_halfs++ < 1);
        } 
        return buf.toString();
    } 

	
	private static String computeSHA1(String _text) throws NoSuchAlgorithmException, UnsupportedEncodingException  { 
	        MessageDigest md = MessageDigest.getInstance("SHA-1");
	        byte[] sha1hash = new byte[40];
	        md.update(_text.getBytes("iso-8859-1"), 0, _text.length());
	        sha1hash = md.digest();
	        return convertToHex(sha1hash);

	}
	
	/**
	 * @see to.networld.scrawler.interfaces.IFOAFPerson#hasEMail(java.lang.String)
	 */
	@Override
	public boolean hasEMail(String _email) {
		try {
			String sha1value = computeSHA1(_email);
			Vector<String> mboxValues = this.getNodes("foaf:mbox_sha1sum");
			for ( String entry : mboxValues ) {
				if ( sha1value.equals(entry) ) return true;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @see to.networld.scrawler.interfaces.IFOAFPerson#getAccounts()
	 */
	@Override
	public Vector<Account> getAccounts() {
		List<Element> elements = this.getLinkNodes(this.queryPrefix + "/foaf:holdsAccount");
		Vector<Account> retVector = new Vector<Account>();
		for ( Element entry : elements ) {
			Account account = new Account();
			Element nameNode = (Element) entry.selectSingleNode(entry.getUniquePath() + "//foaf:accountName");
			if ( nameNode != null )
				account.setName(nameNode.getTextTrim());
			
			Element serviceHomepageNode = (Element) entry.selectSingleNode(entry.getUniquePath() + "//foaf:accountServiceHomepage");
			if ( serviceHomepageNode != null )
				account.setServiceHomepage(serviceHomepageNode.valueOf("@rdf:resource"));
			
			Element profilePageNode = (Element) entry.selectSingleNode(entry.getUniquePath() + "//foaf:accountProfilePage");
			if ( profilePageNode != null )
				account.setProfilePage(profilePageNode.valueOf("@rdf:resource"));
			
			retVector.add(account);
		}
		return retVector;
	}

	/**
	 * @see to.networld.scrawler.interfaces.IFOAFPerson#getCurrentProjects()
	 */
	@Override
	public Vector<String> getCurrentProjects() { return this.getNodesResource("foaf:currentProject/rdf:Description/rdfs:seeAlso", "rdf:resource"); }

	/**
	 * @see to.networld.scrawler.interfaces.IFOAFPerson#getPastProjects()
	 */
	@Override
	public Vector<String> getPastProjects() { return this.getNodesResource("foaf:pastProject/rdf:Description/rdfs:seeAlso", "rdf:resource"); }
	
}
