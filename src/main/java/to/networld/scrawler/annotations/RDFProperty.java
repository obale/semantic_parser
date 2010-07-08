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