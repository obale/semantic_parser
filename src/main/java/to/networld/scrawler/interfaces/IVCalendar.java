package to.networld.scrawler.interfaces;

import java.util.Vector;

import to.networld.scrawler.annotations.RDFEntity;
import to.networld.scrawler.annotations.RDFProperty;
import to.networld.scrawler.common.Ontologies;

/**
 * @author Alex Oberhauser
 */
@RDFEntity(ontoURI = Ontologies.icalURI, concept="Vcalendar")
public interface IVCalendar extends IRDFEntity {
	
	@RDFProperty(ontoURI = Ontologies.icalURI, value = "Vevent")
	public abstract Vector<IVEvent> getEvents();
	
}
