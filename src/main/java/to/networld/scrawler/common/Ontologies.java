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

package to.networld.scrawler.common;

/**
 * Stores the ontology URIs. "xyzURI + property" identifies the property uniquely.
 * 
 * @author Alex Oberhauser
 */
public abstract class Ontologies {
	public static final String foafURI = "http://xmlns.com/foaf/0.1/";
	public static final String diveURI = "http://scubadive.networld.to/dive.rdf#";
	public static final String doapURI = "http://usefulinc.com/ns/doap#";
	public static final String siocURI = "http://rdfs.org/sioc/ns#";
	public static final String scotURI = "http://scot-project.org/scot/ns#";
	public static final String skosURI = "http://www.w3.org/2009/08/skos-reference/skos.rdf#";
	public static final String rdfURI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
	public static final String rdfsURI = "http://www.w3.org/2000/01/rdf-schema#";
	public static final String owlURI = "http://www.w3.org/2002/07/owl#";
	public static final String geoURI = "http://www.w3.org/2003/01/geo/wgs84_pos#";
	public static final String dcURI = "http://purl.org/dc/elements/1.1/";
	public static final String dctermsURI = "http://purl.org/dc/terms/";
	public static final String contentURI = "http://purl.org/rss/1.0/modules/content/";
	public static final String icalURI = "http://www.w3.org/2002/12/cal#";
	public static final String opfURI = "http://www.idpf.org/2007/opf";
}
