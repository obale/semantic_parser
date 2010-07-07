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
	String ontoURI();
	String value();
	String alt1() default "";
	String alt2() default "";
}
