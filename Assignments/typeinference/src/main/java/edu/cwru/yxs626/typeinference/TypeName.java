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

    /** The identifier used to represent the TypeName. */
    private final String identifier;

    /**
     * Maps identifiers that have already been used to their corresponding
     * TypeNames.
     */
    private static final Map<String, TypeName> definedTypeNames = new HashMap<>();

    /**
     * Creates a TypeName with the given identifier.
     * 
     * @param identifier the string used as the identifier
     */
    private TypeName(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns a new TypeName with the given identifier.
     * 
     * @param identifier the string used as the identifier
     * @return a new TypeName
     */
    public static final TypeName of(String identifier) {
        // validates the input, throws exceptions if the input fails the identifier test
        checkIdentifier(identifier);

        // the rest of the code would not be reached if the input fails the identifier
        // test

        TypeName typeName = new TypeName(identifier);
        // maps the identifier with the created type name
        definedTypeNames.put(identifier, typeName);

        return typeName;
    }

    /**
     * Returns the identifier of the TypeName.
     * 
     * @return the identifier of the TypeName
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Returns a string representation of the TypeName.
     * 
     * @return a string representation of the TypeName
     */
    @Override
    public String toString() {
        return this.getIdentifier();
    }

    /**
     * Performs sanity check on the given identifier input.
     * 
     * @param identifier the identifier to be validated
     */
    private static void checkIdentifier(String identifier) {
        if (identifier == null) {
            throw new NullPointerException("The input identifier is null");
        }
        // if there is already a TypeName declared from the input identifier
        else if (definedTypeNames.containsKey(identifier)) {
            throw new IllegalStateException("Another TypeName with the same identifier has already been defined.");
        }
        // else the input identifier pass the test, nothing to be done
    }
}