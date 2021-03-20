package lab4;

public interface Heatable {
	// set a final variable for pizza's hot serving temp at 165
	static final int HOTSERVINGTEMPERATURE = 165;
	
	// establish abstract method to set pizza to temp of 165
	abstract void heatIt();
}
