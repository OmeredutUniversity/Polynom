import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MonomTest {

	Monom m1;
	Monom m2;
	Monom m3;
	
	@Before
	public void setUp() throws Exception {
		m1 = new Monom(5, 4);
		m2 = new Monom(-4.5, 2);
		m3 = new Monom(2.3, 1);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testMonom() {
		m1 = new Monom();System.out.println("Default Constructor" + m1);
	}

	@Test
	public final void testMonomDoubleInt() {
		System.out.println("constractor: " + new Monom(0, 5));
		System.out.println("constractor: " + new Monom(5, -1));
	}

	@Test
	public final void testF() {
		System.out.println("m1.f(3) = " + m1.f(3) + " m2.f(3) = " + m2.f(3) + " m3.f(3) = " + m3.f(3));
		System.out.println("m1.f(-2) = " + m1.f(-2) + " m2.f(-2) = " + m2.f(-2) + " m3.f(-2) = " + m3.f(-2));
	}

	@Test
	public final void testValueOfX() {
		System.out.println("f(x) = 25, value of x = " + m1.valueOfX(25));
	}

	@Test
	public final void testAdd() {
		System.out.println("m1.add(m2) = " + m1.add(m2) + " " + m1);
		System.out.println("m2.add(6*x^2) = " + m2.add(new Monom (6, 2)) + " " + m2);
	}

	@Test
	public final void testMultiply() {
		m1.multiply(m2);
		System.out.println("m1.multiply(m2) = " + m1);
	}

	@Test
	public final void testDerivative() {
		m3 = m2.derivative();
		System.out.println("m2.derivative() = " + m3);
	}

	@Test
	public final void testInit() {
		m2.init("3*x^7");
		m3.init("hello");
		System.out.println("m2.init() = " + m2);
		System.out.println("m3.init(hello) = " + m3);
	}

	@Test
	public final void testToString() {
		System.out.println("m1.toString() = " + m1.toString());
		System.out.println("m2.toString() = " + m2.toString());
		System.out.println("m3.toString() = " + m3.toString());
	}

}
