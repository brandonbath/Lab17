package co.grandcircus;

import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/*Begin by showing a menu with three options.
 If the user chooses option 1, display a list of countries that have been saved in a file.
If the user chooses option 2, prompt the user to enter a country and then write the
country to the file of countries.
 If the user chooses option 3, display a goodbye message and exit
*/

/*Create a class called CountriesApp that displays a menu and responds to user
choices*/

public class CountriesApp {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		boolean userPressedQuit = false;
		do {
			String countryList = "1. Display a list of countries.";
			String userPrompt = "2. Enter a country to add to the list.";
			String userQuit = "3. Quit.";
			System.out.println("==================================================");
			System.out.println("Please choose an option: ");
			System.out.println(countryList);
			System.out.println(userPrompt);
			System.out.println(userQuit);
			System.out.println("==================================================");
			String fileName = "Countries.txt";
			Path filePath = Paths.get("resources", fileName);

			int userIntInput = Validator.getInt(scan, "", 1, 3);
			//System.out.println(userIntInput);
			ArrayList<Country> countryArrList = new ArrayList<>();

			if (userIntInput == 1) {
				CountriesTextFile.readFromFile(filePath);
				for (Country c : countryArrList) {
					System.out.println(c);
				}

			} else if (userIntInput == 2) {
				System.out.println("What country do you want to add?");
				String userCountryAdd = scan.nextLine();
				System.out.println("What's the population?");
				long userPopAdd = scan.nextLong();
				BigInteger userPopBigInt = BigInteger.valueOf(userPopAdd);
				Country countryObj = new Country(userCountryAdd, userPopBigInt);
				countryArrList.add(countryObj);

				CountriesTextFile.writeCountriesToFile(filePath, countryArrList);

			} else
				userPressedQuit = true;

		} while (!userPressedQuit);
		System.out.println("Goodbye.");
	}

}
