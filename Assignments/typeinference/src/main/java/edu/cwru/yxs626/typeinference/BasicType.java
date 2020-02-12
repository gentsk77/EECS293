package edu.cwru.yxs626.typeinference;
import java.util.Objects;

/**
 * BasicType is a simple type, such as Integer, Boolean, or Double.
 * 
 * @author Yue Shu
 */
public final class BasicType extends SimpleTypeEntry {

    /** The TypeName of the BasicType. */
    private final TypeName typeName;

    /**
     * Creates a BasicType with the input TypeName.
     * 
     * @param typeName the TypeName used to declare the BasicType
     */
    private BasicType(TypeName typeName) {
        this.typeName = typeName;
    }

    /**
     * Returns a new BasicType with the input identifier.
     * 
     * @param identifier the identifier used to declare the BasicType
     * @return a BasicType
     */
    public static final BasicType of(String identifier) {
        Objects.requireNonNull(identifier, "Identifier should not be null");

        try {
            // first creates a TypeName with the given identifier, possible exceptions will
            // be propagated from TypeName::of
            TypeName typeName = TypeName.of(identifier);
            return new BasicType(typeName);
        }
        // catch the IllegalStateException propogated from TypeName::of
        catch (IllegalStateException exception) {
            // re-throw the exception with updated message
            throw new IllegalStateException("Another BasicType with the same identifier has already been defined.");
        }
    }

    /**
     * Returns the TypeName of the BasicType.
     * 
     * @return the TypeName of the BasicType
     */
    public TypeName getTypeName() {
        return typeName;
    }

    /**
     * Returns the identifier of the TypeName of the BasicType.
     * 
     * @return the identifier of the TypeName of the BasicType
     */
    public String getIdentifier() {
        return getTypeName().getIdentifier();
    }

    /**
     * Returns a string representation of the BasicType.
     * 
     * @return a string representation of the BasicType
     */
    @Override
    public String toString() {
        return getTypeName().toString();
    }

}