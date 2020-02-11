package edu.cwru.yxs626.typeinference;

import org.junit.Test;

import static org.junit.Assert.*;

import java.lang.reflect.*;

public class BasicTypeTest {

    @Test
    public void testGetIdentifier() {
        assertEquals("Identifier doesn't match.", "E", BasicType.of("E").getIdentifier());
    }

    @Test
    public void testToString() {
        assertEquals("String doesn't match.", "F", BasicType.of("F").toString());
    }

    @Test
    public void testGetTypeName() {
        // using java reflection to access field typeName
        BasicType basicType = BasicType.of("G");
        Field typeNameField;
        try {
            typeNameField = BasicType.class.getDeclaredField("typeName");
            typeNameField.setAccessible(true);
            TypeName typeName = (TypeName) typeNameField.get(basicType);

            // test valid typeName
            assertEquals("TypeName doesn't match.", typeName, basicType.getTypeName());
        } catch (NoSuchFieldException e) {
            fail("TypeName not defined as a field.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOf() {
        // test null
        try {
            BasicType.of(null);
            fail("NullPointerException not thrown given a null identifier.");
        } catch (NullPointerException exception) {
            assertTrue(true);
        }

        // test valid input
        try {
            BasicType basicType = BasicType.of("H");
            assertEquals("Test valid identifier doesn't match.", "H", basicType.getIdentifier());
        } catch (Exception exception) {
            fail("Test valid input failed");
        }

        // test repeated input
        try {
            BasicType.of("H");
            fail("IllegalStateException not thrown given a repeated identifier.");
        } catch (IllegalStateException exception) {
            assertTrue(true);
        }
    }

    @Test
    public void testHasEqualUnderlyingType() {
        BasicType basicType1 = BasicType.of("ValidTestingType1");

        // test null
        try {
            basicType1.hasEqualUnderlyingType(null);
            fail("NullPointerException not thrown.");
        } catch (NullPointerException exception) {
            assertTrue(true);
        } catch (Exception exception) {
            fail("Wrong exception.");
        }

        // test valid
        try {
            BasicType basicType2 = BasicType.of("ValidTestingType2");
            assertFalse("Simple type entries should not be equal.", basicType1.hasEqualUnderlyingType(basicType2));
        } catch (Exception exception) {
            fail("Should not throw exceptions.");
        }
    }
}
