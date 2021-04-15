// mtreadwa && Max Treadway

package lab5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

abstract class Document {
	String filename;
	StringBuilder fileContent = new StringBuilder();
	
	Document(String filename) {
		this.filename = filename;
	}
	
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
	
	abstract void collectDocInfo();
}
