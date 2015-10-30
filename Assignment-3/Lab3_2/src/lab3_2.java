import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory; 
import org.apache.jena.query.ReadWrite; 
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.util.FileManager;
/**
 * @author Navaneeth.Rao
 */
public class lab3_2 {
	public static void main(final String[] args) {
		org.apache.log4j.Logger.getRootLogger()
		.setLevel(org.apache.log4j.Level.OFF);
		// Make a TDB-backed dataset
		final String directory = "MyDatabases/";
		final Dataset dataset = TDBFactory.createDataset(directory + "Dataset1");
		// create an empty Model
		final Model model = dataset.getDefaultModel();
		// Load Monterey and record time
		final double start = System.currentTimeMillis();
		final String file = "Monterey.rdf";
		FileManager.get().readModel(model, file);
		final double end = System.currentTimeMillis();
		// Display the time taken
		final double time = (end - start) / 1000.0;
		System.out.println(String.format("Load of %s took %.1f seconds", file, time));
		dataset.begin(ReadWrite.READ);
		try {
			// Create a query
			final String queryString = "SELECT ?p ?o WHERE { <urn:monterey:#incident1> ?p ?o . }";
			final Query query = QueryFactory.create(queryString);
			// Execute the query
			final QueryExecution qexec = QueryExecutionFactory.create(query, model);
			final ResultSet rs = qexec.execSelect();
			// Output the results
			ResultSetFormatter.outputAsXML(new FileOutputStream("Lab3_2_NVenugopalaRao.xml"), rs);
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			dataset.end();
			model.close();
		}
	}
}
