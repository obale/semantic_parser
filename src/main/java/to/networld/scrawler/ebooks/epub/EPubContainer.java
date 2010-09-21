/**
 * scrawler - to.networld.scrawler.ebooks.epub
 *
 * Copyright (C) 2010 by Networld Project
 * Written by Alex Oberhauser <alexoberhauser@networld.to>
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

package to.networld.scrawler.ebooks.epub;

import java.net.URL;

import org.dom4j.DocumentException;

import to.networld.scrawler.common.RDFParser;

/**
 * @author Alex Oberhauser
 */
public class EPubContainer extends RDFParser {

	/**
	 * @param url
	 * @throws DocumentException
	 */
	protected EPubContainer(URL url) throws DocumentException {
		super(url);
		this.namespace.put("con", "urn:oasis:names:tc:opendocument:xmlns:container");
	}
	
	public String getRootFile() { return this.getSingleNodeResource("con:rootfile", "full-path"); }

}
