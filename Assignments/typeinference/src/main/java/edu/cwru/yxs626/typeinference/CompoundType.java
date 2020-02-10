package edu.cwru.yxs626.typeinference;

/**
 * A Compound Type is a type used in conjunction with other types, such as List
 * in the case of List<Integer>.
 * 
 * @author Yue Shu
 */
public final class CompoundType implements Type {

    /** The TypeName of the CompoundType. */
    private final TypeName typeName;

    /** The arity of the CompoundType. */
    private final int arity;

    /**
     * Creates a new CompoundType.
     * 
     * @param typeName the TypeName of the CompoundType
     * @param arity    the arity of the CompoundType
     */
    private CompoundType(TypeName typeName, int arity) {
        this.typeName = typeName;
        this.arity = arity;
    }

    /**
     * Returns the identifier of the CompoundType.
     * 
     * @return the identifier of the CompoundType
     */
    public String getIdentifier() {
        return typeName.getIdentifier();
    }

    /**
     * Returns the arity of the CompoundType.
     * 
     * @return the arity of the CompoundType
     */
    public int getArity() {
        return arity;
    }

    /**
     * Creates a new CompoundType with the given identfier and arity.
     * 
     * @param identifier the identifier to define the CompoundType
     * @param arity      the arity to define the CompoundType
     * @return a new CompoundType
     */
    public static final CompoundType of(String identifier, int arity) {
        // moved the location of checkArity
        checkArity(arity);

        try {
            TypeName typeName = TypeName.of(identifier);

            return new CompoundType(typeName, arity);
        }
        // catch the IllegalStateException propogated from TypeName::of
        catch (IllegalStateException exception) {
            // re-throw the exception with updated message
            throw new IllegalStateException("Another CompoundType with the same identifier has already been defined.");
        }
    }

    /** Check the arity of the CompoundType to see whether it's valid. */
    private static void checkArity(int arity) {
        if (arity < 1) {
            throw new IllegalArgumentException("The input arity should be a positive integer.");
        }
        // else pass the arity test
    }

    /**
     * Returns a String representation of the CompoundType.
     * 
     * @return a String representation of the CompoundType
     */
    @Override
    public String toString() {
        return typeName.toString();
    }

    /**
     * Check whether the CompoundType is a variable.
     * 
     * @return false
     */
    @Override
    public boolean isVariable() {
        return false;
    }

}