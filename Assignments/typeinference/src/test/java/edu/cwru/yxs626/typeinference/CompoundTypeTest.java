package edu.cwru.yxs626.typeinference;

import org.junit.Test;

import static org.junit.Assert.*;

public class CompoundTypeTest {

    @Test
    public void testGetIdentifier() {
        assertEquals("Identifier doesn't match.", "ListDumb", CompoundType.of("ListDumb", 1).getIdentifier());
    }

    @Test
    public void testGetArity() {
        assertEquals("Arity doesn't match.", 2, CompoundType.of("Map", 2).getArity());
    }

    @Test
    public void testToString() {
        assertEquals("String doesn't match.", "Set", CompoundType.of("Set", 2).toString());
    }

    @Test
    public void testIsVariable() {
        assertFalse("Should return false.", CompoundType.of("ArrayList", 1).isVariable());
    }

    @Test
    public void testOf() {
        // test null
        try {
            CompoundType.of(null, 1);
            fail("NullPointerException not thrown given a null identifier.");
        } catch (NullPointerException exception) {
            assertTrue(true);
        }

        // test invalid arity
        try {
            CompoundType.of("LinkedList", 0);
            fail("IllegalArgumentException not thrown at non-positive arity.");
        } catch (IllegalArgumentException exception) {
            assertTrue(true);
        }

        // test valid input
        try {
            CompoundType compoundType = CompoundType.of("LinkedList", 1);
            assertEquals("Test valid identifier doesn't match.", "LinkedList", compoundType.getIdentifier());
            assertEquals("Test valid arity doesn't match.", 1, compoundType.getArity());
        } catch (Exception exception) {
            fail("Test valid input failed");
        }

        // test repeated input
        try {
            CompoundType.of("LinkedList", 1);
            fail("IllegalStateException not thrown given a repeated identifier.");
        } catch (IllegalStateException exception) {
            assertTrue(true);
        }
    }
}
