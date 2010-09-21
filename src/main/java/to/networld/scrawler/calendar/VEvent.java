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

import java.util.Vector;

import to.networld.scrawler.interfaces.IVEvent;

/**
 * @author Alex Oberhauser
 */
public class VEvent implements IVEvent {
	private final String uid;
	private String summary = null;
	private String startDate = null;
	private String endDate = null;
	private Vector<String> categories = new Vector<String>();
	private String location = null;
	
	public VEvent(String _uid) {
		this.uid = _uid;
	}

	/**
	 * @see to.networld.scrawler.interfaces.IVEvent#getSummary()
	 */
	@Override
	public String getSummary() { return this.summary; }

	/**
	 * @see to.networld.scrawler.interfaces.IVEvent#getUID()
	 */
	@Override
	public String getUID() { return this.uid; }

	/**
	 * @see to.networld.scrawler.interfaces.IVEvent#setSummary(java.lang.String)
	 */
	@Override
	public void setSummary(String _summary) {
		this.summary = _summary;
	}

	/**
	 * @see to.networld.scrawler.interfaces.IVEvent#getEndDate()
	 */
	@Override
	public String getEndDate() { return this.endDate; }

	/**
	 * @see to.networld.scrawler.interfaces.IVEvent#getStartDate()
	 */
	@Override
	public String getStartDate() { return this.startDate; }

	/**
	 * @see to.networld.scrawler.interfaces.IVEvent#setEndDate(java.lang.String)
	 */
	@Override
	public void setEndDate(String _endDate) {
		this.endDate = _endDate;
	}

	/**
	 * @see to.networld.scrawler.interfaces.IVEvent#setStartDate(java.lang.String)
	 */
	@Override
	public void setStartDate(String _startDate) {
		this.startDate = _startDate;
	}

	/**
	 * @see to.networld.scrawler.interfaces.IVEvent#getCategories()
	 */
	@Override
	public Vector<String> getCategories() { return this.categories; }

	/**
	 * @see to.networld.scrawler.interfaces.IVEvent#setCategories(java.util.Vector)
	 */
	@Override
	public void setCategories(Vector<String> _categories) {
		this.categories = _categories;
	}

	/**
	 * @see to.networld.scrawler.interfaces.IVEvent#getLocation()
	 */
	@Override
	public String getLocation() { return this.location; }

	/**
	 * @see to.networld.scrawler.interfaces.IVEvent#setLocation(java.lang.String)
	 */
	@Override
	public void setLocation(String _location) {
		this.location = _location;
	}
	
}
