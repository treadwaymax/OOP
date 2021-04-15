// mtreadwa && Max Treadway
package lab3;

public class RegularDoc extends Document {
	
	int wordCount;
	
	public RegularDoc(String filename) {
		super(filename);
	}
	
	@Override
	public void collectDocInfo() {
		wordCount = 0;
		String[] words = fileContent.toString().split("\\s+");
		wordCount += words.length;
		}
}
