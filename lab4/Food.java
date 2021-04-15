// mtreadwa && Max Treadway

package lab4;

public abstract class Food {
	static int calories; // go here or in food class?
	int temperature;
	
	public Food() {
		System.out.println("Here comes food!");
	}
	
	// abstract method implemented by child classes
	abstract String eat();
}
