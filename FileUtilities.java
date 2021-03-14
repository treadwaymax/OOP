// mtreadwa && Max Treadway

package hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* FileUtilities class provides some basic tools to read a file, count words, search and replace strings. */

public class FileUtilities {

	/* readFile() method takes filename as a string parameter. It opens the file
	 * which is assumed to be residing in the project folder. It reads the file's
	 * each line into a StringBuilder. While reading, it preserves the line breaks.
	 * It then returns the StringBuilder.
	 */

	public StringBuilder readFile(String filename) {
		File file = new File(filename);
		StringBuilder fileContent = new StringBuilder();
		try {
			// use scanner method to ingest file contents
			Scanner input = new Scanner(file);
			while (input.hasNextLine()) {
				// read each line preserving line breaks
				fileContent.append(input.nextLine() + "\n");
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return fileContent;
	}

	/* wordCount method receives text content in a StringBuilder object and returns
	 * its word count. It considers white-space as a word-delimiter.
	 */
	
	int countWords(StringBuilder fileContent) {
		int wordCount = 0;
		// create string array to store all words in fileContent after converting to string and delimiting at white space.
		String[] words = fileContent.toString().split("\\s+");
		wordCount += words.length;
		return wordCount;
	}

	/* searchAll() method receives text content in a StringBuilder and returns all
	 * the char-positions at which searchString is found. These positions are
	 * counted from the beginning of the file starting with 0. For example, if the
	 * fileContent has: "What a wonderful world", the searchString 'wonder' is at
	 * char-position 7. The search is case-sensitive. If the searchString is not
	 * found, it returns null. Hints: 1. You may find String methods indexOf() and
	 * substring() useful. 2. You may have to traverse the fileContent twice. Once
	 * to count the number of times the searchString is found, and second time to store its positions.
	 */
	
	int[] searchAll(StringBuilder fileContent, String searchString) {
		int[] positions;
		StringBuilder temp = new StringBuilder(fileContent);
		StringBuilder temp2 = new StringBuilder(fileContent);
		// first fileContent iteration to count number of times searchString is found
		int count = (temp.toString().split(searchString, -1).length) -1;
		positions = new int[count];
		int x = 0;
		if (!temp.toString().contains(searchString)) {
			positions = null;
		}
		// second fileContent iteration to store positions
		for (int i = 0; i < temp2.length(); i++) {
			if (temp2.indexOf(searchString) > 0 && (temp2.indexOf(searchString) < temp2.length())) {
				positions[x] = (temp2.indexOf(searchString) + x);
				temp2.delete(temp2.indexOf(searchString), (temp2.indexOf(searchString) + 1));
				x++;
			}
		}
		return positions;
	}
	
	/* replace method receives text content in a StringBuilder object, and 
	 * replaces all occurrences of oldString with new String.
	 * It returns the number of replacements done.
	 * If oldString is not found, it means that no replacement happens. In such a case, it returns 0.
	 */
	
	int replace(StringBuilder fileContent, String oldString, String newString) {
		int replacements = 0;
		// Use previous method to get locations of oldString
		int[] locations;
		StringBuilder temp3 = new StringBuilder(fileContent);
		int count = (temp3.toString().split(oldString, -1).length) -1;
		locations = new int[count];
		// if oldString isn't found, returns 0
		if (!temp3.toString().contains(oldString)) {
			replacements = 0; }
		// iterate through fileContent and replace oldString w/ newString
		for (int i = 0; i < fileContent.length(); i++) {
			if (fileContent.indexOf(oldString) > 0 && (fileContent.indexOf(oldString) < fileContent.length())) {
				int x = 0;
				locations[x] = (fileContent.indexOf(oldString) + x);
		// replace oldString from index start to end, with newString
				fileContent.replace(fileContent.indexOf(oldString), (fileContent.indexOf(oldString) + oldString.length()), newString);
				x++;
				replacements++;
			}
		}
		return replacements;
	}
}
