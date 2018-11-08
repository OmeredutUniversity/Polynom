import java.util.Iterator;

public class Test {

	public static void main(String[] args) {

		monomTest();
		System.out.println("\n\n\n");
		polynomTest();

	}


	/**
	 * This function check all the methods in Monom class
	 */
	public static void monomTest(){
		System.out.println("\n**********Monom test**********\n");

		Monom m1 = new Monom();
		System.out.println("default constructor: " + m1.toString());
		Monom m2 = new Monom(-4.5, 3);
		System.out.println("data constructor: " + m2.toString());
		Monom m3 = new Monom(m2);
		Monom m4 = new Monom(3, -2);
		Monom m5 = new Monom(2, 2);
		System.out.println("copy constructor: m2: " + m2.toString() + " m3: " + m3.toString() + " same pale in memory? " + (m2 == m3));

		System.out.println("\n f(x) = " + m2.f(5));
		System.out.println("\n value of x = " + m2.valueOfX(36));

		System.out.println("\n add m2 to m5 success? " + m5.add(m2) + " the result is: " + m5.toString());
		System.out.println("\n add m3 to m2 success? " + m2.add(m3) + " the result is: " + m2.toString());

		m3.multiply(m5);
		System.out.println("\n multyply m3 with m5 => " + m3.toString());

		Monom m6 = m3.derivative();
		System.out.println("\n derivative of m3: " + m6.toString());
		System.out.println("\n derivative of m6: " + (m6.derivative()).toString());

		m4.init(m2.toString());
		System.out.println("\n m4.init(m2.toString()): " + m4.toString());
		m4.init("2*x^2");
		System.out.println("\n m4.init(String) with 2x^2: " + m4.toString());
		m4.init("3*x^-2");
		System.out.println("\n m4.init(String) with 2x^-2: " + m4.toString());

	}

	
	/**
	 * This function check all the methods in Polynom class
	 */
	public static void polynomTest(){
		System.out.println("\n**********Polynom test**********\n");

		Polynom p1 = new Polynom();
		//System.out.println("default constructor: " + p1.toString());
		Polynom p2 = new Polynom("2*x^4 - 3*x^2 + 4*x^1 - 4*x^0");
		//System.out.println("string constructor: " + p2.toString());
		Polynom p3 = new Polynom(p2);
		//System.out.println("copy constructor: " + p3.toString());
		Polynom p4 = new Polynom("2*x^5 + 4*x^2 - 1*x^2");
		Polynom p5 = new Polynom("2*x^5 + 4*x^3 - 3*x^2");
		Polynom p6 = new Polynom("0*x^5 + 0*x^3 - 0*x^2 + 8");
		Polynom p7 = new Polynom("3*x^3 + 2*x^2 - 1*x^1 + 5");
		Polynom p8 = new Polynom("-3*x^2 + 2*x^1 + 5");
		
		p4.add(p2);
		System.out.println("\n add p2 to p4: " + p4.toString());

		p5.substract(p3);
		System.out.println("\n subtract p3 from p5: " + p5.toString());

		p5.multiply(p4);
		System.out.println("\n multiply p5 and p4: " + p5.toString());

		System.out.println("\n p2 is equals to p3? " + p2.equals(p3));
		System.out.println("\n p2 is equals to p5? " + p2.equals(p5));

		System.out.println("\n p1 is zero polynom? " + p1.isZero());
		System.out.println("\n p3 is zero polynom? " + p3.isZero());
		System.out.println("\n p6 is zero polynom? " + p6.isZero());

		System.out.println("\n derivative of p5: " + (p5.derivative()).toString());
		System.out.println("\n derivative of p6: " + (p6.derivative()).toString());

		double eps = 0.00001;
		System.out.println("area of p7 between 3 and 6 is: " + p7.area(3, 6, eps));
		System.out.println("area of p7 between -3 and 2 is: " + p7.area(-3, 2, eps));

		System.out.println("\n root of p8: " + p8.root(-1.5, 0.5, Double.MIN_VALUE));
		System.out.println("\n root of p8: " + p8.root(1.5, 2.5, Double.MIN_VALUE));
		System.out.println("\n root of p8: " + p8.root(0, 1.5, Double.MIN_VALUE));

		System.out.println("\n f(x) by x=4.5: " + p7.f(4.5));
		System.out.println("\n f(x) by x=-7: " + p7.f(-7));
		System.out.println("\n f(x) by x=0: " + p7.f(0));

	}
}
