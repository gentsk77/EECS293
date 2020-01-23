package edu.cwru.yxs626.typeinference;

/**
 * TypeName represents the name of a type, such as the strings "Integer", "List", etc.
 * 
 * @author Yue Shu
 */
public final class TypeName {

    private final String identifier;

    private TypeName(String identifier) {
        this.identifier = identifier;
    }

    //  TODO: throws a NullPointerException if the identifier is null and an
    //  IllegalStateException with an appropriate message if another TypeName with
    //  the same identifier has already been defined
    public static final TypeName of(String identifier) {
        return new TypeName(identifier);
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return this.getIdentifier();
    }
}