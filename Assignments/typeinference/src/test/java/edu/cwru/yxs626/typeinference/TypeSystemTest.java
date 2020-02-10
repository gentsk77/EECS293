package edu.cwru.yxs626.typeinference;

import org.junit.Test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Test
    public void testUnify() {
        CompoundType list = CompoundType.of("List", 1);
        CompoundType function = CompoundType.of("Function", 2);
        CompoundType bifunction = CompoundType.of("BiFunction", 3);

        VariableType t = new VariableType();
        VariableType s = new VariableType();
        VariableType u = new VariableType();
        VariableType z = new VariableType();

        BasicType integer = BasicType.of("Integer");

        TypeSystem ts = new TypeSystem();

        ts.add(t);
        ts.add(s);
        ts.add(u);
        ts.add(z);
        ts.add(integer);

        

        try {
            CompoundTypeEntry listInt = CompoundTypeEntry.of(list, new ArrayList<>(Arrays.asList(integer)));
            CompoundTypeEntry listT = CompoundTypeEntry.of(list, new ArrayList<>(Arrays.asList(t)));

            CompoundTypeEntry funcST = CompoundTypeEntry.of(function, new ArrayList<>(Arrays.asList(s, t)));
            CompoundTypeEntry funcIntU = CompoundTypeEntry.of(function, new ArrayList<>(Arrays.asList(integer, u)));

            CompoundTypeEntry bifunc1 = CompoundTypeEntry.of(bifunction,
                    new ArrayList<>(Arrays.asList(funcST, listInt, listT)));
            CompoundTypeEntry bifunc2 = CompoundTypeEntry.of(bifunction, new ArrayList<>(Arrays.asList(funcIntU, listInt, z)));

            ts.add(listInt);
            ts.add(listT);
            ts.add(funcST);
            ts.add(funcIntU);
            ts.add(bifunc1);
            ts.add(bifunc2);

            System.out.println(ts.unify(bifunc1, bifunc2));

        } catch (ArityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}