package edu.cwru.yxs626.typeinference;

import org.junit.Test;

import static org.junit.Assert.*;

public class TypeNameTest {
    @Test
    public void testGetIdentifier() {
        assertEquals("Identifier doesn't match.", "X", TypeName.of("X").getIdentifier());
    }

    @Test
    public void testToString() {
        assertEquals("String doesn't match.", "B", TypeName.of("B").toString());
    }

    @Test
    public void testOf() {
        // test null
        try {
            TypeName.of(null);
            fail("NullPointerException not thrown given a null identifier.");
        } catch (NullPointerException exception) {
            assertTrue(true);
        }

        // test valid input
        try {
            TypeName typeName = TypeName.of("C");
            assertEquals("Test valid identifier dosen't match.", "C", typeName.getIdentifier());
        } catch (Exception exception) {
            fail("Test valid input failed");
        }

        // test repeated input
        try {
            TypeName.of("C");
            fail("IllegalStateException not thrown given a repeated identifier.");
        } catch (IllegalStateException exception) {
            assertTrue(true);
        }
    }

}
