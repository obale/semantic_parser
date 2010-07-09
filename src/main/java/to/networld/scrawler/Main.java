package to.networld.scrawler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import to.networld.scrawler.foaf.Agent;
import to.networld.scrawler.interfaces.IFOAFAgent;

public class Main {
	private static final Vector<String> visitedAgents = new Vector<String>();
	
	protected static void printAgent(IFOAFAgent _agent) throws MalformedURLException, Exception {
		System.out.println("URI:                       " + _agent.getURI());
		System.out.println("Image URI:                 " + _agent.getImageURL());
		System.out.println("Name:                      " + _agent.getName());
		System.out.println("E-Mails:                   " + _agent.getEMails());
		System.out.println("Work Info Homepage:        " + _agent.getWorkInfoHomepage());
		System.out.println("Work Homepage:             " + _agent.getWorkplaceHomepage());
		System.out.println("School Homepage:           " + _agent.getSchoolHomepage());
		System.out.println("Location:                  " + _agent.getLocation());
		System.out.println("Phone Numbers:             " + _agent.getPhoneNumbers());
		System.out.println("Dive Certificates:         " + _agent.getDiveCertificate());
		System.out.println("Website:                   " + _agent.getWebsite());
		System.out.println("Weblog:                    " + _agent.getWeblog());
		System.out.println("Interests:                 " + _agent.getInterests());
		System.out.println("Publications:              " + _agent.getPublications());
		System.out.println("---");
		Vector<String> agents = _agent.getKnownAgents();
		for ( String entry : agents ) {
			if ( !visitedAgents.contains(entry) ) {
				visitedAgents.add(entry);
				Main.printAgent(new Agent(new URL(entry)));
			}
		}
	}

	/**
	 * @param args
	 * @throws Exception 
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws MalformedURLException, Exception {
		Main.printAgent(new Agent(new URL("http://devnull.networld.to/foaf.rdf")));
	}

}
