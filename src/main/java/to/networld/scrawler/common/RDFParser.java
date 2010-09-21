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

import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jaxen.JaxenException;
import org.jaxen.SimpleNamespaceContext;
import org.jaxen.XPath;
import org.jaxen.dom4j.Dom4jXPath;

/**
 * Superclass that hides the low level RDF parsing code. Each class that needs to parse
 * RDF files should extend this class.
 * 
 * @author Alex Oberhauser
 */
public class RDFParser {
	protected final URL url;
	private final SAXReader reader;
	private final Document document;
	protected final HashMap<String, String> namespace = new HashMap<String, String>();
	protected String queryPrefix = "/";
	
	protected RDFParser(URL _url) throws DocumentException {
		this.reader = new SAXReader();
		this.setReaderOptions();
		this.url = _url;
		this.document = this.reader.read(_url);
		this.initDefaultNamespace();
	}

	/**
	 * Set optimized reader options to be able to parse the files faster.
	 */
	private synchronized void setReaderOptions() {
		this.reader.setValidation(false);
		this.reader.setIgnoreComments(true);
		this.reader.setIncludeExternalDTDDeclarations(false);
		this.reader.setIncludeInternalDTDDeclarations(false);
		this.reader.setStripWhitespaceText(true);
	}
	
	protected synchronized void initDefaultNamespace() {
		this.namespace.put("rdf", Ontologies.rdfURI);
		this.namespace.put("rdfs", Ontologies.rdfsURI);
		this.namespace.put("owl", Ontologies.owlURI);
	}
	
	@SuppressWarnings("unchecked")
	protected synchronized List<Element> getLinkNodes(String _query, HashMap<String, String> _namespaces) {
		try {
			XPath xpath = new Dom4jXPath(_query);
			xpath.setNamespaceContext(new SimpleNamespaceContext(_namespaces));
			return (List<Element>) xpath.selectNodes(this.document);
		} catch (JaxenException e) {
			e.printStackTrace();
			return new LinkedList<Element>();
		}
	}
	
	@SuppressWarnings("unchecked")
	protected synchronized List<Element> getLinkNodes(String _query) {
		return (List<Element>) this.document.selectNodes(_query);
	}
	
	/**
	 * Please set before you call the function the variables this.namespace and this.queryPrefix.
	 * 
	 * @param _nodeName The name of the node for example 'dive:name'
	 * @return The string representation of the given node.
	 */
	protected synchronized String getSingleNode(String _nodeName) {
		List<Element> nodeList = this.getLinkNodes(this.queryPrefix + "/" + _nodeName, this.namespace);
		if ( nodeList.size() > 0 )
			return nodeList.get(0).getTextTrim();
		else
			return null;
	}
	
	protected synchronized String getConditionalSingleNode(String _nodeName, String _resourceName, String _resourceValue) {
		List<Element> nodeList = this.getLinkNodes(this.queryPrefix + "/" + _nodeName, this.namespace);
		if ( nodeList.size() > 0 ) {
			for ( Element node : nodeList ) {
				if ( node.valueOf("@" + _resourceName).equalsIgnoreCase(_resourceValue) )
					return node.getTextTrim();
			}
		}
		return null;
	}
	
	/**
	 * Please set before you call the function the variables this.namespace and this.queryPrefix.
	 * 
	 * @param _nodeName The name of the node for example 'dive:name'
	 * @return The string representation of the given node. If found more than one times the Vector has more elements.
	 */
	protected synchronized Vector<String> getNodes(String _nodeName) {
		List<Element> nodeList = this.getLinkNodes(this.queryPrefix + "/" + _nodeName, this.namespace);
		Vector<String> retValues = new Vector<String>();
		for ( Element entry : nodeList ) {
			String str = entry.getTextTrim();
			if ( !str.equals("") )
				retValues.add(str);
		}
		return retValues;
	}
	
	/**
	 * Please set before you call the function the variables this.namespace and this.queryPrefix.
	 * 
	 * @param _nodeName The name of the node for example 'dive:name'
	 * @return The resource as string representation of the given node.
	 */
	protected synchronized String getSingleNodeResource(String _nodeName, String _resourceName) {
		List<Element> nodeList = this.getLinkNodes(this.queryPrefix + "/" + _nodeName, this.namespace);
		if ( nodeList.size() > 0 )
			return nodeList.get(0).valueOf("@" + _resourceName);
		else
			return null;
	}

	/**
	 * Please set before you call the function the variables this.namespace and this.queryPrefix.
	 * 
	 * @param _nodeName The name of the node for example 'dive:name'
	 * @return The resource as string representation of the given node. If found more than one times the Vector has more elements.
	 */
	protected synchronized Vector<String> getNodesResource(String _nodeName, String _resourceName) {
		List<Element> nodeList = this.getLinkNodes(this.queryPrefix + "/" + _nodeName, this.namespace);
		Vector<String> retValues = new Vector<String>();
		for ( Element entry : nodeList ) {
			String str = entry.valueOf("@" + _resourceName);
			if ( !str.equals("") )
				retValues.add(str);
		}
		return retValues;
	}
	
	public synchronized URL getURL() { return this.url; }
}
