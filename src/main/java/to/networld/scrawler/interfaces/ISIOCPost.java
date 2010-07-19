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

package to.networld.scrawler.interfaces;

import java.util.Vector;

import to.networld.scrawler.annotations.RDFEntity;
import to.networld.scrawler.annotations.RDFProperty;
import to.networld.scrawler.annotations.RDFProperty.Type;
import to.networld.scrawler.common.Ontologies;

/**
 * @author Alex Oberhauser
 *
 */
@RDFEntity(ontoURI = Ontologies.siocURI, concept="Post")
public abstract interface ISIOCPost {
	
	@RDFProperty(ontoURI = Ontologies.siocURI, value = "link", type = Type.RESOURCE)
	public abstract String getLink();
	
	@RDFProperty(ontoURI = Ontologies.dcURI, value = "title", type = Type.LITERAL)
	public abstract String getTitle();
	
	@RDFProperty(ontoURI = Ontologies.dctermsURI, value = "created", type = Type.LITERAL)
	public abstract String getCreationDate();
	
	@RDFProperty(ontoURI = Ontologies.siocURI, value = "content", type = Type.LITERAL)
	public abstract String getContent();

	@RDFProperty(ontoURI = Ontologies.contentURI, value = "encoded", type = Type.LITERAL)
	public abstract String getEncodedContent();
	
	@RDFProperty(ontoURI = Ontologies.siocURI, value = "topic", type = Type.RESOURCE)
	public abstract Vector<String> getTopics();

	@RDFProperty(
			ontoURI = Ontologies.siocURI,
			value = "has_creator", 
			type = Type.ROOTNODE,
			subNode= { "User" },
			subNodeDeep = { 1 },
			subNodeOntoURI = { Ontologies.siocURI },
			subNodeType = { Type.RESOURCE } )
	public abstract Vector<String> getCreators();
	
	@RDFProperty(
			ontoURI = Ontologies.foafURI,
			value = "maker", 
			type = Type.RESOURCE,
			subNode= { "Person" },
			subNodeDeep = { 1 },
			subNodeOntoURI = { Ontologies.foafURI },
			subNodeType = { Type.RESOURCE } )
	public abstract Vector<String> getMakers();
	
	@RDFProperty(ontoURI = Ontologies.siocURI, value = "links_to", type = Type.RESOURCE)
	public abstract Vector<String> getLinksTo();
	
}
