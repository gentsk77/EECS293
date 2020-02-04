package edu.cwru.yxs626.typeinference;

import org.junit.Test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.Map;

public class TypeSystemTest {

    @Test
    public void testAdd() {
        TypeSystem typeSystem = new TypeSystem();

        // test null
        try {
            typeSystem.add(null);
            fail("Exception not thrown.");
        } catch (NullPointerException exception) {
            assertTrue(true);
        } catch (Exception exception) {
            fail("Wrong exception type.");
        }

        // test valid
        BasicType validType = BasicType.of("AddType");
        assertEquals("TypeEntry not matched.", validType, typeSystem.add(validType));

        // test repeated
        Field groupsField;
        try {
            // use reflect to access original groups
            groupsField = TypeSystem.class.getDeclaredField("groups");
            groupsField.setAccessible(true);
            Map<TypeEntry, TypeGroup> ogGroups = (Map<TypeEntry, TypeGroup>) groupsField.get(typeSystem);

            // add repeated
            assertEquals("TypeEntry not matched.", validType, typeSystem.add(validType));
            Map<TypeEntry, TypeGroup> newGroups = (Map<TypeEntry, TypeGroup>) groupsField.get(typeSystem);

            assertEquals("Groups not matched.", ogGroups, newGroups);
        } catch (NoSuchFieldException e) {
            fail("groups not defined as a field.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRepresentative() {
        TypeSystem typeSystem = new TypeSystem();

        // test null
        try {
            typeSystem.representative(null);
            fail("Exception not thrown.");
        } catch (NullPointerException exception) {
            assertTrue(true);
        } catch (Exception exception) {
            fail("Wrong exception type.");
        }

        // test illegal
        BasicType newType = BasicType.of("NewType");
        try {
            typeSystem.representative(newType);
            fail("Exception not thrown.");
        } catch (IllegalStateException exception) {
            assertTrue(true);
        } catch (Exception exception) {
            fail("Wrong exception type.");
        }

        // test valid
        typeSystem.add(newType);
        assertEquals("TypeEntry not matched.", newType, typeSystem.representative(newType));
    }
}