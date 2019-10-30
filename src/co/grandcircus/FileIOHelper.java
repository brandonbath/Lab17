package scannerdemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileIOHelper {

	public static void main(String[] args) {

	}

	public static void createDir(String ancestry, String name) {
		String folderNameCurrentPart = "resources";
		String folderPathAncestralPart = "";

		// If an absolute path is not specified, the compiler assumes we're operating
		// relative to the project folder
		Path folder = Paths.get(folderPathAncestralPart + folderNameCurrentPart);

		if (Files.notExists(folder)) {
			try {
				Files.createDirectory(folder);
				System.out.println("The folder \"" + folderNameCurrentPart + "\" was created successfully.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Folder creation failed with trace: ");
				e.printStackTrace();
			}
		} else {
			System.out.println("The folder \"" + folderNameCurrentPart + "\" already exists.");
		}
	}

	public static boolean createFile(String ancestry, String name) {
		Path filePath = Paths.get(ancestry, name);
		return createFile(filePath);
	}

	public static boolean createFile(Path filePath) {
		// Check for parent's existence.
		if (filePath.isAbsolute()) {
			if (!Files.exists(filePath.getParent())) {
				System.out.println(
						"File creation failed on file >>" + filePath + "<<: The parent directory does not exist.");
				return false;
			} else {
				// File path is relative to project directory.
				// TODO: FInd a way to perform the file ancestry check
				// on the relative steps between us and the project dir!
			}
		}

		// Now check for file's existence.
		if (Files.notExists(filePath)) {
			try {
				Files.createFile(filePath);
			} catch (IOException e) {
				System.out.println(
						"File creation failed on file >>" + filePath + "<<: An exception occurred with trace ");
				e.printStackTrace();
			}
			System.out.println("File creation succeeded for file >>" + filePath + "<<.");
			return true;
		} else {
			System.out.println("File creation did not occur for file >>" + "<<: The file already exists.");
			return false;
		}
	}

	// TODO Interesting. A file that did not exist was automatically create for
	// writing!!!!
	public static boolean writeToFile(Path filePath, ArrayList<Student> toBeWritten) {
	
		// TODO add existence check!
		File file = filePath.toFile();
		

		// The second argument to FileOutputStream sets the stream to append mode.
		// This is the try-with-resource block.
		try (PrintWriter output = new PrintWriter(new FileOutputStream(file, true));) {
			// Evidently System.out and PrintWriter share ancestry or interfaces. \
			// TODO: Find out which!
			for (Student s : toBeWritten)
			{
				output.println(s);
			}
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("The file >>" + filePath + "<< does not exist.");
			e.printStackTrace();
			return false;
		} finally {
			// Just to illustrate the use of finally...although the try-with-resource is
			// effectively already adding one...
		}

		// If we're here, something went wrong...
		// return false;
	}
	
	

	public static boolean readFromFile(Path filePath) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()));

			String line = br.readLine();
			while (line != null) {
				System.out.println(line);
				line = br.readLine();

			}
			br.close();
		} catch (FileNotFoundException fnfe) {
			// TODO Auto-generated catch block
			fnfe.printStackTrace();
		} catch (IOException e) {

		} finally {

		}
		return false;

	}
	
	public static ArrayList<Student> readStudentsFromFile(Path filePath) {
		ArrayList<Student> studentsToReturn = new ArrayList<>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()));

			String line = br.readLine();

			while (line != null) {
				System.out.println(line);
				Student s = Student.fromCsvString(line);
				studentsToReturn.add(s);
				
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException fnfe) {
			// TODO Auto-generated catch block
			fnfe.printStackTrace();
		} catch (IOException e) {

		} finally {

		}
		
		return studentsToReturn;

	}

}
