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
import to.networld.scrawler.scubadive.Equipment;

/**
 * @author Alex Oberhauser
 *
 */
@RDFEntity(ontoURI = Ontologies.diveURI, concept = "Diver")
public interface IScubaDiveBuddy {
	
	public abstract void initDive(String _nodeID);

	public abstract String getNodeID();

	@RDFProperty(ontoURI=Ontologies.diveURI, value="role")
	public abstract String getRole();

	@RDFProperty(ontoURI=Ontologies.foafURI, value="name", type = Type.LITERAL)
	public abstract String getName();

	@RDFProperty(ontoURI=Ontologies.diveURI, value="certorg", type = Type.LITERAL)
	public abstract String getCertOrg();

	@RDFProperty(ontoURI=Ontologies.diveURI, value="certnr", type = Type.LITERAL)
	public abstract String getCertNr();

	@RDFProperty(ontoURI=Ontologies.diveURI, value="certdate", type = Type.LITERAL)
	public abstract String getCertDate();
	
	@RDFProperty(ontoURI=Ontologies.diveURI, value="currentCertification", type = Type.RESOURCE)
	public abstract String getCurrentCertification();
	
	@RDFProperty(ontoURI=Ontologies.diveURI, value="totalDivesUntilNow", type = Type.LITERAL)
	public abstract String getTotalDivesUntilNow();
	
	@RDFProperty(ontoURI=Ontologies.diveURI, value="usedEquipment", type = Type.RESOURCE)
	public abstract Vector<Equipment> getUsedEquiptment();
	
	@RDFProperty(ontoURI=Ontologies.diveURI, value="seeDiverProfile", type = Type.RESOURCE)
	public abstract String getDiverProfileURI();
	
	@RDFProperty(ontoURI=Ontologies.rdfsURI, value="seeAlso", type = Type.RESOURCE)
	public abstract String getProfileURI();
}