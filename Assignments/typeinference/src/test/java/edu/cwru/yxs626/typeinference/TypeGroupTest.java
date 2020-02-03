package edu.cwru.yxs626.typeinference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;

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
        // test null
        
    }
}