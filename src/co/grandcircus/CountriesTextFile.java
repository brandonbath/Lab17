package co.grandcircus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;


/*Create a class named CountriesTextFile that contains a method that allows you to
read a list of countries from a file and another method that allows you to write a list
of countries to a file.*/

/*Store the list of countries in a text files named countries.txt in the same directory as
the CountriesTextFile class. If the countries.txt file doesn’t exist, CountriesTextFile
should create it. The class should use buffered I/O streams and should close all I/O
streams when they are not needed.*/

public class CountriesTextFile {

	
	public static boolean writeCountriesToFile(Path filePath, ArrayList<Country> countries) {
		
		// TODO add existence check!
		File file = filePath.toFile();
		
		// The second argument to FileOutputStream sets the stream to append mode.
		// This is the try-with-resource block.
		try (PrintWriter output = new PrintWriter(new FileOutputStream(file, true));) {
			// Evidently System.out and PrintWriter share ancestry or interfaces. \
			// TODO: Find out which!
			for (Country c : countries)
			{
				output.println(c);
			}
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("The file >>" + filePath + "<< does not exist.");
			e.printStackTrace();
			return false;
		}
	}
	
	public static ArrayList<Country> readFromFile(Path filePath) {
		ArrayList<Country> countriesInFile = new ArrayList<>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()));

			String line = br.readLine();
			while (line != null) { 
				System.out.println(line); 
				Country newCountry = Country.fromString(line);
				countriesInFile.add(newCountry); 
				line = br.readLine(); 
			}
			br.close();
		} catch (FileNotFoundException fnfe) {
			// TODO Auto-generated catch block
			fnfe.printStackTrace();
		} catch (IOException e) {

		} finally {

		}
		return countriesInFile;
	}
}
