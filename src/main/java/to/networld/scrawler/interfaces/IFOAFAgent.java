package to.networld.scrawler.interfaces;

import java.util.Vector;

import to.networld.scrawler.annotations.RDFEntity;
import to.networld.scrawler.annotations.RDFProperty;

/**
 * @author Alex Oberhauser
 *
 */
@RDFEntity(ontoURI = Ontologies.foafURI)
public interface IFOAFAgent {
	
	@RDFProperty(ontoURI=Ontologies.foafURI, value="name")
	public abstract String getName();
	
	@RDFProperty(ontoURI=Ontologies.foafURI, value="gender")
	public abstract String getGender();

	@RDFProperty(ontoURI=Ontologies.foafURI, value="img", alt1="depiction")
	public abstract String getImageURL();

	@RDFProperty(ontoURI=Ontologies.foafURI, value="dateOfBirth")
	public abstract String getDateOfBirth();

	@RDFProperty(ontoURI=Ontologies.foafURI, value="homepage")
	public abstract String getWebsite();

	@RDFProperty(ontoURI=Ontologies.foafURI, value="weblog")
	public abstract String getWeblog();

	@RDFProperty(ontoURI=Ontologies.foafURI, value="schoolHomepage")
	public abstract String getSchoolHomepage();

	@RDFProperty(ontoURI=Ontologies.foafURI, value="workplaceHomepage")
	public abstract String getWorkplaceHomepage();

	@RDFProperty(ontoURI=Ontologies.foafURI, value="openid")
	public abstract String getOpenid();

	@RDFProperty(ontoURI=Ontologies.foafURI, value="Point", alt1="lat", alt2="long")
	public abstract Vector<Double> getLocation();

	@RDFProperty(ontoURI=Ontologies.foafURI, value="knows")
	public abstract Vector<IFOAFAgent> getKnownAgents();

	@RDFProperty(ontoURI=Ontologies.foafURI, value="interest")
	public abstract Vector<String> getInterests();

	@RDFProperty(ontoURI=Ontologies.foafURI, value="mbox")
	public abstract Vector<String> getEMails();

	@RDFProperty(ontoURI=Ontologies.foafURI, value="phone")
	public abstract Vector<String> getPhoneNumbers();

	@RDFProperty(ontoURI=Ontologies.diveURI, value="hasCertification")
	public abstract String getDiveCertificate();

	public abstract String getURI();

}