package to.networld.scrawler.interfaces;

import java.util.Vector;

/**
 * @author Alex Oberhauser
 *
 */
public interface IFOAFAgent {

	public abstract String getName();
	
	public abstract String getGender();

	public abstract String getImageURL();

	public abstract String getDateOfBirth();

	public abstract String getWebsite();

	public abstract String getWeblog();

	public abstract String getSchoolHomepage();

	public abstract String getWorkplaceHomepage();

	public abstract String getOpenid();

	public abstract Vector<Double> getLocation();

	public abstract Vector<IFOAFAgent> getKnownAgents();

	public abstract Vector<String> getInterests();

	public abstract Vector<String> getEMails();

	public abstract Vector<String> getPhoneNumbers();

	/**
	 * Reads out the dive certificate (see http://scubadive.networld.to for the ontology).
	 * 
	 * @return The scuba dive certificate.
	 */
	public abstract String getDiveCertificate();

	public abstract String getURI();

}