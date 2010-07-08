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
@RDFEntity(ontoURI = Ontologies.foafURI, concept="Agent")
public interface IFOAFAgent {
	
	@RDFProperty(ontoURI=Ontologies.foafURI, value="name")
	public abstract String getName();
	
	@RDFProperty(ontoURI=Ontologies.foafURI, value="gender", type = Type.LITERAL)
	public abstract String getGender();

	@RDFProperty(ontoURI=Ontologies.foafURI, value="depiction", alt = { "img" }, type = Type.RESOURCE )
	public abstract String getImageURL();

	@RDFProperty(ontoURI=Ontologies.foafURI, value="dateOfBirth", type = Type.LITERAL)
	public abstract String getDateOfBirth();

	@RDFProperty(ontoURI=Ontologies.foafURI, value="homepage", type = Type.RESOURCE)
	public abstract String getWebsite();

	@RDFProperty(ontoURI=Ontologies.foafURI, value="weblog", type = Type.RESOURCE)
	public abstract String getWeblog();

	@RDFProperty(ontoURI=Ontologies.foafURI, value="schoolHomepage", type = Type.RESOURCE)
	public abstract String getSchoolHomepage();

	@RDFProperty(ontoURI=Ontologies.foafURI, value="workplaceHomepage", type = Type.RESOURCE)
	public abstract String getWorkplaceHomepage();
	
	@RDFProperty(ontoURI=Ontologies.foafURI, value="workInfoHomepage", type = Type.RESOURCE)
	public abstract String getWorkInfoHomepage();

	@RDFProperty(ontoURI=Ontologies.foafURI, value="openid")
	public abstract String getOpenid();

	@RDFProperty(
			ontoURI=Ontologies.geoURI,
			value = "Point",
			type = Type.ROOTNODE, 
			subNode= { "lat", "long" },
			subNodeDeep = { 1, 1 },
			subNodeOntoURI = { Ontologies.geoURI, Ontologies.geoURI },
			subNodeType = { Type.LITERAL, Type.LITERAL } )
	public abstract Vector<Double> getLocation();

	@RDFProperty(ontoURI=Ontologies.foafURI, value="knows", type = Type.RESOURCE)
	public abstract Vector<IFOAFAgent> getKnownAgents();

	@RDFProperty(ontoURI=Ontologies.foafURI, value="interest", type = Type.RESOURCE_LITERAL)
	public abstract Vector<String> getInterests();

	@RDFProperty(ontoURI=Ontologies.foafURI, value="mbox")
	public abstract Vector<String> getEMails();

	@RDFProperty(ontoURI=Ontologies.foafURI, value="phone")
	public abstract Vector<String> getPhoneNumbers();

	@RDFProperty(ontoURI=Ontologies.diveURI, value="hasCertification", type = Type.RESOURCE)
	public abstract String getDiveCertificate();

	public abstract String getURI();

}
