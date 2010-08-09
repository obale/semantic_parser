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

package to.networld.scrawler.rdfa;

import java.net.URL;
import java.util.List;
import java.util.Vector;

import org.dom4j.DocumentException;
import org.dom4j.Element;

import to.networld.scrawler.common.RDFParser;
import to.networld.scrawler.interfaces.IMetaData;

/**
 * @author Alex Oberhauser
 */
public class MetaData extends RDFParser implements IMetaData {

	/**
	 * @param url
	 * @throws DocumentException
	 */
	public MetaData(URL url) throws DocumentException {
		super(url);
	}
	
	private String splitProperty(String _property) {
		String checkProperty = _property.toLowerCase();
		if ( checkProperty.startsWith("dc.") ) {
			return _property.substring(3, _property.length());
		} else return null;
	}

	private Vector<String> getAttribute(String _attributeName) {
		List<Element> elements = this.getLinkNodes("//*[name()='meta']'");
		Vector<String> retVector = new Vector<String>();
		for ( Element entry : elements ) {
			String property = this.splitProperty(entry.valueOf("@name"));
			if ( property != null ) {
				if ( property.equalsIgnoreCase(_attributeName) )
					retVector.add(entry.valueOf("@content"));
			}
		}
		return retVector;
	}
	
	/**
	 * @see to.networld.scrawler.interfaces.IMetaData#getLinks()
	 */
	public Vector<String> getLinks() {
		List<Element> elements = this.getLinkNodes("//*[name()='link']'");
		Vector<String> retVector = new Vector<String>();
		for ( Element entry : elements ) {
			String property = entry.valueOf("@type");
			if ( property != null ) {
				if ( property.equalsIgnoreCase("application/rdf+xml") ) {
					String link = entry.valueOf("@href");
					if ( link.startsWith("/") ) {
						link = this.url.getProtocol() + "://" + this.url.getHost() + link; 
					}
					retVector.add(link);
				}
			}
		}
		return retVector;
	}
	
	/**
	 * @see to.networld.scrawler.interfaces.IMetaData#getTitle()
	 */
	@Override
	public Vector<String> getTitle() { return this.getAttribute("title"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IMetaData#getCreator()
	 */
	@Override
	public Vector<String> getCreator() { return this.getAttribute("creator"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IMetaData#getSubject()
	 */
	@Override
	public Vector<String> getSubject() { return this.getAttribute("subject"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IMetaData#getLanguage()
	 */
	@Override
	public Vector<String> getLanguage() { return this.getAttribute("language"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IMetaData#getRights()
	 */
	@Override
	public Vector<String> getRights() { return this.getAttribute("rights"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IMetaData#getDescription()
	 */
	@Override
	public Vector<String> getDescription() { return this.getAttribute("description"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IMetaData#getType()
	 */
	@Override
	public Vector<String> getType() { return this.getAttribute("type"); }
	
	/**
	 * @see to.networld.scrawler.interfaces.IMetaData#getFormat()
	 */
	@Override
	public Vector<String> getFormat() { return this.getAttribute("format"); }

}
