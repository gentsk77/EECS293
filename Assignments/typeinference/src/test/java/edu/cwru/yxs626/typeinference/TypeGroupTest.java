package edu.cwru.yxs626.typeinference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.util.Iterator;

import org.junit.Test;

public class TypeGroupTest {

    @Test
    public void testGetRepresentative() {
        TypeGroup typeGroup = TypeGroup.of(BasicType.of("NullPointerException"), new TypeSystem());

        Field representativeField;
        try {
            representativeField = TypeGroup.class.getDeclaredField("representative");
            representativeField.setAccessible(true);
            BasicType basicType = (BasicType) representativeField.get(typeGroup);

            // test valid typeName
            assertEquals("TypeEntry doesn't match.", basicType, typeGroup.getRepresentative());
        } catch (NoSuchFieldException e) {
            fail("TypeEntry not defined as a field.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSize() {
        TypeGroup typeGroup = TypeGroup.of(BasicType.of("IllegalStateException"), new TypeSystem());
        assertEquals(typeGroup.size(), 1);
    }

    @Test
    public void testOf() {
        // test null type entry
        try {
            TypeGroup.of(null, new TypeSystem());
            fail("NullPointerException not thrown.");
        } catch (NullPointerException exception) {
            assertTrue(true);
        } catch (Exception exception) {
            fail("Wrong exception.");
        }

        // test null type system
        try {
            TypeGroup.of(BasicType.of("IdleType"), null);
            fail("NullPointerException not thrown.");
        } catch (NullPointerException exception) {
            assertTrue(true);
        } catch (Exception exception) {
            fail("Wrong exception.");
        }

        // test valid input
        try {
            BasicType validType = BasicType.of("ValidType");
            TypeGroup validGroup = TypeGroup.of(validType, new TypeSystem());

            assertEquals("Representative not matched.", validType, validGroup.getRepresentative());
            assertEquals("Size not matched.", 1, validGroup.size());
        } catch (Exception exception) {
            fail("Exception caught.");
        }
    }

    @Test
    public void testIterator() {
        try {
            BasicType validType = BasicType.of("Iterator");
            TypeGroup validGroup = TypeGroup.of(validType, new TypeSystem());
            Iterator<TypeEntry> itr = validGroup.iterator();

            while (itr.hasNext()) {
                assertEquals("TypeEntry not matched", validType, itr.next());
            }
        } catch (Exception exception) {
            fail("Exception caught.");
        }
    }
}