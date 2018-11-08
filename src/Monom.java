
/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative),
 * see: https://en.wikipedia.org/wiki/Monomial
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply.
 *
 * @author Boaz
 */
public class Monom implements function {

	/**
	 * This constructor create zero monom
	 */
	public Monom() {
		new Monom(0, 0);
	}

	/**
	 * This constructor create monom with coefficient and power
	 * @param a is the coefficient of the monom
	 * @param b is the power of the monom
	 */
	public Monom(double a, int b) {
		if (b >= 0){
			this.set_coefficient(a);
			this.set_power(b);
		} else {
			new Monom(0,0);
            System.out.println("This Monom invalid, zero monom created");
			//throw new IllegalArgumentException("The power invalid");
		}
	}


	/**
	 * This is a copy constructor
	 * @param ot monom
	 */
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	// ***************** add your code below **********************

	/**
	 * @param x is the variable of the monom
	 * @return the value of the monom with x
	 */
	@Override
	public double f(double x) {
		return _coefficient * (Math.pow(x, _power));
	}

	/**
	 * @param fx is the value of f(x)
	 * @return the value of x, if is invalid return Nan
	 */
	public double valueOfX(double fx) {
		boolean isNegative = false;
		boolean powerIsOdd = (_power%2 != 0);
		if (fx*_coefficient < 0){
			isNegative = true;
		}
		double result = fx / _coefficient;
		if (powerIsOdd && isNegative){
			result = -result;
		}
		result = root(result, _power);
		if (powerIsOdd && isNegative){
			result = -result;
		}
		return result;
	}

	/**
	 * @return the power of the monom
	 */
	public int get_power() {
		return _power;
	}

	/**
	 * @return the coefficient of the monom
	 */
	public double get_coefficient() {
		return _coefficient;
	}

	/**
	 * @param monom to add
	 * @return true if the addition success and false if not
	 */
	public boolean add(Monom monom) {
		if (_power == monom.get_power()) {
			_coefficient += monom.get_coefficient();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method multiply the monom with get monom
	 * @param monom get to multiply
	 */
	public void multiply(Monom monom) {
		_coefficient *= monom.get_coefficient();
		_power += monom.get_power();
	}

	/**
	 * @return the derivative
	 */
	public Monom derivative() {
		double coefficient = _coefficient * _power;
		int power = _power - 1;
		if (power < 0) {
			power = 0;
		}
		return new Monom(coefficient, power);
	}

	/**
	 * This method get monomString and initialize the monom if the string is valid else
	 * initalized the monom with 0
	 * @param monomString is a string of monom
	 * @return true if the string is valid and the initialized succes, else return false
	 */
	public boolean init(String monomString) {

		String initString = monomString.replace(" ", "");
		String coefficientString = "0";
		String powerString = "0";
		boolean isInit = true;
		boolean wasMult = false;
		boolean wasX = false;
		boolean wasPower = false;
		boolean wasDot = false;
		boolean wasMinus = false;
		for (int i = 0; i < initString.length() && isInit; i++) {
			if (!wasMult){
				if (initString.charAt(i) == '*'){
					wasMult = true;
				} else {
					if (initString.charAt(i) == '-' || initString.charAt(i) == '+' || initString.charAt(i) == '.' ||
							(initString.charAt(i) >= '0' && initString.charAt(i) <= '9')){
						if (initString.charAt(i) == '-'){
							wasMinus = !wasMinus;
						} else {
							if (initString.charAt(i) == '.') {
								if (wasDot) {
									isInit = false;
								} else {
									wasDot = true;
								}
							}
							coefficientString += initString.charAt(i);
						}
					} else if (initString.charAt(i) == 'x' || initString.charAt(i) == 'X'){
						wasMult = true;
						wasX = true;
					} else {
						isInit = false;
					}
				}
			} else {
				if (!wasX){
					if (initString.charAt(i) == 'x' || initString.charAt(i) == 'X'){
						wasX = true;
					} else {
						isInit = false;
					}
				} else {
					if (!wasPower){
						if (initString.charAt(i) == '^'){
							wasPower = true;
						} else {
							isInit = false;
						}
					} else {
						if (initString.charAt(i) >= '0' && initString.charAt(i) <= '9') {
							powerString += initString.charAt(i);
						} else {
							isInit = false;
						}
					}
				}
			}
		}
		if (isInit) {
			_coefficient = Double.parseDouble(coefficientString);
			_power = Integer.parseInt(powerString);
			if (_power < 0){
				isInit = false;
				_power = 0;
				_coefficient = 0;
			} else if (wasMinus) {
				_coefficient *= -1;
			}
		} else {
			_coefficient = 0;
			_power = 0;
		}
		return isInit;
	}





	/**
	 * @return string of Monom
	 */
	@Override
	public String toString() {
		String coefficient = String.valueOf(_coefficient);
		if (_coefficient - (int)_coefficient == 0){
			coefficient = String.valueOf((int)_coefficient);
		}
		String toString = coefficient + "*x^" + _power;
		if (_power == 0) {
			toString = coefficient;
		} else if (_power == 1) {
			toString = coefficient + "*x";
		}
		if (_coefficient == 0) {
			toString = "0";
		}
		return toString;
	}

	//****************** Private Methods and Data *****************

	/**
	 * set the coefficient from the user in the monom
	 * @param a coefficient
	 */
	private void set_coefficient(double a) {
		this._coefficient = a;
	}

	/**
	 * set the power from the user in the monom
	 * @param p coefficient
	 */
	private void set_power(int p) {
		if (p > 0) {
			this._power = p;
		}
	}

	/**
	 * helper function that initialize this monom with the coefficient and the power
	 * @param coefficient of the monom
	 * @param power of the monom
	 */
	private void init(double coefficient, int power) {
		set_coefficient(coefficient);
		set_power(power);
	}

	/**
	 * helper method that calculate the root of the base and the exponent
	 * @param base of the root
	 * @param exp the exponent of the root
	 * @return the root
	 */
	private double root(double base, double exp) {
		double result = Math.pow(base, 1 / exp);
		return result;
	}

	private double _coefficient;

	private int _power;
}
