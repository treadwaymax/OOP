//mtreadwa && Max Treadway

package lab7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**StudentRoster class reads data from roster.csv file and loads Student objects into its studentList.
 * It has two inner classes FirstNameComparator and ScoreComparator to sort students on their firstName and score respectively.
 */
public class StudentRoster {
	List<Student> studentList = new ArrayList<>();
	StringBuilder fileContent = new StringBuilder();
	
	//Do not change this method
	public static void main(String[] args) {
		StudentRoster sr = new StudentRoster();
		sr.readData();
		sr.sortByLastName();
		System.out.println("********************** Sorted by Last name **********************");
		sr.printRoster();
		sr.sortByFirstName();
		System.out.println("********************** Sorted by First name **********************");
		sr.printRoster();
		sr.sortByScore();
		System.out.println("********************** Sorted by Score **********************");
		sr.printRoster();
	}
	
	//Do not change this method
	void printRoster() {
		System.out.println(" #. Last name\t\tFirst name\tAndrew ID\tScore");
		System.out.println("----------------------------------------------------------------------");
		int count = 1;
		for (Student s: studentList) System.out.printf("%2d. %-15s\t%-15s\t%-10s\t%.2f%n", count++,
				s.lastName, s.firstName, s.andrewID, s.score);
	}
	
	/**readData() method opens and reads roster.csv file and 
	 * loads the studentList arrayList with Student objects. */
	void readData() {
		try {
			Scanner scanner = new Scanner(new File("roster.csv"));
			Scanner dataScanner = null;
			int index = 0;	
			String lastName = null;
	        String firstName = null;
	        String andrewID = null;
	        float score = 0;	
			while (scanner.hasNextLine()) {
				dataScanner = new Scanner(scanner.nextLine());
				dataScanner.useDelimiter(",");
				while (dataScanner.hasNext()) {
					String data = dataScanner.next();
					if (index == 0)
						lastName = data;
					else if (index == 1)
						firstName = data;
					else if (index == 2)
						andrewID = data;
					else if (index == 3)
						score = Float.parseFloat(data);
					else
						System.out.println("invalid data::" + data);
					index++;
				}
				Student student = new Student(lastName, firstName, andrewID, score);
				index = 0;
				studentList.add(student);
			}
			scanner.close();	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	void sortByLastName() {
		//ascending
		Collections.sort(studentList);
	}
	
	void sortByFirstName() {
		//ascending
		Collections.sort(studentList, new FirstNameComparator());
	}
	
	void sortByScore() {
		//descending order
		Collections.sort(studentList, new ScoreComparator());

	}
	
	//write your Comparators here
	public class FirstNameComparator implements Comparator <Student> {
		public int compare (Student s1, Student s2) {
			return s1.firstName.compareTo(s2.firstName);
		}
	}
	
	public class ScoreComparator implements Comparator <Student>{
		public int compare (Student s1, Student s2) {
			//descending
			int result = Float.compare(s2.score, s1.score);
			if (result == 0)
			result = Float.compare(s2.score, s1.score);
			return result;
		}
	}
}
