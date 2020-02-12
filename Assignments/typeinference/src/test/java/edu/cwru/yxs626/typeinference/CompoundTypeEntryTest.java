package edu.cwru.yxs626.typeinference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CompoundTypeEntryTest {

    private static final CompoundType validCompoundType = CompoundType.of("HashMap", 2);

    private static final List<TypeEntry> validSubTypes = new ArrayList<>(
            Arrays.asList(BasicType.of("String"), BasicType.of("Type")));

    @Test
    public void testGetType() {
        try {
            assertEquals("Type doesn't match.", validCompoundType,
                    CompoundTypeEntry.of(validCompoundType, validSubTypes).getType());
        } catch (Exception e) {
            fail("Caught exception.");
        }
    }

    @Test
    public void testGetSubTypes() {
        try {
            CompoundTypeEntry testCompoundTypeEntry = CompoundTypeEntry.of(validCompoundType, validSubTypes);
            assertEquals("SubTypes doesn't match.", validSubTypes, testCompoundTypeEntry.getSubTypes());
            assertFalse("Didn't return deepcopy.", validSubTypes == testCompoundTypeEntry.getSubTypes());
        } catch (Exception e) {
            fail("Caught exception.");
        }
    }

    @Test
    public void testOf() {
        // test null type
        try {
            CompoundTypeEntry.of(null, validSubTypes);
            fail("NullPointerException not caught.");
        } catch (NullPointerException exception) {
            assertTrue(true);
        } catch (Exception exception) {
            fail("Wrong type of exception.");
        }

        // test null subtypes
        try {
            CompoundTypeEntry.of(validCompoundType, null);
            fail("NullPointerException not caught.");
        } catch (NullPointerException exception) {
            assertTrue(true);
        } catch (Exception exception) {
            fail("Wrong type of exception.");
        }

        // test valid input
        try {
            CompoundTypeEntry testCompoundTypeEntry = CompoundTypeEntry.of(validCompoundType, validSubTypes);
            assertEquals("Type doesn't match.", validCompoundType,
                    CompoundTypeEntry.of(validCompoundType, validSubTypes).getType());
            assertEquals("SubTypes doesn't match.", validSubTypes, testCompoundTypeEntry.getSubTypes());
            assertFalse("Didn't return deepcopy.", validSubTypes == testCompoundTypeEntry.getSubTypes());
        } catch (Exception e) {
            fail("Caught exception.");
        }

        // test invalid arity
        try {
            CompoundTypeEntry.of(validCompoundType,
                    new ArrayList<>(Arrays.asList(BasicType.of("Char"), BasicType.of("Long"), BasicType.of("Double"))));
            fail("NullPointerException not caught.");
        } catch (ArityException exception) {
            assertTrue(true);
        }
    }

    @Test
    public void testToString() {
        // test 1
        CompoundType compoundType = CompoundType.of("CompoundType", 1);
        List<TypeEntry> subTypeList = new ArrayList<>(Arrays.asList(BasicType.of("A")));
        try {
            CompoundTypeEntry compoundTypeEntry = CompoundTypeEntry.of(compoundType, subTypeList);
            assertEquals("CompoundType<A>", compoundTypeEntry.toString());
        } catch (Exception e) {
            fail("Exception by error.");
        }

        // test many
        try {
            CompoundTypeEntry compoundTypeEntry = CompoundTypeEntry.of(validCompoundType, validSubTypes);
            assertEquals("HashMap<String, Type>", compoundTypeEntry.toString());
        } catch (Exception e) {
            fail("Exception by error.");
        }
    }

    @Test
    public void testHasEqualUnderlyingType() {
        BasicType basicType1 = BasicType.of("BasicType1");
        BasicType basicType2 = BasicType.of("BasicType2");

        CompoundType compoundType1 = CompoundType.of("CompoundType1", 1);
        CompoundType compoundType2 = CompoundType.of("CompoundType2", 1);

        try {
            CompoundTypeEntry compoundTypeEntry1 = CompoundTypeEntry.of(compoundType1, Arrays.asList(basicType1));
            CompoundTypeEntry compoundTypeEntry2 = CompoundTypeEntry.of(compoundType1, Arrays.asList(basicType2));
            CompoundTypeEntry compoundTypeEntry3 = CompoundTypeEntry.of(compoundType2, Arrays.asList(basicType2));

            assertTrue("Test equal types failed.", compoundTypeEntry1.hasEqualUnderlyingType(compoundTypeEntry2));
            assertFalse("Test unequal types failed.", compoundTypeEntry1.hasEqualUnderlyingType(compoundTypeEntry3));
        } catch (Exception exception) {
            fail("Wrong exception.");
        }
    }
}