package lab6;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestFraction {

	@Test
	public void testDefaultConstructor() {
		Fraction f = new Fraction();
		assertEquals(1, f.numerator);
		assertEquals(1, f.denominator);
	}
	
	@Test
	public void testNonDefaultConstructor() {
		Fraction f = new Fraction(3, 4);
		assertEquals(3, f.numerator);
		assertEquals(4, f.denominator);
	}
	
	@Test
	public void testGCDWithDivisor() {
		Fraction f = new Fraction();
		assertEquals("Testing 12/4 ", 4, f.findGCD(12,4));
	}
	
	@Test
	public void testGCDWithSmallerNumerator() {
		Fraction f = new Fraction();
		assertEquals("Testing 5/15 ", 5, f.findGCD(5, 15));
	}
	@Test
	public void testGCDWithDenominatorZero() {
		Fraction f = new Fraction();
		assertEquals("Testing 1/0 ", 1, f.findGCD(1,0));
	}
	
	@Test
	public void testGCDWithNumeratorZero() {
		Fraction f = new Fraction();
		assertEquals("Testing 0/1 ", 1, f.findGCD(0,1));
	}
	
	@Test
	public void testGCDWithoutDivisor() {
		Fraction f = new Fraction();
		assertEquals("Testing 13/7 ", 1, f.findGCD(13,7));
	}
	
	@Test
	public void testToString() {
		Fraction f = new Fraction(13, 7);
		assertEquals("Testing toString", "13/7", f.toString());
	}
	
	@Test
	public void testAdd() {
		Fraction f1 = new Fraction (1, 2);
		Fraction f2 = new Fraction (3, 4);
		Fraction f3 = f1.add(f2);
		assertEquals(5, f3.numerator);
		assertEquals(4, f3.denominator);
	}

	@Test
	public void testToDecimal() {
		Fraction f = new Fraction (1, 2);
		assertEquals(0.5, f.toDecimal(), 0D);		
	}
}
