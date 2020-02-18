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

    private static final TypeEntry string = BasicType.of("String");

    private static final TypeEntry type = BasicType.of("Type");

    private static final List<TypeEntry> validSubTypes = new ArrayList<>(Arrays.asList(string, type));

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
            TypeEntry testCompoundTypeEntry = CompoundTypeEntry.of(validCompoundType, validSubTypes);
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
            TypeEntry testCompoundTypeEntry = CompoundTypeEntry.of(validCompoundType, validSubTypes);
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
            TypeEntry compoundTypeEntry = CompoundTypeEntry.of(compoundType, subTypeList);
            assertEquals("CompoundType<A>", compoundTypeEntry.toString());
        } catch (Exception e) {
            fail("Exception by error.");
        }

        // test many
        try {
            TypeEntry compoundTypeEntry = CompoundTypeEntry.of(validCompoundType, validSubTypes);
            assertEquals("HashMap<String, Type>", compoundTypeEntry.toString());
        } catch (Exception e) {
            fail("Exception by error.");
        }
    }

    @Test
    public void testHasEqualUnderlyingType() {
        TypeEntry basicType1 = BasicType.of("BasicType1");
        TypeEntry basicType2 = BasicType.of("BasicType2");

        CompoundType compoundType1 = CompoundType.of("CompoundType1", 1);
        CompoundType compoundType2 = CompoundType.of("CompoundType2", 1);

        try {
            TypeEntry compoundTypeEntry1 = CompoundTypeEntry.of(compoundType1, Arrays.asList(basicType1));
            TypeEntry compoundTypeEntry2 = CompoundTypeEntry.of(compoundType1, Arrays.asList(basicType2));
            TypeEntry compoundTypeEntry3 = CompoundTypeEntry.of(compoundType2, Arrays.asList(basicType2));

            assertTrue("Test equal types failed.", compoundTypeEntry1.hasEqualUnderlyingType(compoundTypeEntry2));
            assertFalse("Test unequal types failed.", compoundTypeEntry1.hasEqualUnderlyingType(compoundTypeEntry3));
        } catch (Exception exception) {
            fail("Wrong exception.");
        }
    }

    @Test
    public void testRepresentativeString() {

        // test null
        try {
            TypeEntry compoundTypeEntry = CompoundTypeEntry.of(validCompoundType, validSubTypes);
            compoundTypeEntry.representativeString(null);
            fail("NullPointerException not caught.");
        } catch (NullPointerException exception) {
            assertTrue(true);
        } catch (Exception exception) {
            fail("Wrong type of exception.");
        }

        // test valid
        try {
            TypeSystem ts = new TypeSystem();

            TypeEntry t = new VariableType();
            TypeEntry s = new VariableType();

            List<TypeEntry> variableSubTypes = new ArrayList<>(Arrays.asList(t, s));

            TypeEntry compoundTypeEntry1 = CompoundTypeEntry.of(validCompoundType, validSubTypes);
            CompoundTypeEntry compoundTypeEntry2 = CompoundTypeEntry.of(validCompoundType, variableSubTypes);

            ts.add(string);
            ts.add(type);
            ts.add(t);
            ts.add(s);
            ts.add(compoundTypeEntry1);
            ts.add(compoundTypeEntry2);

            ts.unify(compoundTypeEntry1, compoundTypeEntry2);

            assertEquals("TypeEntry without variable type wrong", "HashMap<String, Type>",
                    compoundTypeEntry1.representativeString(ts));
            assertEquals("TypeEntry with variable type should now return unified representation.",
                    "HashMap<String, Type>", compoundTypeEntry2.representativeString(ts));
        } catch (Exception exception) {
            fail("Exception.");
        }
    }
}