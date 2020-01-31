package edu.cwru.yxs626.typeinference;

public final class CompoundType implements Type {

    private final TypeName typeName;

    private final int arity;

    private CompoundType(TypeName typeName, int arity) {
        this.typeName = typeName;
        this.arity = arity;
    }

    public String getIdentifier() {
        return typeName.getIdentifier();
    }

    public int getArity() {
        return this.arity;
    }

    public static final CompoundType of(String identifier, int arity) {
        try {
            checkArity(arity);
            TypeName typeName = TypeName.of(identifier);

            return new CompoundType(typeName, arity);
        }
        // catch the IllegalStateException propogated from TypeName::of
        catch (IllegalStateException exception) {
            // re-throw the exception with updated message
            throw new IllegalStateException("Another CompoundType with the same identifier has already been defined.");
        }
    }

    private static void checkArity(int arity) {
        if (arity < 1) {
            throw new IllegalArgumentException("The input arity should be a positive integer.");
        }
        // else pass the arity test
    }

    @Override
    public String toString() {
        return typeName.toString();
    }

    @Override
    public boolean isVariable() {
        return false;
    }

}