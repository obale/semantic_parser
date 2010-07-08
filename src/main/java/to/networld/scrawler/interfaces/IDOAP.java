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
@RDFEntity(ontoURI = Ontologies.foafURI, concept = "Organization")
public interface IDOAP {
	
	@RDFProperty(ontoURI = Ontologies.foafURI, value = "name", type = Type.LITERAL)
	public String getName();
	
	@RDFProperty(ontoURI = Ontologies.foafURI, value = "logo", type = Type.RESOURCE)
	public String getLogo();
	
	@RDFProperty(ontoURI = Ontologies.doapURI, value = "created", type = Type.LITERAL)
	public String getCreationDate();
	
	@RDFProperty(ontoURI = Ontologies.foafURI, value = "homepage", type = Type.RESOURCE)
	public String getHomepage();
	
	@RDFProperty(ontoURI = Ontologies.foafURI, value = "weblog", type = Type.RESOURCE)
	public String getWeblog();
	
	@RDFProperty(ontoURI = Ontologies.doapURI, value = "wiki", type = Type.RESOURCE)
	public String getWiki();
	
	@RDFProperty(ontoURI = Ontologies.doapURI, value = "mailing-list", type = Type.RESOURCE)
	public String getMailinglist();
	
	@RDFProperty(ontoURI = Ontologies.doapURI, value = "bug-database", type = Type.RESOURCE)
	public String getBugDatabase();
	
	@RDFProperty(ontoURI = Ontologies.doapURI, value = "download-page", type = Type.RESOURCE)
	public String getDownloadPage();
	
	@RDFProperty(ontoURI = Ontologies.doapURI, value = "browse", type = Type.RESOURCE)
	public Vector<String> getRepositoryBrowse();

	@RDFProperty(ontoURI = Ontologies.doapURI, value = "shortdesc", type = Type.LITERAL)
	public String getShortDescription();
	
	@RDFProperty(ontoURI = Ontologies.doapURI, value = "description", type = Type.LITERAL)
	public String getDescription();
	
	@RDFProperty(
			ontoURI = Ontologies.doapURI,
			value = "release",
			subNodeOntoURI = { Ontologies.doapURI, Ontologies.doapURI },
			subNode = { "Version", "name" },
			subNodeDeep = { 1, 2 },
			subNodeType = { Type.ROOTNODE, Type.LITERAL }
			)
	public Vector<String> getReleases();

	@RDFProperty(
			ontoURI = Ontologies.doapURI,
			value = "maintainer", 
			type = Type.ROOTNODE,
			subNodeOntoURI = { Ontologies.foafURI }, 
			subNode = { "Person" },
			subNodeDeep = { 1 },
			subNodeType = { Type.RESOURCE } )
	public Vector<IFOAFAgent> getMaintainer();
	
	@RDFProperty(
			ontoURI = Ontologies.doapURI,
			value = "developer", 
			type = Type.ROOTNODE,
			subNodeOntoURI = { Ontologies.foafURI }, 
			subNode = { "Person" },
			subNodeDeep = { 1 },
			subNodeType = { Type.RESOURCE } )
	public Vector<IFOAFAgent> getDeveloper();
	
	@RDFProperty(
			ontoURI = Ontologies.foafURI,
			value = "currentProject",
			type = Type.ROOTNODE,
			subNodeOntoURI = { Ontologies.rdfURI, Ontologies.rdfsURI },
			subNode = { "Description", "seeAlso" },
			subNodeDeep = { 1, 2 },
			subNodeType = { Type.ROOTNODE, Type.RESOURCE } )
	public Vector<String> getCurrentProjects();
	
	@RDFProperty(ontoURI = Ontologies.doapURI, value = "license", type = Type.RESOURCE_LITERAL)
	public String getLicense();

}
