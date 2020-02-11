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
        BasicType wrapper = BasicType.of("Wrapper");

        TypeSystem ts = new TypeSystem();
        
        ts.add(t);
        ts.add(s);
        ts.add(u);
        ts.add(z);
        ts.add(integer);
        ts.add(wrapper);

        // test nulll
        try {
            ts.unify(null, null);
            fail("NullPointerException not thrown.");
        } catch (NullPointerException exception) {
            assertTrue(true);
        } catch (Exception exception) {
            fail("Wrong exception");
        }

        // test simple type entries
        try {
            assertTrue("Two variable types should unify.", ts.unify(s, t));
            assertTrue("Two equal basic types should unify.", ts.unify(integer, integer));
            assertTrue("One variable type with one basic type should unify.", ts.unify(s, integer));
            assertFalse("Two different basic types should not unify.", ts.unify(integer, wrapper));
        } catch (Exception exception) {
            fail("Shouldn't catch exception here.");
        }

        // test compound type entries
        try {
            CompoundTypeEntry listInt = CompoundTypeEntry.of(list, new ArrayList<>(Arrays.asList(integer)));
            CompoundTypeEntry listWrap = CompoundTypeEntry.of(list, new ArrayList<>(Arrays.asList(wrapper)));
            CompoundTypeEntry listT = CompoundTypeEntry.of(list, new ArrayList<>(Arrays.asList(t)));

            CompoundTypeEntry funcST = CompoundTypeEntry.of(function, new ArrayList<>(Arrays.asList(s, t)));
            CompoundTypeEntry funcIntU = CompoundTypeEntry.of(function, new ArrayList<>(Arrays.asList(integer, u)));
            CompoundTypeEntry funcWrapU = CompoundTypeEntry.of(function, new ArrayList<>(Arrays.asList(wrapper, u)));

            CompoundTypeEntry bifunc1 = CompoundTypeEntry.of(bifunction,
                    new ArrayList<>(Arrays.asList(funcST, listInt, listT)));
            CompoundTypeEntry bifunc2 = CompoundTypeEntry.of(bifunction,
                    new ArrayList<>(Arrays.asList(funcIntU, listInt, z)));
            CompoundTypeEntry bifunc3 = CompoundTypeEntry.of(bifunction,
                    new ArrayList<>(Arrays.asList(funcIntU, listWrap, z)));

            ts.add(listInt);
            ts.add(listWrap);
            ts.add(listT);
            ts.add(funcST);
            ts.add(funcIntU);
            ts.add(funcWrapU);
            ts.add(bifunc1);
            ts.add(bifunc2);
            ts.add(bifunc3);

            assertTrue("Should unify.", ts.unify(listInt, listT));
            assertTrue("Should unify.", ts.unify(funcST, funcIntU));
            assertTrue("Should unify.", ts.unify(bifunc1, bifunc2));

            assertFalse("Should not unify.", ts.unify(funcST, listInt));
            assertFalse("Should not unify.", ts.unify(listWrap, listInt));
            assertFalse("Should not unify.", ts.unify(listWrap, listInt));

            assertFalse("Should not unify.", ts.unify(funcIntU, funcWrapU));
            assertFalse("Should not unify.", ts.unify(bifunc1, bifunc3));

        } catch (Exception e) {
            fail("Shouldn't catch exception here.");
        }

    }
}