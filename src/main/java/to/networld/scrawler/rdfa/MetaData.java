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

import org.dom4j.DocumentException;
import org.dom4j.Element;

import to.networld.scrawler.common.RDFParser;

/**
 * @author Alex Oberhauser
 *
 */
public class MetaData extends RDFParser {

	/**
	 * @param url
	 * @throws DocumentException
	 */
	public MetaData(URL url) throws DocumentException {
		super(url);
		this.document = this.reader.read(url);
		this.namespace.put("", "http://www.w3.org/1999/xhtml");
		this.namespace.put("dc", "http://purl.org/dc/elements/1.1/");
	}
	
	public String getAuthor() {
		List<Element> elements = this.getLinkNodes("html");
		System.out.println(elements.get(0).asXML());
		return null;
	}

}
