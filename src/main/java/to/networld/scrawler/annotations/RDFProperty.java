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

package to.networld.scrawler.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Alex Oberhauser
 *
 */
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RDFProperty {
	
	enum Type {
		ROOTNODE,
		RESOURCE,
		LITERAL,
		/** Could be either a resource or a literal */
		RESOURCE_LITERAL,
		UNDEFINED
	}
	
	String ontoURI();
	String value();
	Type type() default Type.UNDEFINED;
	
	String [] alt() default { };
	
	String [] subNodeOntoURI() default { };
	
	/**
	 * The first sub node has the deep 1 (one), the second sub node 2 (two) and so one...
	 * 
	 * @return A integer array that stores the deep of the nodes.
	 */
	int [] subNodeDeep() default { 1 };
	String [] subNode() default { } ;
	Type [] subNodeType() default { Type.UNDEFINED };

}