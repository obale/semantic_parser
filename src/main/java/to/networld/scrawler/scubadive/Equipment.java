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

/**
 * @author Alex Oberhauser
 *
 */
public class Equipment {
	private String type = null;
	private String brand = null;
	private String color = null;
	
	public synchronized void setType(String _type) {
		this.type = _type;
	}
	
	public synchronized void setBrand(String _brand) {
		this.brand = _brand;
	}
	
	public synchronized void setColor(String _color) {
		this.color = _color;
	}
	
	public synchronized String getType() { return this.type; }
	
	public synchronized String getBrand() { return this.brand; }
	
	public synchronized String getColor() { return this.color; }
}
