import java.rmi.MarshalledObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.function.Predicate;

 //import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{

    // ********** add your code below ***********


    /**
     * This default constructor create empty polynom
     */
    public Polynom(){
        //polynom = new ArrayList<>();
        init();
    }

    /**
     * Copy constructor
     * @param p polynom for copy
     */
    public Polynom(Polynom p){
        init();
        add(p.copy());
    }

    /**
     * string polynom constructor
     * @param polynomString string of polynom
     */
    public Polynom(String polynomString){
        new Polynom();
        if (!init(polynomString)){
            System.out.println("is invalid polynom, default polynom created");
        }
    }

    /**
     * Add p1 to this Polynom
     * @param p1 polynom to add
     */
    @Override
    public void add(Polynom_able p1) {
        Iterator iterator = p1.iteretor();
        while (iterator.hasNext()){
            add((Monom) iterator.next());
        }
    }

    /**
     * Add m1 to this polynom
     * @param m1 Monom
     */
    @Override
    public void add(Monom m1) {
        if (m1.get_coefficient() != 0) {
            boolean isAdded = false;
            Iterator iterator = iteretor();
            while (iterator.hasNext() && !isAdded) {
                Monom currentMonom = (Monom) iterator.next();
                if (currentMonom.get_power() == m1.get_power()) {
                    currentMonom.add(m1);
                    isAdded = true;
                    if (currentMonom.get_coefficient() == 0) {
                        iterator.remove();
                    }
                } else if (currentMonom.get_power() < m1.get_power()) {
                    polynom.add(polynom.indexOf(currentMonom), m1);
                    isAdded = true;
                }
            }
            if (!isAdded) {
                polynom.add(polynom.size(), m1);
            }
        }
    }

    /**
     * Substract p1 from this polynom
     * @param p1 polynom to subtract
     */
    @Override
    public void substract(Polynom_able p1) {
        Iterator p1Iterator = p1.iteretor();
        while (p1Iterator.hasNext()) {
            Monom monom = (Monom) p1Iterator.next();
            Monom monomToAdd = new Monom(monom.get_coefficient() * -1, monom.get_power());
            add(monomToAdd);
        }
    }


    /**
     * multyply this polynom with p1
     * @param p1 polynom to multiply
     */
    @Override
    public void multiply(Polynom_able p1) {
        Polynom_able copyPolynom = copy();
        init();
        Iterator iterator = copyPolynom.iteretor();
        while (iterator.hasNext()){
            Monom currentMonom = (Monom) iterator.next();
            Iterator p1Iterator = p1.iteretor();
            while (p1Iterator.hasNext()){
                Monom multMonom = new Monom(currentMonom);
                multMonom.multiply((Monom) p1Iterator.next());
                Monom monom = new Monom(multMonom);
                add(monom);
            }
        }
    }


    /**
     * Check if this polynom is equals to p1 for every x
     * @param p1 to check
     * @return true if this equals polynom, else return false
     */
    @Override
    public boolean equals(Polynom_able p1) {
        boolean isEquals = true;
        Iterator iterator = iteretor();
        Iterator p1Iterator = p1.iteretor();
        while (iterator.hasNext() && isEquals){
            if (p1Iterator.hasNext()){
                Monom_Comperator mc = new Monom_Comperator();
                if (mc.compare((Monom) iterator.next(), (Monom) p1Iterator.next()) != 0){
                    isEquals = false;
                }
            } else {
                isEquals = false;
            }
        }
        if (p1Iterator.hasNext()){
            isEquals = false;
        }
        return isEquals;
    }


    /**
     * Check if this polynom is zero polynom
     * @return true if this polynom is zero polynom and else return false
     */
    @Override
    public boolean isZero() {
        boolean isZero = true;
        Iterator iterator = iteretor();
        while (iterator.hasNext() && isZero){
            Monom currentMonom = (Monom) iterator.next();
            if (currentMonom.get_coefficient() != 0 && currentMonom.get_power() != 0){
                isZero = false;
            }
        }
        return isZero;
    }


    /**
     * Find the root of this polynom between x0 and x1
     * @param x0 starting point
     * @param x1 end point
     * @param eps step (positive) value
     * @return the root of this polynom between x0 and x1, if cannot find retun Double.MAX_VALUE
     */

    @Override
    public double root(double x0, double x1, double eps) {
    	double smallX = x0;
        double bigX = x1;
        double xMiddle = (x0 + x1)/2;
        if (f(x0)*f(x1) > 0){
            return Double.MAX_VALUE;
        } else {
            while (xMiddle > smallX && xMiddle < bigX){
                boolean flag = true;
                if (f(smallX) > f(bigX)){
                    flag = false;
                }
                if (Math.abs(f(xMiddle)) < eps){
                    return xMiddle;
                } else if (f(xMiddle) > 0){
                    if (flag){
                        bigX = xMiddle;
                        xMiddle = (smallX + bigX)/2;
                    } else {
                        smallX = xMiddle;
                        xMiddle = (smallX + bigX)/2;
                    }
                } else {
                    if (flag) {
                        smallX = xMiddle;
                        xMiddle = (smallX + bigX) / 2;
                    } else {
                        bigX = xMiddle;
                        xMiddle = (smallX + bigX) / 2;
                    }
                }
            }
        }
        return xMiddle;
    }


    /**
     * create a copy of this polynom
     * @return copy of this polynom
     */
    @Override
    public Polynom_able copy() {
        Polynom p = new Polynom();
        Iterator iterator = iteretor();
        while (iterator.hasNext()){
            Monom monom = new Monom((Monom) iterator.next());
            p.add(monom);
        }
        return p;
    }

    /**
     * create derivative of this polynom
     * @return derivative of this polynom
     */
    @Override
    public Polynom_able derivative() {
        Polynom p = new Polynom();
        Iterator iterator = iteretor();
        while (iterator.hasNext()){
            Monom monom = (Monom) iterator.next();
            monom = monom.derivative();
            p.add(monom);
        }
        return p;
    }


    /**
     * Calculate the area of this polynom between x0 and x1 in relative of eps
     * @param x0
     * @param x1
     * @param eps
     * @return the area of this polynom between x0 and x1 in relaive of eps
     */
    @Override
    public double area(double x0, double x1, double eps) {
        double min = x0;
        double max = x1;
        if (x1 < x0){
            min = x1;
            max = x0;
        }
        double area = 0;
        double halfEps = eps/2;
        while (min <= max){
            double fx = f(min+halfEps);
            if (fx > 0) {
            		area += (eps*fx);
            }
            min += eps;
        }
        return area;
    }


    /**
     * @return iterator of polynom
     */
    @Override
    public Iterator<Monom> iteretor() {
        return polynom.iterator();
    }

    /**
     * @param x
     * @return the value of the polynom by x
     */
    @Override
    public double f(double x) {
        double result = 0;
        Iterator<Monom> iterator = iteretor();
        while (iterator.hasNext()){
            Monom currentMonom = iterator.next();
            result += currentMonom.f(x);
        }
        return result;
    }


    /**
     * Initialize polynom by string
     * @param p
     * @return polynom initialized by the string if the string is valid else return empty polynom
     */
    public boolean init(String p){
        Polynom polynom1 = new Polynom();
        boolean isInit = true;
        String spaceless = p.replace(" ", "");
        StringTokenizer stringTokenizer = new StringTokenizer(spaceless, "+-", true);

        while (stringTokenizer.hasMoreElements() && isInit){
            Monom monom = new Monom();
            String currentToken = stringTokenizer.nextToken();
            if (currentToken.equals("-") || currentToken.equals("+")){
                if (stringTokenizer.hasMoreTokens()){
                    String monomToInit = stringTokenizer.nextToken();
                    if (currentToken.equals("-")){
                        monomToInit = "-" + monomToInit;
                    }
                    if (monom.init(monomToInit)) {
                        polynom1.add(monom);
                    } else {
                        isInit = false;
                    }
                }
            } else {
                if (stringTokenizer.hasMoreTokens()) {
                    if (monom.init(currentToken)) {
                        polynom1.add(monom);
                    } else {
                        isInit = false;
                    }
                }
            }
        }
        if (isInit){
            init();
            Iterator iterator = polynom1.iteretor();
            while (iterator.hasNext()){
                add((Monom) iterator.next());
            }
        }
        return isInit;
    }

    /**
     * @return string of the polynom
     */
    @Override
    public String toString() {
        String toString = "";
        Iterator iterator = iteretor();
        if (iterator.hasNext()){
            Monom currentMonom = (Monom) iterator.next();
            if (currentMonom.get_power() == 0){
                toString = String.valueOf(currentMonom.get_coefficient());
            } else {
                toString += currentMonom.toString();
            }
        } else {
            toString = "0";
        }
        while (iterator.hasNext()){
            Monom currentMonom = (Monom) iterator.next();
            if (currentMonom.get_coefficient() < 0){
                toString += currentMonom.toString();
            } else {
                toString += "+" + currentMonom.toString();
            }
        }
        return toString;
    }


    //****************** Private Methods and Data *****************

    private ArrayList<Monom> polynom;

    /**
     * helper method for initialize the polynom
     */
    private void init(){
        polynom = null;
        polynom = new ArrayList<Monom>();
    }
	
}
