package to.networld.scrawler.tests;


import java.util.Vector;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import to.networld.scrawler.foaf.Agent;
import to.networld.scrawler.interfaces.IFOAFAgent;

/**
 * @author Alex Oberhauser
 *
 */
public class IFOAFAgentTest {
	private IFOAFAgent testAgent;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.testAgent = new Agent("http://devnull.networld.to/foaf.rdf#me");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.testAgent = null;
	}
	
	@Test
	public void testGetName() {
		String name = this.testAgent.getName();
		Assert.assertEquals("Alex Oberhauser", name);
	}
	
	@Test
	public void testGetFriends() {
		Vector<IFOAFAgent> friendVector = this.testAgent.getFriends();
		for ( IFOAFAgent entry : friendVector ) {
			Assert.assertNotNull(entry.getURI());
		}
	}

}
