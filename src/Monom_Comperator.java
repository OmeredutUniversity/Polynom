
import java.util.Comparator;

public class Monom_Comperator implements Comparator<Monom> {


    // ******** add your code below *********

    /**
     * @param o1 first monom to compare
     * @param o2 second monom to compare
     * @return 0 if o1 equals o2. if o1 biggest return 1 and -1 if o2 is biggest
     */
    @Override
    public int compare(Monom o1, Monom o2) {
        int compare = 0;
        if (o1.get_power() > o2.get_power()){
            compare = 1;
        } else if (o1.get_power() < o2.get_power()){
            compare = -1;
        }
        return compare;
    }
}
