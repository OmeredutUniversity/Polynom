
public class Test {
	public static void main(String[] args) {
		Polynom p = new Polynom("0.2*x^4-1.5*x^3+3.0*x^2-1*x^1-5*x^0");
		p.showPolynom(-2, 6);
		System.out.println(p.area(-2, 6, 0.01));
	}
}
