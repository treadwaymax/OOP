package hw1;

import java.util.Scanner;

/**
 * TextEditor is the main class of the application. It starts with creating its
 * own object and then invoking useFileUtilities method.
 */
public class TextEditor {

	FileUtilities fileUtilities = new FileUtilities();
	Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		TextEditor te = new TextEditor();
		System.out.println("Enter file name");
		te.useFileUtilities(te.input.nextLine());
	}

	/**
	 * useFileUtilities() takes filename as input parameter and uses FileUtilities's
	 * instance to perform all file related functions: read, count words, search,
	 * and replace strings.
	 */
	private void useFileUtilities(String filename) {
		StringBuilder fileContent = fileUtilities.readFile(filename);
		System.out.println("\n**** FILE CONTENTS ****");
		System.out.print(fileContent.toString());

		System.out.println("\n**** WORD COUNT ****");
		System.out.printf("This file has %d words%n", fileUtilities.countWords(fileContent));

		System.out.println("\n**** SEARCH ****");
		input = new Scanner(System.in);
		System.out.println("Enter searchString");
		String searchString = input.nextLine();
		int[] positions = fileUtilities.searchAll(fileContent, searchString);
		if (positions != null) {
			System.out.print("The string '" + searchString + "' found at positions: ");
			for (int i = 0; i < positions.length; i++)
				System.out.printf("%d ", positions[i]);
			System.out.println();
			System.out.println("\n**** REPLACE ****");
			System.out.println("Enter new string:");
			String newString = input.nextLine();
			System.out.printf("%d replacements made %n", fileUtilities.replace(fileContent, searchString, newString));

			System.out.println("\n**** FILE CONTENTS AFTER REPLACE ****");
			System.out.println(fileContent.toString());

		} else {
			System.out.printf("Sorry, %s not found %n", searchString);
		}
		System.out.println("**** GOOD BYE! ****");
		input.close();
	}
}
