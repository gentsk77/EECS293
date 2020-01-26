package edu.cwru.yxs626.typeinference;

public final class BasicType extends SimpleTypeEntry {

    private final TypeName typeName;

    private BasicType(TypeName typeName) {
        this.typeName = typeName;
    }

    public static final BasicType of(String identifier) {
        try {
            TypeName typeName = TypeName.of(identifier);
            return new BasicType(typeName);
        } catch (IllegalStateException exception) {
            throw new IllegalStateException("Another BasicType with the same identifier has already been defined.");
        }
    }

    public TypeName getTypeName() {
        return this.typeName;
    }

    public String getIdentifier() {
        return this.typeName.getIdentifier();
    }

    @Override
    public String toString() {
        return this.typeName.toString();
    }

}