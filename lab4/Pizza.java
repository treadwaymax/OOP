// mtreadwa && Max Treadway

package lab4;

public class Pizza extends Food implements Heatable {
	static final int PIZZA_CALORIES = 800;

	public Pizza() {
		//increment total calories by 800
		calories += PIZZA_CALORIES;
		System.out.println("Serving Pizza");
	}
	
	@Override
	public void heatIt(){
		// sets pizza food temp to 165
		temperature = Heatable.HOTSERVINGTEMPERATURE;
		System.out.println("Now its hot @ 165 degrees");
	}

	@Override
	public String eat() {
		// this string object is returned to eat() method in eatFood()
		return "Chomp Chomp";
	}
}