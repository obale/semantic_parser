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

/**
 * @author Alex Oberhauser
 */
public class Account {
	private String accountServiceHomepage = null;
	private String accountProfilePage = null;
	private String accountName = null;

	public synchronized void setServiceHomepage(String _accountServiceHomepage) {
		this.accountServiceHomepage = _accountServiceHomepage;
	}

	public synchronized void setProfilePage(String _accountProfilePage) {
		this.accountProfilePage = _accountProfilePage;
	}

	public synchronized void setName(String _accountName) {
		this.accountName = _accountName;
	}
	
	public synchronized String getServiceHomepage() { return this.accountServiceHomepage; }
	
	public synchronized String getProfilePage() { return this.accountProfilePage; }
	
	public synchronized String getName() { return this.accountName; }
}