// mtreadwa && Max Treadway
package lab3;

import java.util.Scanner;

public class DocAnalyst {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter file name");
		String filename = input.next();
		if (filename.endsWith(".csv")) { // check for csv file extension 
			CSVDoc csvDoc = new CSVDoc(filename); // instantiates appropriate class
			csvDoc.readFile(); // invokes readfile method
			csvDoc.collectDocInfo(); // invokes collectDocInfo method	
			System.out.println(filename + " has " + csvDoc.rowCount + " rows and " + csvDoc.columnCount + " columns");
		} else {
			RegularDoc regularDoc = new RegularDoc(filename); // instantiates appropriate class
			regularDoc.readFile(); // invokes readfile method
			regularDoc.collectDocInfo(); // invokes collectDocInfo method
			System.out.println("This file has " + regularDoc.wordCount + " words"); // prints wordcount	
		}
		input.close();
	}

}