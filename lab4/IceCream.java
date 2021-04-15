// mtreadwa && Max Treadway

package lab4;

public class IceCream extends Food{
	static final int ICECREAM_CALORIES = 500;
	
	public IceCream() {
		//increment calorie counter in main method by 500
		calories += ICECREAM_CALORIES;
		System.out.println("Serving Icecream");
	}

	@Override
	public String eat() {
		// this string object is returned to eat() method in eatFood()
		return "Slurp Slurp";
	}
}