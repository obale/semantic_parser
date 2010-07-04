package to.networld.scrawler.interfaces;

import java.util.Vector;

/**
 * @author Alex Oberhauser
 *
 */
public interface IFOAFAgent {
	
	/**
	 * Returns the unique URI that is related to this entity.
	 * 
	 * @return The URI to the FOAF file of this entity.
	 */
	public String getURI();
	
	/**
	 * Extracts the name of the agent.
	 * 
	 * @return The name of the agent.
	 */
	public String getName();
	
	/**
	 * Extracts the gender of the agent.
	 * 
	 * @return The gender of the agent.
	 */
	public String getGender();
	
	/**
	 * Extract the image URL of the agent.
	 * 
	 * @return The image as URL of the agent.
	 */
	public String getImageURL();

	/**
	 * Extracts the friends aka. known agents from a FOAF file.
	 * 
	 * @return The friend as IFOAFAgent.
	 */
	public Vector<IFOAFAgent> getFriends();

	/**
	 * Extracts the interests from a FOAF file.
	 * 
	 * @return Interests as Strings.
	 */
	public Vector<String> getInterests();

}