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

package to.networld.scrawler.calendar;

import java.util.List;
import java.net.URL;
import java.util.Vector;

import org.dom4j.DocumentException;
import org.dom4j.Element;

import to.networld.scrawler.common.Ontologies;
import to.networld.scrawler.common.RDFParser;
import to.networld.scrawler.interfaces.IVCalendar;
import to.networld.scrawler.interfaces.IVEvent;

/**
 * @author Alex Oberhauser
 */
public class VCalendar extends RDFParser implements IVCalendar {

	/**
	 * @param url
	 * @throws DocumentException
	 */
	public VCalendar(URL url) throws DocumentException {
		super(url);
		this.namespace.put("ical", Ontologies.icalURI);
		
		this.queryPrefix = "/rdf:RDF/ical:Vcalendar";
	}

	/**
	 * @see to.networld.scrawler.interfaces.IVCalendar#getEvents()
	 */
	@Override
	public Vector<IVEvent> getEvents() {
		List<Element> nodes = this.getLinkNodes(this.queryPrefix + "/ical:Vevent", this.namespace);
		Vector<IVEvent> retVector = new Vector<IVEvent>();
		
		String oldPrefix = this.queryPrefix;
		for ( Element entry : nodes ) {
			this.queryPrefix = entry.getUniquePath();
			IVEvent event = new VEvent(this.getSingleNode("ical:uid"));
			event.setSummary(this.getSingleNode("ical:summary"));
			event.setStartDate(this.getSingleNode("ical:dtstart"));
			event.setEndDate(this.getSingleNode("ical:dtend"));
			event.setCategories(this.getNodes("ical:categories"));
			event.setLocation(this.getSingleNode("ical:location"));
			
			retVector.add(event);
		}
		this.queryPrefix = oldPrefix;
		return retVector;
	}

}
