import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PolynomTest {

	Polynom p1;
	Polynom p2;
	Polynom p3;
	Monom m1;
	Monom m2;
	Monom m3;

	@Before
	public void setUp() throws Exception {
		m1 = new Monom(0.2,2);
		m2 = new Monom(-3,4);
		m3 = new Monom(2,1);
		p1 = new Polynom();
		p2 = new Polynom();
		p3 = new Polynom();
		p1.add(m1);
		p1.add(m2);
		p1.add(m3);
	}

	@After
	public void tearDown() throws Exception {
	}



	@Test
	public final void testPolynomPolynom() {		
		p2 = new Polynom(p1);
		System.out.println("p1 = " + p1 + "; p2 = " + p2);
	}

	@Test
	public final void testPolynomString() {
		p3 = new Polynom("3*x^5 - 2*x^2 + 1*x^4 + 3*x^0");
		System.out.println("p3 = "+ p3);
	}

	@Test
	public final void testAddPolynom_able() {
		p3.init("3*x^5+1*x^4-2*x^2+3*x^0");
		p2.init("-3*x^4+0.2*x^2+2*x^1");
		p3.add(p2);
		System.out.println("new p3 = " + p3);
	}

	@Test
	public final void testAddMonom() {
		p2.add(m1);
		p2.add(m3);
		p2.add(m3);
		p2.add(m2);
		System.out.println("p2 = " + p2);
	}

	@Test
	public final void testSubstract() {		
		p2.add(m1);
		p2.add(m2);
		p1.substract(p2);
		System.out.println("p3 - p2 = " + p1);
	}

	@Test
	public final void testMultiply() {
		p3.add(new Monom(-5, 2));
		p3.add(new Monom(3, 1));
		p1.multiply(p3);
		System.out.println("p1 multiply p3 = " + p1);
	}

	@Test
	public final void testEqualsPolynom_able() {
		p2.add(m1);
		p2.add(m2);
		p2.add(m3);
		System.out.println("p2 equals p1 ? " + p2.equals(p1));
	}

	@Test
	public final void testIsZero() {
		System.out.println("p1 is zero ? " + p1.isZero());
		System.out.println("p2 is zero ? " + p2.isZero());
	}

	@Test
	public final void testRoot() {
		System.out.println("root of p1 between -8 and 0.5 = " + p1.root(-8, 0.5, Double.MIN_VALUE));
	}

	@Test
	public final void testCopy() {
		p3 = (Polynom)p1.copy();
		System.out.println("p3 copy of p1 = " + p3);
	}

	@Test
	public final void testDerivative() {
		p2 = (Polynom)p1.derivative();
		System.out.println("p1.derivative() = " + p2);
	}

	@Test
	public final void testArea() {
		System.out.println("area of p1 between -3 and 2 = " + p1.area(-3, 2, 0.001));
	}

	@Test
	public final void testF() {
		System.out.println("p1.f(6) = " + p1.f(6));
	}
	
	@Test
	public final void testShowPolynom() {
		Polynom p = new Polynom("0.2*x^4-1.5*x^3+3.0*x^2-1*x^1-5*x^0");
		p.showPolynom(-2, 6);
		System.out.println(p.area(-2, 6, 0.01));
	}

	@Test
	public final void testToString() {
		System.out.println(p1.toString());
	}
	
	

}
