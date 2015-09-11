package utd.sw.assignment1;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.*;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.util.FileManager;

import java.io.*;

import org.apache.jena.vocabulary.VCARD;

public class Lab1_4d extends Object {
	public static void main (String args[]) {
		try{
			org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.OFF);
			// some definitions
			String defaultNameSpace = "http://org.semwebprogramming/chapter2/people#";
			String personURI    = "http://utdallas/semclass#KevenAtes";
			String fullName    = "Keven L. Ates";
			String birthDate   = "1901-01-01";
			String email     = "atescomp@utdallas.edu";
			String title     = "Senior Lecturer";
			// create an empty model
			// Model model = ModelFactory.createDefaultModel();

			String directory = "MyDatabases/Dataset1" ;
			Dataset dataset = TDBFactory.createDataset(directory);
			dataset.begin(ReadWrite.READ);
			Model model = dataset.getNamedModel("myrdf") ;
			dataset.end() ;
			dataset.begin(ReadWrite.WRITE);
			model = dataset.getDefaultModel();

			InputStream inFoafInstance = FileManager.get().open("Navaneeth_FOAFFriends.rdf");
			model.read(inFoafInstance,defaultNameSpace);		
			inFoafInstance.close();

			@SuppressWarnings("unused")
			Resource johnSmith 
			= model.createResource(personURI)
			.addProperty(VCARD.FN, fullName)
			.addProperty(VCARD.BDAY,birthDate)
			.addProperty(VCARD.EMAIL,email)
			.addProperty(VCARD.TITLE,title);

			FileOutputStream xmlfile = new FileOutputStream("Lab1_4_nvenugopalarao.xml");
			FileOutputStream ntpfile = new FileOutputStream("Lab1_4_nvenugopalarao.ntp");
			FileOutputStream n3file = new FileOutputStream("Lab1_4_nvenugopalarao.n3");

			model.write(xmlfile,"RDF/XML-ABBREV");
			model.write(ntpfile,"N-TRIPLE");
			model.write(n3file,"N3");
			dataset.commit();
			dataset.end();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
