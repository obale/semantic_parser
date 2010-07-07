package to.networld.scrawler.interfaces;

import java.util.Vector;

import to.networld.scrawler.annotations.RDFEntity;
import to.networld.scrawler.scubadive.Buddy;

/**
 * @author Alex Oberhauser
 *
 */
@RDFEntity(ontoURI = "http://scubadive.networld.to/dive.rdf#")
public interface IScubaDiveDive {

	public abstract String getActivity();

	public abstract String getDate();

	public abstract String getDivesite();

	public abstract String getLocation();

	public abstract String getComment();

	public abstract String getEntranceType();

	public abstract String getWaterType();

	public abstract String getWeight();

	public abstract String getExposureProtection();

	public abstract String getMaxDeep();

	public abstract String getBottomTime();

	public abstract String getLatitude();

	public abstract String getLongitude();

	public abstract String getGeoImage();

	public abstract Vector<Buddy> getBuddies();

	public abstract String getFilename();

	public abstract String getNodeID();

	public abstract int getID();

	public abstract String getName();

}