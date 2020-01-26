package edu.cwru.yxs626.typeinference;

import java.util.HashMap;
import java.util.Map;

/**
 * TypeName represents the name of a type, such as the strings "Integer",
 * "List", etc.
 * 
 * @author Yue Shu
 */
public final class TypeName {

    private final String identifier;

    private static final Map<String, TypeName> definedTypeNames = new HashMap<>();

    private TypeName(String identifier) {
        this.identifier = identifier;
    }

    public static final TypeName of(String identifier) {
        sanityCheck(identifier);

        TypeName typeName = new TypeName(identifier);
        definedTypeNames.put(identifier, typeName);

        return typeName;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return this.getIdentifier();
    }

    private static void sanityCheck(String identifier) {
        if (identifier == null) {
            throw new NullPointerException("The input identifier is null");
        }
        if (definedTypeNames.containsKey(identifier)) {
            throw new IllegalStateException("Another TypeName with the same identifier has already been defined.");
        }
    }
}