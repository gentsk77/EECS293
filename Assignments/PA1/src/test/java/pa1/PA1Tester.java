package pa1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

/**
 * Unit test for Programming Assignment 1.
 * 
 * @author Yue Shu
 */
public class PA1Tester {
    /**
     * Test longestSmallerPrefix.
     */
    @Test
    public void testLongestSmallerPrefix() {
        List<Integer> a = Arrays.asList(1, 1, 3, 4);
        List<Integer> b = Arrays.asList(1, 2, 3);

        List<String> c = Arrays.asList("a", "b", "c");
        List<String> d = Arrays.asList("a", "c", "b", "d");

        List<Integer> resultInt;
        List<String> resultString;

        // test zero
        try {
            assertEquals("test zero", PA1.longestSmallerPrefix(new ArrayList<>(), b, Comparator.naturalOrder()),
                    new ArrayList<>());
        } catch (Exception exception) {
            fail();
        }

        // test int
        try {
            resultInt = PA1.longestSmallerPrefix(a, b, Comparator.naturalOrder());
            assertEquals("test integer lists", resultInt, Arrays.asList(1, 1, 3));
        } catch (Exception exception) {
            fail();
        }

        // test string
        try {
            resultString = PA1.longestSmallerPrefix(c, d, Comparator.naturalOrder());
            assertEquals("test string lists", resultString, Arrays.asList("a", "b"));
        } catch (Exception exception) {
            fail();
        }

        // test fail
        try {
            PA1.longestSmallerPrefix(null, b, null);
            fail();
        } catch (IOException exception) {

        }

    }
}
