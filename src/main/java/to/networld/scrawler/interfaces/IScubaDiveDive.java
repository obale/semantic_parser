package to.networld.scrawler.interfaces;

import java.util.Vector;

import to.networld.scrawler.annotations.RDFEntity;
import to.networld.scrawler.annotations.RDFProperty;
import to.networld.scrawler.annotations.RDFProperty.Type;
import to.networld.scrawler.common.Ontologies;

/**
 * @author Alex Oberhauser
 *
 */
@RDFEntity(ontoURI = Ontologies.diveURI, concept = "Dive")
public interface IScubaDiveDive {

	@RDFProperty(ontoURI = Ontologies.diveURI, value = "activity", type = Type.LITERAL)
	public abstract String getActivity();

	@RDFProperty(ontoURI = Ontologies.diveURI, value = "date", type = Type.LITERAL)
	public abstract String getDate();
	
	@RDFProperty(ontoURI = Ontologies.diveURI, value = "divesite", type = Type.LITERAL)
	public abstract String getDivesite();
	
	@RDFProperty(ontoURI = Ontologies.diveURI, value = "divebase", type = Type.LITERAL)
	public abstract String getDivebase();

	@RDFProperty(ontoURI = Ontologies.diveURI, value = "location", type = Type.LITERAL)
	public abstract String getLocation();

	@RDFProperty(ontoURI = Ontologies.diveURI, value = "comment", type = Type.LITERAL)
	public abstract String getComment();

	@RDFProperty(ontoURI = Ontologies.diveURI, value = "entrancetype", type = Type.LITERAL)
	public abstract String getEntranceType();

	@RDFProperty(ontoURI = Ontologies.diveURI, value = "watertype", type = Type.LITERAL)
	public abstract String getWaterType();

	@RDFProperty(ontoURI = Ontologies.diveURI, value = "weight", type = Type.LITERAL)
	public abstract String getWeight();
	
	@RDFProperty(ontoURI = Ontologies.diveURI, value = "exposureprotection", type = Type.LITERAL)
	public abstract String getExposureProtection();
	
	@RDFProperty(ontoURI = Ontologies.diveURI, value = "maxdeep", type = Type.LITERAL)
	public abstract String getMaxDeep();
	
	@RDFProperty(ontoURI = Ontologies.diveURI, value = "bottomtime", type = Type.LITERAL)
	public abstract String getBottomTime();

	@RDFProperty(
			ontoURI = Ontologies.geoURI,
			value = "Point",
			type = Type.ROOTNODE,
			subNodeOntoURI = { Ontologies.geoURI },
			subNode = { "lat" },
			subNodeDeep = { 1 },
			subNodeType = { Type.LITERAL })
	public abstract String getLatitude();

	@RDFProperty(
			ontoURI = Ontologies.geoURI,
			value = "Point",
			subNodeOntoURI = { Ontologies.geoURI },
			subNode = { "long" },
			subNodeDeep = { 1 })
	public abstract String getLongitude();

	@RDFProperty(ontoURI = Ontologies.geoURI, value = "image", type = Type.RESOURCE)
	public abstract String getGeoImage();

	@RDFProperty(ontoURI = Ontologies.diveURI, value = "diver", type = Type.RESOURCE)
	public abstract IScubaDiveBuddy getDiver();
	
	@RDFProperty(ontoURI = Ontologies.diveURI, value = "partner", type = Type.RESOURCE)
	public abstract Vector<IScubaDiveBuddy> getBuddies();

	@RDFProperty(ontoURI = Ontologies.diveURI, value = "id", type = Type.LITERAL)
	public abstract int getID();

	@RDFProperty(ontoURI = Ontologies.diveURI, value = "name", type = Type.LITERAL)
	public abstract String getName();

}