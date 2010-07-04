package to.networld.scrawler.common;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFWriter;
import com.hp.hpl.jena.util.FileManager;

/**
 * Provides useful implementation to read/write RDF files with the help
 * of the Jena Framework.
 * 
 * Each object of this class is related to one RDF file.
 * 
 * @author Alex Oberhauser
 *
 */
public class RDFHandler {
	private String inputFile = null;
	private Model model = null;
	
	public RDFHandler(String _inputFile) {
		this.setLogLevel(Level.OFF);
		this.inputFile = _inputFile;
		this.model = ModelFactory.createDefaultModel();
		if ( this.inputFile != null ) this.readFile();
	}
	
	public void setLogLevel(Level _loglevel) {
		Logger fileLogger = Logger.getLogger("com.hp.hpl.jena.util.FileManager");
		fileLogger.setLevel(_loglevel);
		
		Logger locationManagerLogger = Logger.getLogger("com.hp.hpl.jena.util.LocationManager");
		locationManagerLogger.setLevel(_loglevel);
	}
	
	public void readStream(InputStream _xmlFile, String _url) {
		this.inputFile = "";
		this.model = ModelFactory.createDefaultModel();
		this.model.read(_xmlFile, null);
	}
	
	/**
	 * Add the file that is assigned to this object to the model.
	 */
	private void readFile() {
		InputStream in = FileManager.get().open(this.inputFile);
		this.model.read(in, "file:///" + this.inputFile);
	}
	
	/**
	 * Writes the current model to the file (given as filepath as String). 
	 * 
	 * Format:			RDF/XML-ABBREV
	 * Encoding:		UTF-8
	 * TAB:				4 whitespace
	 * XML Declaration: Shown
	 * 
	 * @param outputFile The output file as String.
	 * @throws IOException
	 */
	public void writeFile(String outputFile) throws IOException {
		Writer file = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "UTF-8"));
    	RDFWriter writer = this.model.getWriter("RDF/XML-ABBREV");
        writer.setProperty("showXmlDeclaration", "true");
        writer.setProperty("tab", "4");
        writer.setProperty("relativeURIs", "same-document,relative");
        writer.write(this.model, file, "");
	}
	
	/**
	 * Executes a query on the current model.
	 * 
	 * @param queryString The query as String.
	 * @return A ResultSet that could be read out with the help of the Jena framework.
	 */
	public ResultSet executeQuery(String queryString) {
		Query query = QueryFactory.create(queryString);
		QueryExecution qe;
		if ( this.inputFile != null )
			qe = QueryExecutionFactory.create(query, this.model);
		else
			qe = QueryExecutionFactory.create(query);
		ResultSet results = qe.execSelect();
		return results;
	}

	/**
	 * Print the output as table to the standard output.
	 * 
	 * @param results A ResultSet that will be returned by executeQuery(String)
	 */
	public void printResult(ResultSet results) {
		ResultSetFormatter.out(System.out, results);
	}
	
	public String getFileName() { return this.inputFile; }
	public Model getModel() { return this.model; }
}
