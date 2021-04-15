package lab7;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestStudentRoster {
	static StudentRoster studentRoster = new StudentRoster();
	@BeforeClass 
	public static void setupClass() {
		studentRoster.readData();
	}
	
	@Test
	public void test1_studentCount() {
		assertEquals(17, studentRoster.studentList.size());
	}
	
	@Test
	public void test2_studentLastNameSort() {
		studentRoster.sortByLastName();
		assertEquals("Beck", studentRoster.studentList.get(0).lastName);
	}
	
	@Test
	public void test3_studentFirstNameSort() {
		studentRoster.sortByFirstName();
		assertEquals("Alan", studentRoster.studentList.get(0).firstName);
	}

	@Test
	public void test4_studentScoreSort() {
		studentRoster.sortByScore();
		assertEquals(9.3, studentRoster.studentList.get(0).score, .01);
	}
}
