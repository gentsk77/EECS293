package pa1;

import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * PA1 contains the source code for Programming Assignment 1.
 * 
 * EECS293 Spring 2020, Prof. Liberatore
 *
 * @author Yue Shu
 */
public class PA1 {

    /**
     * Finds the longest smaller prefix in two lists, a and b, where a smaller
     * prefix is a prefix of list a whose elements are less than or equal to the
     * corresponding element in list b.
     *
     * @param <T> generic type of the elements in lists a and b
     * @param a   the list a to be compared
     * @param b   the list b to be compared
     * @param cmp the comparator to be used in comparing the elements in list a and
     *            b
     * @return a list of the longest smaller prefix in list a and b
     * @throws IOException throws exception when the input argument is found invalid
     */
    public static <T> List<T> longestSmallerPrefix(List<T> a, List<T> b, Comparator<? super T> cmp) throws IOException {
        List<T> result = new LinkedList<>();

        // performs sanity check on the input parameters
        try {
            sanityCheck(a, b, cmp);
        } catch (IOException e) {
            System.out.println("Invalid input: none of the arguments should be null.");
            throw e;
        }

        Iterator<T> iteratorA = a.iterator();
        Iterator<T> iteratorB = b.iterator();

        while (iteratorA.hasNext() && iteratorB.hasNext()) {
            T elementA = iteratorA.next();
            T elementB = iteratorB.next();

            if (cmp.compare(elementA, elementB) <= 0) {
                result.add(elementA);
            } else {
                break;
            }
        }

        return result;
    }

    /**
     * Performs sanity check on the input parameters to ensure they are valid.
     *
     * @param <T> generic type of the elements in lists a and b
     * @param a   list a to be checked, which should not be null
     * @param b   list b to be checked, which should not be null
     * @param cmp the comparator to be checked, which should not be null
     * @throws IOException throws exception when the sanity check is not passed
     */
    private static <T> void sanityCheck(List<T> a, List<T> b, Comparator<? super T> cmp) throws IOException {
        if (a == null || b == null || cmp == null) {
            throw new IOException("Check input type for null value.");
        }
    }
}