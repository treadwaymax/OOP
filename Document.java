package lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

abstract class Document {
	String filename;		//stores file name provided in the constructor
	StringBuilder fileContent = new StringBuilder();
	
	Document(String filename) {
		this.filename = filename;
	}
	
	/**readFile() method takes Document's filename and opens the file. 
	 * Then it reads its contents into fileContent StringBuilder. 
	 * It preserves the newline breaks in the content.
	 */
	void readFile() {
		File file = new File(filename);
		try {
			Scanner input = new Scanner(file);
			while (input.hasNextLine()) {
				fileContent.append(input.nextLine() + "\n");
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/**collectDocInfo() to be implemented by child classes */
	abstract void collectDocInfo();
}
