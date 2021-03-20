package lab4;

import java.util.Scanner;

/* This is a dinner simulation program to illustrate several OO concepts such as abstract classes, interfaces, static variables, static final constants, and polymorphism. The Dinner class starts the program. */

public class Dinner { 
	/** main method prints the menu and takes user input in the variable 'choice'.
	 * It then invokes the getFood() method and passes the choice to it.
	 * After getFood() method returns the appropriate Food object, the main method  
	 * invokes the eatFood() method and passes that Food object to it. 
	 * It then prints how many calories have been consumed.
	 * The above is repeated in a loop till the user enters 'n' to the 
	 * question, 'Want some more?' 	 */
	
	public static void main(String[] args) {
		// creates Dinner object for polymorphic dynamic binding
		Dinner dinner = new Dinner();
		//instantiate choice and decision variables
		int choice = 1;
		String decision = "y";
		Scanner input = new Scanner(System.in);
		while (true) {
			System.out.println("What would you like to eat? \n 1. Pizza \n 2. Chips \n 3. Ice cream");
			// get user input and assign to choice var
			choice = input.nextInt();
			if (choice >= 1 || choice <= 3) {
				// pass user input to getFood and pass object to eatFood
				dinner.eatFood(dinner.getFood(choice));
				System.out.println("You have consumed " + Food.calories + " calories");
			}
			System.out.println("Want some more (y/n)?");
			//take user input as next line and pass to decision var
			input.nextLine();
			decision = input.nextLine();
			//if "n" is selected, say Good night and break while loop
			if(decision.equals("n")) {
				System.out.print("Good night!");
				break; 
				}
			}
		input.close();
	}

	/**getFood() takes choice and returns Pizza object, Chips object, or IceCream object for choices 1, 2, and 3 respectively. */
	
	public Food getFood(int choice) {
		Food f = null;
		switch (choice) {
		case 1: Pizza pizza = new Pizza(); f = pizza; break;
		case 2: 	Chips chips = new Chips(); f = chips; break;
		case 3: 	IceCream iceCream = new IceCream(); f = iceCream; break;
		default: break;
		}
		return f;
	}
	
	/**eatFood() method takes Food object as an argument. 
	 * It checks if the object is Pizza. If yes, then 
	 * it invokes its heatIt() method
	 * Then it invokes the eat() method of the Food object. 
	 */
	
	public void eatFood(Food f) {
		// check if object f is of a certain type and execute response
		if (f instanceof Pizza) {
			((Pizza)f).heatIt();
			System.out.println(((Pizza)f).eat());
		}
		if (f instanceof IceCream) System.out.println(((IceCream)f).eat());
		if (f instanceof Chips) System.out.println(((Chips)f).eat());
	}
}
