package hw2;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class TestTextEditor {
	String fileOne = "sample.txt";
	StringBuilder fileOneContent;
	FileUtilities fileUtilities = new FileUtilities();
	
	@Before
	public void setUp() throws Exception {
		fileOneContent =  fileUtilities.readFile(fileOne);
	}
	
	@Test
	public void test1_readfile() {
		assertEquals("Test file size", 150, fileOneContent.toString().length() );
	}
	
	@Test
	public void test2_wordCount() { 
		assertEquals("Word count for fileOne", 24, fileUtilities.countWords(fileOneContent));
	}
	
	@Test
	public void test3_searchAllExisting() {
		int[] positions = fileUtilities.searchAll(fileOneContent, "file");
		assertEquals("No of found positions", 2, positions.length);
		assertEquals("First position", 17, positions[0]);
		assertEquals("Second position", 28, positions[1]);
		
	}
	
	@Test
	public void test4_searchAllNonExisting() {
		int[] positions = fileUtilities.searchAll(fileOneContent, "java");
		assertEquals("No of found positions", null, positions);
	}
	
	@Test
	public void test5_replaceExisting() {
		fileUtilities.replace(fileOneContent, "file", "document");
		assertEquals("No of replaced words", 2, fileUtilities.searchAll(fileOneContent, "document").length);
		assertEquals("Number of words in replaced text", 24, fileUtilities.countWords(fileOneContent));
	}
	
	@Test
	public void test6_replaceNonExisting() {
		fileUtilities.replace(fileOneContent, "java", "document");
		assertEquals("No of replaced words", null, fileUtilities.searchAll(fileOneContent, "document"));
	}
	
	@Test
	public void test7_searchAllNullFileContent() {
		assertEquals("Test null file content in search", null, fileUtilities.searchAll(null, "document") );
	}
	
	@Test
	public void test8_searchAllNullSearchString() {
		assertEquals("Test null search string", null, fileUtilities.searchAll(fileOneContent, null) );
	}
	
	@Test
	public void test9_replaceNullFileContent() {
		assertEquals("Test null file content in replace", 0, fileUtilities.replace(null, "file", "document") );
	}
	
	@Test
	public void test10_replaceNullOldString() {
		assertEquals("Test null oldString in replace", 0, fileUtilities.replace(fileOneContent, null, "document") );
	}
	
	@Test
	public void test11_replaceNullNewString() {
		assertEquals("Test null newString in replace", 0, fileUtilities.replace(fileOneContent, "file", null) );
	}
}
