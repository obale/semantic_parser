/**
 * scrawler - to.networld.scrawler.ebooks
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

package to.networld.scrawler.doap;

import java.net.URL;
import java.util.Vector;

import org.dom4j.DocumentException;

import to.networld.scrawler.common.Ontologies;
import to.networld.scrawler.common.RDFParser;
import to.networld.scrawler.interfaces.IDOAPOrganization;

/**
 * @author Alex Oberhauser
 */
public class DOAPOrganization extends RDFParser implements IDOAPOrganization {

	/**
	 * @param url
	 * @throws DocumentException
	 */
	public DOAPOrganization(URL url) throws DocumentException {
		super(url);
		this.namespace.put("foaf", Ontologies.foafURI);
		this.namespace.put("doap", Ontologies.doapURI);
		this.queryPrefix = "/rdf:RDF/foaf:Organization";
	}

	/**
	 * @see to.networld.scrawler.interfaces.IDOAPOrganization#getBugDatabase()
	 */
	@Override
	public String getBugDatabase() { return this.getSingleNodeResource("doap:bug-database", "rdf:resource"); }

	/**
	 * @see to.networld.scrawler.interfaces.IDOAPOrganization#getCreationDate()
	 */
	@Override
	public String getCreationDate() { return this.getSingleNode("doap:created"); }

	/**
	 * XXX: Don't return only a reference. Possible return values are:
	 *      - The Project URI
	 * 		- The Project Name
	 *      - A class that represents the subtree that is related to the project.
	 * 
	 * @see to.networld.scrawler.interfaces.IDOAPOrganization#getCurrentProjects()
	 */
	@Override
	public Vector<String> getCurrentProjects() { return this.getNodesResource("foaf:currentProject/rdf:Description/rdfs:seeAlso", "rdf:resource"); }

	/**
	 * @see to.networld.scrawler.interfaces.IDOAPOrganization#getDescription()
	 */
	@Override
	public String getDescription() { return this.getSingleNode("doap:description"); }

	/**
	 * @see to.networld.scrawler.interfaces.IDOAPOrganization#getDeveloper()
	 */
	@Override
	public Vector<String> getDeveloper() { return this.getNodesResource("doap:developer/foaf:Person", "rdf:about"); }

	/**
	 * @see to.networld.scrawler.interfaces.IDOAPOrganization#getDownloadPage()
	 */
	@Override
	public String getDownloadPage() { return this.getSingleNodeResource("doap:download-page", "rdf:resource"); }

	/**
	 * @see to.networld.scrawler.interfaces.IDOAPOrganization#getHomepage()
	 */
	@Override
	public String getHomepage() { return this.getSingleNodeResource("foaf:homepage", "rdf:resource"); }

	/**
	 * @see to.networld.scrawler.interfaces.IDOAPOrganization#getLicense()
	 */
	@Override
	public String getLicense() { 
		String license = this.getSingleNode("doap:license");
		if ( license == null || license.equals("") ) {
			license = this.getSingleNodeResource("doap:license", "rdf:resource");
		}
		return license;
	}

	/**
	 * @see to.networld.scrawler.interfaces.IDOAPOrganization#getLogo()
	 */
	@Override
	public String getLogo() { return this.getSingleNodeResource("foaf:logo", "rdf:resource"); }

	/**
	 * @see to.networld.scrawler.interfaces.IDOAPOrganization#getMailinglist()
	 */
	@Override
	public String getMailinglist() { return this.getSingleNodeResource("doap:mailing-list", "rdf:resource"); }

	/**
	 * @see to.networld.scrawler.interfaces.IDOAPOrganization#getMaintainer()
	 */
	@Override
	public Vector<String> getMaintainer() { return this.getNodesResource("doap:maintainer/foaf:Person", "rdf:about"); }

	/**
	 * @see to.networld.scrawler.interfaces.IDOAPOrganization#getName()
	 */
	@Override
	public String getName() { return this.getSingleNode("foaf:name"); }

	/**
	 * @see to.networld.scrawler.interfaces.IDOAPOrganization#getReleases()
	 */
	@Override
	public Vector<String> getReleases() { return this.getNodes("doap:release/doap:version/doap:name"); }

	/**
	 * @see to.networld.scrawler.interfaces.IDOAPOrganization#getRepositoryBrowse()
	 */
	@Override
	public Vector<String> getRepositoryBrowse() { return this.getNodesResource("doap:repository//doap:browse", "rdf:resource"); }

	/**
	 * @see to.networld.scrawler.interfaces.IDOAPOrganization#getShortDescription()
	 */
	@Override
	public String getShortDescription() { return this.getSingleNode("doap:shortdesc"); }

	/**
	 * @see to.networld.scrawler.interfaces.IDOAPOrganization#getWeblog()
	 */
	@Override
	public String getWeblog() { return this.getSingleNodeResource("foaf:blog", "rdf:resource"); }

	/**
	 * @see to.networld.scrawler.interfaces.IDOAPOrganization#getWiki()
	 */
	@Override
	public String getWiki() { return this.getSingleNodeResource("doap:wiki", "rdf:resource"); }

}
