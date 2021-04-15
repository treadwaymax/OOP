// mtreadwa && Max Treadway
package lab5;

public class CSVDoc extends Document {
	// make static variable in order to call from DocAnalyst
	int rowCount = 0; 
	int columnCount = 0;
	
	public CSVDoc(String filename) {
		super(filename);
	}

	@Override
	public void collectDocInfo() {
		rowCount = 0;
		columnCount = 0;
		// count number of lines total (rows) in fileContent
		rowCount = fileContent.toString().split("\n").length;
		// count number of columns = number of delimeters in the csv file ; split the string by the comma, then count the number of values
		String[] firstRow; // instantiate a string array "firstRow"
		firstRow = fileContent.toString().split("\n"); // take fileContent as a String split at new lines
		columnCount = firstRow[0].split(",").length; // count strings separated by commas in first row of csv file
	}

}
