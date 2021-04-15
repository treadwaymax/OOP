package lab3;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestDocs {
	
	DocAnalyst docAnalyst;  
	CSVDoc csvDoc = new CSVDoc("books.csv");
	RegularDoc regularDoc = new RegularDoc("sample.txt");
	
	@Before
	public void setUp() throws Exception {
		docAnalyst = new DocAnalyst();
		csvDoc.readFile();
		csvDoc.collectDocInfo();
		regularDoc.readFile();
		regularDoc.collectDocInfo();
	}

	@Test
	public void test1_csvRowCount() {
		assertEquals("Test csv row count", 6, csvDoc.rowCount);
	}
	
	@Test
	public void test2_csvColumnCount() {
		assertEquals("Test csv column count", 4, csvDoc.columnCount);
	}
	
	@Test
	public void test3_regularWordCount() {
		assertEquals("Test regular word count", 24, regularDoc.wordCount);
	}
	
}
