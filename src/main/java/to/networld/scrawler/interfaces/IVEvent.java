package to.networld.scrawler.interfaces;

import java.util.Vector;

import to.networld.scrawler.annotations.RDFEntity;
import to.networld.scrawler.common.Ontologies;

/**
 * Not direct related to a RDF file, but encapsulates one ical:Vevent
 * object.
 * 
 * @author Alex Oberhauser
 */
@RDFEntity(ontoURI = Ontologies.icalURI, concept="Vevent")
public interface IVEvent {
	
	public void setSummary(String _summary);
	
	public void setStartDate(String _startDate);
	
	public void setEndDate(String _endDate);
	
	public void setCategories(Vector<String> _categories);
	
	public void setLocation(String _location);
	
	public String getUID();
	
	public String getSummary();
	
	public String getStartDate();
	
	public String getEndDate();
	
	public Vector<String> getCategories();
	
	public String getLocation();
}
