package utd.sw.assignment_2;
import java.io.*; 
 
import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.vocabulary.DC;
import org.apache.jena.vocabulary.VCARD;

public class Lab_2 {
	static Model mv = null;
	public static void main (String args[]) {
		org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.OFF);
		String NURI           = "http://utdallas/semclass/person#StanleyKubrick";
		String fName          = "Stanley Kubrick";
		String movieDS_URI    = "http://utdallas/semclass/movies#DrStrangelove";
		String movieCO_URI    = "http://utdallas/semclass/movies#ClockworkOrange";
		String yrds           = "1964";
		String yrCO           = "1972";
		String bookRA_URI     = "http://utdallas/semclass/books#RedAlert";
		String bookCO_URI     = "http://utdallas/semclass/books#ClockworkOrange(Book)";
		String authorRA_URI   = "http://utdallas/semclass/person#PeterGeorge";
		String authorCO_URI   = "http://utdallas/semclass/person#AnthonyBurgess";

		//Default model
		mv = ModelFactory.createDefaultModel();

		Resource pClass    = mv.createResource("http://utdallas/semclass/vocab/person");
		Resource mvDir      = mv.createResource(NURI);
		Resource mvClass     = mv.createResource("http://utdallas/semclass/vocabu/movie");
		Resource mvDS        = mv.createResource(movieDS_URI);
		Resource mvCO        = mv.createResource(movieCO_URI);
		Resource bkCO         = mv.createResource(bookCO_URI);
		Resource authorRA       = mv.createResource(authorRA_URI);
		Resource authorC       = mv.createResource(authorCO_URI);
		Resource bkClass      = mv.createResource("http://utdallas/semclass/book");
		Resource bkRA         = mv.createResource(bookRA_URI);

		Property MvYear      = mv.createProperty("http://utdallas/semclass/vocab/movie#year");
		Property MvDirector  = mv.createProperty("http://utdallas/semclass/vocab/movie#director");
		Property MvTitle     = mv.createProperty("http://utdallas/semclass/vocab/movie#title");

		//Add properties to resources created
		bkRA.addProperty(DC.type, bkClass);
		bkCO.addProperty(DC.type, bkClass);

		authorRA.addProperty(DC.type, pClass);
		//authorRA.addProperty(DC.creator, bkRA);
		bkRA.addProperty(DC.creator, authorRA);
		authorRA.addProperty(VCARD.ROLE, "Author");
		authorC.addProperty(DC.type, pClass);
		authorC.addProperty(VCARD.ROLE, "Author");
		authorC.addProperty(DC.creator, bkCO);

		mvCO.addProperty(DC.type, mvClass);
		mvCO.addProperty(MvYear, yrCO);
		mvCO.addProperty(MvTitle, mvCO);
		mvCO.addProperty(DC.source, bkCO);

		mvDir.addProperty(VCARD.FN, fName);
		mvDir.addProperty(MvDirector, mvDS);
		mvDir.addProperty(MvDirector, mvCO);
		mvDir.addProperty(DC.type, pClass);

		mvDS.addProperty(DC.type, mvClass);
		mvDS.addProperty(MvYear, yrds);
		mvDS.addProperty(MvTitle, mvDS);
		mvDS.addProperty(DC.source, bkRA);

		// TDB-backed dataset created
		String directory = "MyDatabases/Dataset1" ;
		Dataset dataset = TDBFactory.createDataset(directory);

		try {
			PrintWriter writeOne = new PrintWriter("Lab2_3_nvenugopalarao.xml", "UTF-8");
			PrintWriter writeTwo = new PrintWriter("Lab2_3_nvenugopalarao.n3", "UTF-8");
			System.out.println("Files opened\nWriting mode");
			dataset.begin(ReadWrite.WRITE) ;
			mv.write(writeOne, "RDF/XML-ABBREV");
			mv.write(writeTwo, "N3");
			dataset.commit();
			dataset.end() ;
			// files closed
			writeOne.close();
			writeTwo.close();
			mv.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
